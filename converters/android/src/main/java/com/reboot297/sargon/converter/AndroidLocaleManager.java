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

import com.reboot297.sargon.model.LocalePath;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Locale Manager for Android files.
 */
@Singleton
final class AndroidLocaleManager implements TextLocaleManager {

    /**
     * Folder name for default values.
     */
    private static final String FOLDER_NAME = "values";

    /**
     * Default name for file with string resources.
     */
    private static final String FILE_NAME = "strings.xml";

    /**
     * Default constructor.
     */
    @Inject
    AndroidLocaleManager() {

    }

    /**
     * Get path to file with strings for specific locale.
     *
     * @param rootFolder root folder
     * @param locale     locale
     * @return path
     */
    @Nonnull
    @Override
    public String pathToLocaleFile(@Nonnull String rootFolder, @Nonnull Locale locale) {
        return rootFolder + File.separator + nameFromLocale(locale) + File.separator + FILE_NAME;
    }

    /**
     * Find files with strings in root folder.
     *
     * @param rootFolderPath root folder
     * @return list of objects
     */
    @Nonnull
    @Override
    public List<LocalePath> findFiles(@Nonnull String rootFolderPath) {
        //TODO refactoring this method and add unit tests
        var result = new ArrayList<LocalePath>();
        var folders = new File(rootFolderPath).listFiles();
        if (folders != null) {
            for (var folder : folders) {
                if (folder.isDirectory()) {
                    var files = folder.listFiles();
                    if (files != null) {
                        for (var file : files) {
                            if (file.getName().equals(FILE_NAME)) {
                                var parentName = file.getParentFile().getName();
                                var locale = extractLocale(parentName);
                                var localePath = new LocalePath(locale, file.getAbsolutePath());
                                result.add(localePath);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Extract locale from folder name.
     *
     * @param fileName name
     * @return locale
     */
    @Nonnull
    public Locale extractLocale(@Nonnull String fileName) {
        var language = "";
        var country = "";
        if (!fileName.equals(FOLDER_NAME) && fileName.startsWith(FOLDER_NAME)) {
            var values = fileName.replace("values-", "").replace("-r", " ").split(" ");
            language = values[0];
            if (values.length > 1) {
                country = values[1];
            }
        }
        return new Locale(language, country);
    }

    /**
     * Create name for android folder depending on locale.
     *
     * @param locale locale
     * @return folder name
     */
    @Nonnull
    @Override
    public String nameFromLocale(@Nonnull Locale locale) {
        var builder = new StringBuilder();
        builder.append(FOLDER_NAME);
        if (locale.getLanguage() != null && !locale.getLanguage().isBlank()) {
            builder.append('-');
            builder.append(locale.getLanguage().toLowerCase());
            if (locale.getCountry() != null && !locale.getCountry().isBlank()) {
                builder.append("-r").append(locale.getCountry().toUpperCase());
            }
        }
        return builder.toString();
    }
}
