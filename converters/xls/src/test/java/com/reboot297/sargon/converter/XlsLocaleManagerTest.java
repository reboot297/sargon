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

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * Test cases for xls locale manager.
 */
public class XlsLocaleManagerTest {

    private final BaseLocaleManager localeManager = new XlsLocaleManager();

    /**
     * Extract locale key from default column name.
     */
    @Test
    public void testExtractDefaultLocaleLocale() {
        var source = "Default";
        var locale = localeManager.extractLocale(source);
        assertEquals("", locale);;
    }

    /**
     * Extract locale key from column name with language.
     */
    @Test
    public void testExtractLanguage() {
        assertEquals("en", localeManager.extractLocale("en"));
        assertEquals("en", localeManager.extractLocale("EN"));
        assertEquals("en", localeManager.extractLocale("eN"));
        assertEquals("en", localeManager.extractLocale("En"));
    }

    /**
     * Extract locale key from column name with language and country.
     */
    @Test
    public void testExtractLanguageAndCountry() {
        assertEquals("en_US", localeManager.extractLocale("en_us"));
        assertEquals("en_US", localeManager.extractLocale("en_US"));
        assertEquals("en_US", localeManager.extractLocale("EN_US"));
        assertEquals("en_US", localeManager.extractLocale("EN_us"));
    }

    /**
     * Extract locale key from empty column name.
     */
    @Test
    public void testExtractLocaleFromEmptyName() {
        assertEquals("", localeManager.extractLocale(""));
    }

    /**
     * Extract locale key from wrong column name.
     */
    @Test
    public void testExtractFromWrongName() {
        assertEquals("", localeManager.extractLocale("en-us"));
    }

    /**
     * Create name from empty locale
     */
    @Test
    public void testCreateNameFromDefaultLocale() {
        assertEquals("Default", localeManager.nameFromLocale(""));
    }

    /**
     * Create name from locale with language only
     */
    @Test
    public void testCreateNameFromLocaleWithLanguage() {
        assertEquals("en", localeManager.nameFromLocale("en"));
    }

    /**
     * Create name from locale with language and country.
     */
    @Test
    public void testCreateNameFromLocaleWithLanguageAndCountry() {
        assertEquals("en_US", localeManager.nameFromLocale("en_US"));
    }
}
