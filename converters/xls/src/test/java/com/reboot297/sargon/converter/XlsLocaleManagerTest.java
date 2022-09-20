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
     * Extract locale object from default column name.
     */
    @Test
    public void testExtractDefaultLocaleLocale() {
        var source = "Default";
        var locale = localeManager.extractLocale(source);
        assertEquals("", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    /**
     * Extract locale object from column name with language.
     */
    @Test
    public void testExtractLanguage() {
        var source = "en";
        var locale = localeManager.extractLocale(source);
        assertEquals("en", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    /**
     * Extract locale object from column name with language and country.
     */
    @Test
    public void testExtractLanguageAndCountry() {
        var source = "en_US";
        var locale = localeManager.extractLocale(source);
        assertEquals("en", locale.getLanguage());
        assertEquals("US", locale.getCountry());
    }

    /**
     * Extract locale object from empty column name.
     */
    @Test
    public void testExtractLocaleFromEmptyName() {
        var source = "";
        var locale = localeManager.extractLocale(source);
        assertEquals("", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    /**
     * Extract locale object from wrong column name.
     */
    @Test
    public void testExtractFromWrongName() {
        var source = "en-us";
        var locale = localeManager.extractLocale(source);
        assertEquals("", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    /**
     * Create name from empty locale/
     */
    @Test
    public void testCreateNameFromDefaultLocale() {
        assertEquals("Default", localeManager.nameFromLocale(new Locale("", "")));
    }

    /**
     * Create name from locale with language only
     */
    @Test
    public void testCreateNameFromLocaleWithLanguage() {
        assertEquals("en", localeManager.nameFromLocale(new Locale("EN", "")));
    }

    /**
     * Create name from locale with language and country.
     */
    @Test
    public void testCreateNameFromLocaleWithLanguageAndCountry() {
        assertEquals("en_US", localeManager.nameFromLocale(new Locale("en", "us")));
    }

    /**
     * Create name fro locale with country only.
     */
    @Test
    public void testCreateNameFromLocaleWithCountry() {
        assertEquals("Default", localeManager.nameFromLocale(new Locale("", "US")));
    }
}
