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
import com.reboot297.sargon.model.PropertyItem;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
     * Executor service for background work.
     */
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * Map of converters.
     */
    @SuppressWarnings("unused")
    private final Map<String, BaseConverter> converters = new HashMap<>();

    @Inject
    AppManagerImpl(XlsConverter xlsConverter, AndroidConverter androidConverter) {
        addConverter(androidConverter);
        addConverter(xlsConverter);
    }


    public void addConverter(@Nonnull BaseConverter converter) {
        converters.put(converter.getCommand(), converter);
    }

    @Override
    public void generateProperties() {
        executorService.submit(this::runGenerateProperties);
        executorService.shutdown();
    }

    public void runGenerateProperties() {
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

    @Override
    public boolean convert(String from, String to) {
        var future = executorService.submit(() -> runConvertCommand(from, to));
        boolean result = false;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return result;
    }

    private boolean runConvertCommand(@Nonnull String from, @Nonnull String to) {
        if (!loadProperties()) {
            return false;
        }

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
            var items = (List<BaseItem>) converterFrom.readItems(sourcePath);
            result = converterTo.writeItems(destinationPath, items);
            System.out.println("Convert data completed. success: " + result);
        } else {
            System.err.println("Can't find converter");
        }
        return result;
    }

    private boolean loadProperties() {
        System.out.println("Load properties");
        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(DEFAULT_PROPERTIES_PATH));
            return true;
        } catch (FileNotFoundException e) {
            String message = String.format("[ERROR] Can't find '%s' file. \n"
                    + "Please make sure that the one exists, if not, then "
                    + "run app with '--generate-properties' command to generate this file,"
                    + " with default values.", DEFAULT_PROPERTIES_PATH);
            System.err.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Set<String> getAvailableIds() {
        return converters.keySet();
    }
}
