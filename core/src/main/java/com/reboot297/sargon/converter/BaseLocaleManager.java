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
import java.util.Locale;

/**
 * Base interface to work with locales.
 */
interface BaseLocaleManager {

    /**
     * Extract locale object from name of the file or column name of table.
     *
     * @param text name
     * @return Locale object
     */
    @Nonnull
    Locale extractLocale(@Nonnull String text);

    /**
     * Generate name from locale object.
     *
     * @param locale locale
     * @return name.
     */
    @Nonnull
    String nameFromLocale(@Nonnull Locale locale);
}
