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

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Locale Manager for XLS files.
 */
@Singleton
final class XlsLocaleManager extends BaseLocaleManagerImpl {

    /**
     * Name for default locale column.
     */
    static final String DEFAULT_LOCALE_NAME = "Default";

    /**
     * Default constructor.
     */
    @Inject
    XlsLocaleManager() {

    }

    /**
     * Extract locale from column name.
     *
     * @param text column name
     * @return locale key
     */
    @Nonnull
    @Override
    public String extractLocale(@Nonnull String text) {
        var language = "";
        var country = "";

        if (!text.equals(DEFAULT_LOCALE_NAME)) {
            var values = text.split(LOCALE_NAME_SEPARATOR);
            if (values[0].matches("[a-zA-Z]+")) {
                language = values[0];
                if (values.length > 1) {
                    country = values[1];
                    return language.toLowerCase() + "_" + country.toUpperCase();
                }
                return language.toLowerCase();
            }
        }
        return "";
    }

    /**
     * Create column name from locale key.
     *
     * @param localeKey locale key
     * @return column name
     */
    @Nonnull
    @Override
    public String nameFromLocale(@Nonnull String localeKey) {
        if (localeKey.isEmpty()) {
            return DEFAULT_LOCALE_NAME;
        }

        var parts = localeKey.split(LOCALE_NAME_SEPARATOR);
        if (parts.length == 1 && isLanguageValid(parts[0])) {
            return parts[0].toLowerCase();
        } else if (parts.length == 2 && isLanguageValid(parts[0]) && isCountryValid(parts[1])) {
            return parts[0].toLowerCase() + LOCALE_NAME_SEPARATOR + parts[1].toUpperCase();
        } else {
            return localeKey;
        }
    }
}
