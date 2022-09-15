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

import javax.annotation.Nonnull;

/**
 * Base converter.
 *
 * @param <T> type of data.
 */
public interface BaseConverter<T> {
    /**
     * Unique command from this converter.
     * @return command
     */
    @Nonnull
    String getCommand();

    /**
     * Current formatter.<br/>
     * To create data from a list of items
     *
     * @return formatter.
     */
    @Nonnull
    BaseFormatter<T> getFormatter();

    /**
     * Current parser.<br/>
     * Parse data into list of the items.
     *
     * @return parser.
     */
    @Nonnull
    BaseParser<T> getParser();

    /**
     * Base class to read data from files.
     *
     * @return reader instance or null
     */

    @Nonnull
    BaseFileReader<T> getFileReader();

    /**
     * Base class to write data to files.
     *
     * @return writer instance
     */
    @Nonnull
    BaseFileWriter<T> getFileWriter();

}
