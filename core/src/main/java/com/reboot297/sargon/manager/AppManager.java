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

package com.reboot297.sargon.manager;

import com.reboot297.sargon.converter.BaseConverter;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Manager to work with converters.
 */
public interface AppManager {
    /**
     * Convert string resources.
     *
     * @param from converter identifier.
     * @param to   converter identifier.
     * @return true if success.
     */
    boolean convert(String from, String to);

    /**
     * Convert string resources.
     *
     * @param from            converter identifier.
     * @param to              converter identifier.
     * @param sourcePath      path to source file.
     * @param destinationPath path to destination file.
     * @return true if success.
     */
    boolean convert(@Nonnull String from, @Nonnull String to,
                    @Nonnull String sourcePath, @Nonnull String destinationPath);

    /**
     * Get Set of available ids.
     *
     * @return list of strings.
     */
    Set<String> getAvailableIds();

    /**
     * Add Converter.
     *
     * @param converter converter
     */
    void addConverter(@Nonnull BaseConverter converter);

    /**
     * Generate properties.
     */
    void generateProperties();
}
