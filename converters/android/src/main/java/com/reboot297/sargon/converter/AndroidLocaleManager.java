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

/**
 * Locale Manager for Android files.
 */
@Singleton
final class AndroidLocaleManager extends BaseTextLocaleManagerImpl {

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
     * @param localeKey  locale key
     * @return path
     */
    @Nonnull
    @Override
    public String pathToLocaleFile(@Nonnull String rootFolder, @Nonnull String localeKey) {
        return rootFolder + File.separator + nameFromLocale(localeKey) + File.separator + FILE_NAME;
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
    public String extractLocale(@Nonnull String fileName) {
        if (!fileName.equals(FOLDER_NAME) && fileName.startsWith(FOLDER_NAME)) {
            var values = fileName.replace("values-", "")
                    .replace("-r", " ")
                    .split(" ");
            var firstPart = values[0];
            if (isLanguageValid(firstPart)) {
                return extractLocale(values);
            } else {
                return fileName.replace("values-", "");
            }
        }
        return "";
    }

    @Nonnull
    private String extractLocale(String[] values) {
        var builder = new StringBuilder();
        builder.append(values[0].toLowerCase());
        if (values.length == 2 && isCountryValid(values[1])) {
            builder.append(LOCALE_NAME_SEPARATOR)
                    .append(values[1].toUpperCase());
        }
        return builder.toString();
    }

    /**
     * Create name for android folder depending on locale.
     *
     * @param localeKey locale-key value
     * @return folder name
     */
    @Nonnull
    @Override
    public String nameFromLocale(@Nonnull String localeKey) {
        var builder = new StringBuilder();
        builder.append(FOLDER_NAME);
        var parts = localeKey.split(LOCALE_NAME_SEPARATOR);
        if (parts.length == 1 && isLanguageValid(parts[0])) { // language only
            builder.append("-")
                    .append(parts[0].toLowerCase());
        } else if (parts.length == 2 && isLanguageValid(parts[0]) && isCountryValid(parts[1])) { // language and country
            builder.append("-")
                    .append(parts[0].toLowerCase()).append("-r").append(parts[1].toUpperCase());
        } else { // label
            builder.append(localeKey);
        }
        return builder.toString();
    }
}
