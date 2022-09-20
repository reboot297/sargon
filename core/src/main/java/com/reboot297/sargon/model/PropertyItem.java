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
import javax.annotation.Nullable;

/**
 * Model describes app properties item.
 */
public final class PropertyItem {
    /**
     * Key.
     */
    private final String key;
    /**
     * Value.
     */
    private final String value;
    /**
     * Comment.
     */
    private final String comment;

    /**
     * Default constructor.
     *
     * @param key     key
     * @param value   value
     * @param comment comment
     */
    public PropertyItem(@Nonnull String key, @Nullable String value, @Nullable String comment) {
        this.key = key;
        this.value = value;
        this.comment = comment;
    }

    /**
     * Get key unique of the property.
     *
     * @return string value
     */
    @Nonnull
    public String getKey() {
        return key;
    }

    /**
     * Value of the property.
     *
     * @return value or null
     */
    @Nullable
    public String getValue() {
        return value;
    }

    /**
     * Get comment for the property.
     *
     * @return string value or null
     */
    @Nullable
    public String getComment() {
        return comment;
    }
}
