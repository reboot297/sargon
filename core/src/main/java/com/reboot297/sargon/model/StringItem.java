/*
 * Copyright (c) 2021-2022. Viktor Pop
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.reboot297.sargon.model;

import java.util.Map;

/**
 * Item with string values.
 */
public final class StringItem extends BaseItem {
    /**
     * ID of the item<br/>
     * Should be unique for all data.
     */
    private final String id;
    /**
     * Map<{key}, {value}> of values for different locales.
     * The key can be represented as column in the table.
     * The keys may be two types - locale or label.
     * locale-keys should be supported by all platforms and may be converted to Locale object.
     * labels is a simple string values. Usually they are used for a specific platforms and ignored by others.
     *
     * Samples of the key:
     * "" - default locale,
     * xx - two letters in lover case, locale for specific language
     * xx_XX - locale for specific language and country
     * other cases will be interpreted like labels, for example:
     * "hdpi-en-rBR-night" - label, contains path to the folder for android platform.
     */
    private final Map<String, String> values;

    /**
     * Constructor.
     *
     * @param id     id of string item
     * @param values map of values for different locales.
     */
    public StringItem(String id, Map<String, String> values) {
        this.id = id;
        this.values = values;
    }

    /**
     * Get id.
     *
     * @return string value
     */
    public String getId() {
        return id;
    }

    /**
     * Get values for different locales.
     *
     * @return map of the values
     */
    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public ItemType getType() {
        return ItemType.STRING;
    }
}
