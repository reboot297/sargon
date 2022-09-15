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

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Implementation of the {@link AppManager}.
 */
final class AppManagerImpl implements AppManager {

    /**
     * default path to property file.
     */
    private static final String DEFAULT_PROPERTIES_PATH = "./sargon.properties";
    /**
     * Suffix for input path property.
     */
    private static final String PROPERTIES_SUFFIX_INPUT = "-input";
    /**
     * Suffix for output path property.
     */
    private static final String PROPERTIES_SUFFIX_OUTPUT = "-output";

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

    @Inject
    AppManagerImpl(XlsConverter xlsConverter, AndroidConverter androidConverter) {
        addConverter(androidConverter);
        addConverter(xlsConverter);
    }

    @Override
    public boolean convert(String from, String to) {
        loadProperties();
        var inputFilePath = appProps.getProperty(from + PROPERTIES_SUFFIX_INPUT);
        var outputFilePath = appProps.getProperty(to + PROPERTIES_SUFFIX_OUTPUT);
        return convert(from, to, inputFilePath, outputFilePath);
    }

    @Override
    public boolean convert(@Nonnull String from,
                           @Nonnull String to,
                           @Nonnull String sourcePath,
                           @Nonnull String destinationPath) {

        var converterFrom = converters.get(from);
        var converterTo = converters.get(to);
        if (converterFrom != null && converterTo != null) {
            var source = converterFrom.getFileReader().readFile(sourcePath);
            var items = converterFrom.getParser().parse(source);
            var result = converterTo.getFormatter().format(items);
            converterTo.getFileWriter().writeFile(result, destinationPath);
            return true;
        }
        return false;
    }

    private void loadProperties() {
        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(DEFAULT_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> getAvailableCommands() {
        return converters.keySet();
    }
}
