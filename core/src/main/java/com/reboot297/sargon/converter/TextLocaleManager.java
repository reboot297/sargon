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

import com.reboot297.sargon.model.LocalePath;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Interface help to extract locale from file path.
 */
interface TextLocaleManager extends BaseLocaleManager {

    /**
     * Get path to local file.
     *
     * @param rootFolder root folder
     * @param localeKey     locale key
     * @return path
     */
    @Nonnull
    String pathToLocaleFile(@Nonnull String rootFolder, @Nonnull String localeKey);

    /**
     * Find list of files in root directory.
     *
     * @param rootFolder root folder
     * @return list of object with path to files
     */
    @Nonnull
    List<LocalePath> findFiles(@Nonnull String rootFolder);
}
