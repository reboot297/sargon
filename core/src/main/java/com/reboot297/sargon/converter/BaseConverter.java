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
 * @param <R> type of remote data - string, table...
 * @param <L> type of local data - list of items or one item
 */
public interface BaseConverter<R, L> {
    /**
     * Unique command from this converter.
     *
     * @return command
     */
    @Nonnull
    String getCommand();

    /**
     * Check if supports many locales in one file.
     *
     * @return true if it is the converter for the table.
     */
    boolean isTable();

    /**
     * Current formatter.<br/>
     * To create data from a list of items
     *
     * @return formatter.
     */
    @Nonnull
    BaseFormatter<L, R> getFormatter();

    /**
     * Current parser.<br/>
     * Parse data into list of the items.
     *
     * @return parser.
     */
    @Nonnull
    BaseParser<R, L> getParser();

    /**
     * Base class to read data from files.
     *
     * @return reader instance or null
     */

    @Nonnull
    BaseFileReader<R> getFileReader();

    /**
     * Base class to write data to files.
     *
     * @return writer instance
     */
    @Nonnull
    BaseFileWriter<R> getFileWriter();

}
