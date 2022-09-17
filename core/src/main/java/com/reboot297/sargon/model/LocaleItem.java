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

package com.reboot297.sargon.model;

import java.util.List;

/**
 * Class contains list items for specific locale.
 */
public final class LocaleItem {

    /**
     * The name for default locale.
     */
    public static final String DEFAULT_LOCALE_VALUE_NAME = "Default";

    /**
     * Locale Id.
     */
    private final String id;

    /**
     * List of items.
     */
    private final List<BaseItem> items;

    /**
     * Default constructor.
     *
     * @param id id of locale
     * @param items list of the items
     */
    public LocaleItem(String id, List<BaseItem> items) {
        this.id = id;
        this.items = items;
    }

    /**
     * Get locale id.
     *
     * @return string value
     */
    public String getId() {
        return id;
    }

    /**
     * Get List of BaseItem.
     *
     * @return list items.
     */
    public List<BaseItem> getItems() {
        return items;
    }
}
