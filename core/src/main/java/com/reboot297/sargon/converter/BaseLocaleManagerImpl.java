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

import javax.annotation.Nullable;

abstract class BaseLocaleManagerImpl implements BaseLocaleManager {

    /**
     * Language length is 2 character.
     */
    private static final int LANGUAGE_LENGTH = 2;

    /**
     * In most case country code contains 2 character.
     */
    private static final int COUNTRY_LENGTH = 2;

    /**
     * Sometimes country code contains 3 letters.
     */
    private static final int COUNTRY_MAX_LENGTH = 3;

    /**
     * Separator in locale name.
     */
    static final String LOCALE_NAME_SEPARATOR = "_";

    boolean isLanguageValid(@Nullable String language) {
        return language != null && language.length() == LANGUAGE_LENGTH;
    }

    boolean isCountryValid(@Nullable String country) {
        return country != null && (country.length() == COUNTRY_LENGTH || country.length() == COUNTRY_MAX_LENGTH);
    }

}
