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

import com.reboot297.sargon.model.PropertyItem;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Manager to work with properties.
 */
public abstract class PropertiesManager {
    /**
     * Set of properties for converter.
     */
    private final Set<PropertyItem> properties = new HashSet<>();

    /**
     * Get all available properties for this converter.
     *
     * @return set of values
     */
    Set<PropertyItem> getAvailableProperties() {
        return properties;
    }

    /**
     * Get unique command name.
     *
     * @return string value
     */
    @Nonnull
    abstract String getCommand();

    /**
     * Add property.
     *
     * @param key     string value
     * @param value   string value
     * @param comment string value
     */
    void addProperty(@Nonnull String key, @Nullable String value, @Nullable String comment) {
        properties.add(new PropertyItem(getCommand() + "." + key, value, comment));
    }

    /**
     * Property that contains input path.
     *
     * @return key of the property
     */
    abstract String getInputKey();

    /**
     * Property that contains output path.
     *
     * @return key of the property
     */
    abstract String getOutputKey();
}
