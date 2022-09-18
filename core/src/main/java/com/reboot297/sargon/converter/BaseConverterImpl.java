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
 * Base implementation for all convertors.
 *
 * @param <R> type of remote data - string, table...
 * @param <L> type of local data - list of items or one item
 */
abstract class BaseConverterImpl<R, L> implements BaseConverter<R, L> {
    /**
     * Formatter instance.
     */
    private final BaseFormatter<L, R> formatter;

    /**
     * Parser instance.
     */
    private final BaseParser<R, L> parser;

    /**
     * File reader instance.
     */
    private final BaseFileReader<R> fileReader;

    /**
     * File writer instance.
     */
    private final BaseFileWriter<R> fileWriter;

    /**
     * Manager for locales.
     */
    private final BaseLocaleManager localeManager;

    /**
     * Constructor.
     *
     * @param formatter     default formatter
     * @param parser        default parser
     * @param reader        file reader
     * @param writer        file writer
     * @param localeManager localeManager
     */
    BaseConverterImpl(@Nonnull BaseFormatter<L, R> formatter,
                      @Nonnull BaseParser<R, L> parser,
                      @Nonnull BaseFileReader<R> reader,
                      @Nonnull BaseFileWriter<R> writer,
                      @Nonnull BaseLocaleManager localeManager) {
        this.formatter = formatter;
        this.parser = parser;
        this.fileReader = reader;
        this.fileWriter = writer;
        this.localeManager = localeManager;
    }

    @Nonnull
    @Override
    public BaseFormatter<L, R> getFormatter() {
        return formatter;
    }

    @Nonnull
    @Override
    public BaseParser<R, L> getParser() {
        return parser;
    }

    @Nonnull
    @Override
    public BaseFileReader<R> getFileReader() {
        return fileReader;
    }

    @Nonnull
    @Override
    public BaseFileWriter<R> getFileWriter() {
        return fileWriter;
    }

    @Nonnull
    @Override
    public BaseLocaleManager getLocaleManager() {
        return localeManager;
    }
}
