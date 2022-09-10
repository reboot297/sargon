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

/**
 * Item with one string.
 */
public final class StringItem extends BaseItem {
    /**
     * ID of the item<br/>
     * Should be unique for locale.
     */
    private final String id;
    /**
     * Item Value.
     */
    private final String value;

    /**
     * Constructor.
     * @param id id of string item
     * @param value string value
     */
    public StringItem(String id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Get id.
     * @return string value
     */
    public String getId() {
        return id;
    }

    /**
     * Get value.
     * @return value
     */
    public String getValue() {
        return value;
    }

    @Override
    public ItemType getType() {
        return ItemType.STRING;
    }
}
