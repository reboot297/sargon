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

import java.io.File;
import java.util.Locale;

/**
 * Test cases for android locale manager.
 */
public class AndroidLocaleManagerTest {

    /**
     * Locale manager instance.
     */
    private final TextLocaleManager localeManager = new AndroidLocaleManager();

    /**
     * Extract locale from default android folder name.
     */
    @Test
    public void testExtractDefaultLocale() {
        var folderName = "values";
        var locale = localeManager.extractLocale(folderName);
        assertEquals("", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    /**
     * Extract locale from resources for specific language.
     */
    @Test
    public void testExtractLocaleWithLanguage() {
        var folderName = "values-en";
        var locale = localeManager.extractLocale(folderName);
        assertEquals("en", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    /**
     * Extract locale from resources for language and country.
     */
    @Test
    public void testExtractLocaleWithLanguageAndCountry() {
        var folderName = "values-en-rUS";
        var locale = localeManager.extractLocale(folderName);
        assertEquals("en", locale.getLanguage());
        assertEquals("US", locale.getCountry());
    }

    /**
     * Extract locale from wrong folder.
     */
    @Test
    public void testExtractLocaleFromWrongFolder() {
        var folderName = "val";
        var locale = localeManager.extractLocale(folderName);
        assertEquals("", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    /**
     * Create name for default locale.
     */
    @Test
    public void testCreateNameForDefaultLocale() {
        assertEquals("values", localeManager.nameFromLocale(new Locale("", "")));
    }

    /**
     * Create name for locale with language only.
     */
    @Test
    public void testCreateNameForLocaleWithLanguage() {
        assertEquals("values-en", localeManager.nameFromLocale(new Locale("EN", "")));
    }

    /**
     * Create name for language nad country.
     */
    @Test
    public void testCreateNameForLocaleWithLanguageAndCountry() {
        assertEquals("values-en-rUS", localeManager.nameFromLocale(new Locale("EN", "us")));
    }

    /**
     * Create name for locale with country only.
     */
    @Test
    public void testCreateNameForLocaleWithCountry() {
        assertEquals("values", localeManager.nameFromLocale(new Locale("", "us")));
    }

    /**
     * Creating path to file with strings.
     */
    @Test
    public void testCreatePathToDefaultLocale() {
        var expected = "app" + File.separator + "res" + File.separator + "values" + File.separator + "strings.xml";
        assertEquals(expected, localeManager.pathToLocaleFile("app" + File.separator + "res", new Locale("", "")));
    }

}
