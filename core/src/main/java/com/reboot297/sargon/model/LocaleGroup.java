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

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Locale;

/**
 * Class contains list items for specific locale.
 */
public final class LocaleGroup {

    /**
     * Locale Id.
     */
    private final Locale locale;

    /**
     * List of items.
     */
    private final List<BaseItem> items;

    /**
     * Default constructor.
     *
     * @param locale locale
     * @param items  list of the items
     */
    public LocaleGroup(@Nonnull Locale locale, @Nonnull List<BaseItem> items) {
        this.locale = locale;
        this.items = items;
    }

    /**
     * Get locale.
     *
     * @return Locale object
     */
    @Nonnull
    public Locale getLocale() {
        return locale;
    }

    /**
     * Get List of BaseItem.
     *
     * @return list items.
     */
    @Nonnull
    public List<BaseItem> getItems() {
        return items;
    }
}
