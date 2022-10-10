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

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("", locale);
    }

    /**
     * Extract locale from resources for specific language.
     */
    @Test
    public void testExtractLocaleWithLanguage() {
        var folderName = "values-en";
        var locale = localeManager.extractLocale(folderName);
        assertEquals("en", locale);
    }

    /**
     * Extract locale from resources for language and country.
     */
    @Test
    public void testExtractLocaleWithLanguageAndCountry() {
        var folderName = "values-en-rUS";
        var locale = localeManager.extractLocale(folderName);
        assertEquals("en_US", locale);
    }

    /**
     * Extract locale from custom name.
     */
    @Test
    public void testExtractLocaleFromWrongFolder() {
        var folderName = "values-hdpi-en-rBR-night";
        var locale = localeManager.extractLocale(folderName);
        assertEquals("hdpi-en-rBR-night", locale);
    }

    /**
     * Create name for default locale.
     */
    @Test
    public void testCreateNameForDefaultLocale() {
        assertEquals("values", localeManager.nameFromLocale(""));
    }

    /**
     * Create name for locale with language only.
     */
    @Test
    public void testCreateNameForLocaleWithLanguage() {
        assertEquals("values-en", localeManager.nameFromLocale("EN"));
        assertEquals("values-en", localeManager.nameFromLocale("en"));
    }

    /**
     * Create name for language nad country.
     */
    @Test
    public void testCreateNameForLocaleWithLanguageAndCountry() {
        assertEquals("values-en-rUS", localeManager.nameFromLocale("EN_US"));
        assertEquals("values-en-rUS", localeManager.nameFromLocale("en_us"));
        assertEquals("values-en-rUS", localeManager.nameFromLocale("EN_us"));
        assertEquals("values-en-rUS", localeManager.nameFromLocale("en_US"));
    }

    /**
     * Creating path to file with strings.
     */
    @Test
    public void testCreatePathToDefaultLocale() {
        var expected = "app" + File.separator + "res" + File.separator + "values" + File.separator + "strings.xml";
        assertEquals(expected, localeManager.pathToLocaleFile("app" + File.separator + "res", ""));
    }

}
