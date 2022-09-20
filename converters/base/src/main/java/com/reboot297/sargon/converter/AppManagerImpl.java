/*
 * Copyright (c) 2022. Viktor Pop
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.reboot297.sargon.converter;

import com.reboot297.sargon.manager.AppManager;
import com.reboot297.sargon.model.BaseItem;
import com.reboot297.sargon.model.LocaleGroup;
import com.reboot297.sargon.model.PropertyItem;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Implementation of the {@link AppManager}.
 */
final class AppManagerImpl implements AppManager {

    /**
     * default path to property file.
     */
    private static final String DEFAULT_PROPERTIES_PATH = "./sargon.properties";

    /**
     * Application settings.
     */
    private Properties appProps = new Properties();

    /**
     * Map of converters.
     */
    @SuppressWarnings("unused")
    private final Map<String, BaseConverter> converters = new HashMap<>();

    public void addConverter(@Nonnull BaseConverter converter) {
        converters.put(converter.getCommand(), converter);
    }

    @Override
    public void generateProperties() {
        List<Set<PropertyItem>> properties = new ArrayList<>();
        properties.add(getAppProperties());
        for (var converter : converters.values()) {
            properties.add(converter.getPropertiesManager().getAvailableProperties());
        }
        updateProperties(DEFAULT_PROPERTIES_PATH, properties);
    }

    private Set<PropertyItem> getAppProperties() {
        var properties = new HashSet<PropertyItem>();
        properties.add(new PropertyItem("app.sample-property", "Sample value", "Sample comment"));
        return properties;
    }

    private static void updateProperties(String filePath, List<Set<PropertyItem>> props) {
        // File file = new File(filePath);
        PropertiesConfiguration config = new PropertiesConfiguration();
        PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout();
        try {
            //layout.load(config, new InputStreamReader(new FileInputStream(file)));
            for (var set : props) {
                for (var p : set) {
                    var key = p.getKey();
                    var value = p.getValue();
                    var comment = p.getComment();
                    config.setProperty(key, value);
                    layout.setComment(key, comment);
                    layout.setBlankLinesBefore(key, 1);
                }
            }
            layout.save(config, new FileWriter(filePath, false));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Inject
    AppManagerImpl(XlsConverter xlsConverter, AndroidConverter androidConverter) {
        addConverter(androidConverter);
        addConverter(xlsConverter);
    }

    @Override
    public boolean convert(String from, String to) { //TODO run in background thread
        loadProperties();

        var inputKey = converters.get(from).getPropertiesManager().getInputKey();
        var outputKey = converters.get(to).getPropertiesManager().getOutputKey();

        var inputFilePath = appProps.getProperty(inputKey);
        var outputFilePath = appProps.getProperty(outputKey);
        return convert(from, to, inputFilePath, outputFilePath);
    }

    @Override
    public boolean convert(@Nonnull String from,
                           @Nonnull String to,
                           @Nonnull String sourcePath,
                           @Nonnull String destinationPath) {
        System.out.println("Convert data from " + from + " to " + to);
        System.out.println("Source path: " + sourcePath);
        System.out.println("Destination path: " + destinationPath);
        var result = false;
        var converterFrom = converters.get(from);
        var converterTo = converters.get(to);
        if (converterFrom != null && converterTo != null) {
            var items = readLocaleItems(sourcePath, converterFrom);
            result = writeLocaleFiles(destinationPath, converterTo, items);
            System.out.println("Convert data completed. success: " + result);
        } else {
            System.err.println("Can't find converter");
        }
        return result;
    }

    private List<LocaleGroup> readLocaleItems(@Nonnull String sourcePath,
                                              @Nonnull BaseConverter converter) {
        var localeItems = new ArrayList<LocaleGroup>();
        if (converter.isTable()) {
            return (List<LocaleGroup>) converter.readItems(sourcePath);
        } else {
            BaseTextConverterImpl textConverter = (BaseTextConverterImpl) converter;
            var pathToFiles = textConverter.getLocaleManager().findFiles(sourcePath);
            for (var path : pathToFiles) {
                localeItems.add(new LocaleGroup(path.getLocale(),
                        (List<BaseItem>) converter.readItems(path.getLocalePath())));
            }
        }
        return localeItems;
    }

    private boolean writeLocaleFiles(@Nonnull String destinationPath,
                                     @Nonnull BaseConverter converter,
                                     @Nonnull List<LocaleGroup> items) {
        if (converter.isTable()) {
            return converter.writeItems(destinationPath, items);
        } else {
            return writeToTextLocalFiles((BaseTextConverterImpl) converter, destinationPath, items);
        }
    }

    private boolean writeToTextLocalFiles(@Nonnull BaseTextConverterImpl converter,
                                      @Nonnull String destinationPath,
                                      @Nonnull List<LocaleGroup> items) {
        boolean success = false;
        for (var item : items) {
            var path = converter.getLocaleManager().pathToLocaleFile(destinationPath, item.getLocale());
            success |= converter.writeItems(path, item.getItems());
        }
        return success;
    }

    private void loadProperties() {
        System.out.println("Load properties");
        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(DEFAULT_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> getAvailableIds() {
        return converters.keySet();
    }
}
