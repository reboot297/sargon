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

/**
 * Contains info about path to locale.
 */
public final class LocalePath {
    /**
     * Name of the locale.
     */
    private final String localeKey;

    /**
     * Path to the locale.
     */
    private final String localePath;

    /**
     * Default constructor.
     *
     * @param localeKey  locale key
     * @param localePath path to the file
     */
    public LocalePath(@Nonnull String localeKey, @Nonnull String localePath) {
        this.localeKey = localeKey;
        this.localePath = localePath;
    }

    /**
     * Get path to the file.
     *
     * @return string value
     */
    @Nonnull
    public String getLocalePath() {
        return localePath;
    }

    /**
     * Get locale key.
     *
     * @return locale key
     */
    @Nonnull
    public String getLocaleKey() {
        return localeKey;
    }
}
