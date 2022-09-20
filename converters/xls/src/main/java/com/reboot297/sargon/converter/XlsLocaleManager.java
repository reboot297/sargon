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
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Locale;
import java.util.Optional;

/**
 * Locale Manager for XLS files.
 */
@Singleton
final class XlsLocaleManager implements BaseLocaleManager {

    /**
     * Name for default locale column.
     */
    private static final String DEFAULT_LOCALE_NAME = "Default";
    /**
     * Separator in locale name.
     */
    private static final String LOCALE_NAME_SEPARATOR = "_";

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
     * @return locale object
     */
    @Nonnull
    @Override
    public Locale extractLocale(@Nonnull String text) {
        var language = "";
        var country = "";

        if (!text.equals(DEFAULT_LOCALE_NAME)) {
            var values = text.split(LOCALE_NAME_SEPARATOR);
            if (values[0].matches("[a-z]+")) {
                language = values[0];
                if (values.length > 1) {
                    country = values[1];
                }
            }
        }
        return new Locale(language, country);
    }

    /**
     * Create column name from locale object.
     *
     * @param locale locale object
     * @return column name
     */
    @Nonnull
    @Override
    public String nameFromLocale(@Nonnull Locale locale) {
        var builder = new StringBuilder();
        getLanguage(locale.getLanguage())
                .ifPresentOrElse(language -> {
                    builder.append(language);
                    getCountry(locale.getCountry()).ifPresent(country ->
                            builder.append(LOCALE_NAME_SEPARATOR).append(country)
                    );
                }, () -> builder.append(DEFAULT_LOCALE_NAME));

        return builder.toString();
    }

    /**
     * Extract language from locale.
     *
     * @param language language value
     * @return optional object
     */
    @Nonnull
    private static Optional<String> getLanguage(@Nullable String language) {
        return Optional.ofNullable(language)
                .filter(it -> !it.isBlank())
                .map(String::toLowerCase);
    }

    /**
     * Extract country from locale.
     *
     * @param country country value
     * @return optional object
     */
    @Nonnull
    private static Optional<String> getCountry(@Nullable String country) {
        return Optional.ofNullable(country)
                .filter(it -> !it.isBlank())
                .map(String::toUpperCase);
    }
}
