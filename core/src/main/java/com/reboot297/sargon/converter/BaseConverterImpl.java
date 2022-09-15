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
 * @param <T> type of data.
 */
abstract class BaseConverterImpl<T> implements BaseConverter<T> {
    /**
     * Formatter instance.
     */
    private final BaseFormatter<T> formatter;

    /**
     * Parser instance.
     */
    private final BaseParser<T> parser;

    /**
     * File reader instance.
     */
    private final BaseFileReader<T> fileReader;

    /**
     * File writer instance.
     */
    private final BaseFileWriter<T> fileWriter;

    /**
     * Constructor.
     *
     * @param formatter default formatter
     * @param parser default parser
     * @param reader file reader
     * @param writer file writer
     */
    BaseConverterImpl(@Nonnull BaseFormatter<T> formatter,
                      @Nonnull BaseParser<T> parser,
                      @Nonnull BaseFileReader<T> reader,
                      @Nonnull BaseFileWriter<T> writer) {
        this.formatter = formatter;
        this.parser = parser;
        this.fileReader = reader;
        this.fileWriter = writer;
    }

    @Nonnull
    @Override
    public BaseFormatter<T> getFormatter() {
        return formatter;
    }

    @Nonnull
    @Override
    public BaseParser<T> getParser() {
        return parser;
    }

    @Nonnull
    @Override
    public BaseFileReader<T> getFileReader() {
        return fileReader;
    }

    @Nonnull
    @Override
    public BaseFileWriter<T> getFileWriter() {
        return fileWriter;
    }
}
