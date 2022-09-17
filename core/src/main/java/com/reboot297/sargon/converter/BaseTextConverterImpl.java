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

import com.reboot297.sargon.model.LocaleItem;

import javax.annotation.Nonnull;

abstract class BaseTextConverterImpl<R> extends BaseConverterImpl<R, LocaleItem> {
    /**
     * Constructor.
     *
     * @param formatter default formatter
     * @param parser    default parser
     * @param reader    file reader
     * @param writer    file writer
     */
    BaseTextConverterImpl(@Nonnull BaseFormatter<LocaleItem, R> formatter,
                          @Nonnull BaseParser<R, LocaleItem> parser,
                          @Nonnull BaseFileReader<R> reader,
                          @Nonnull BaseFileWriter<R> writer) {
        super(formatter, parser, reader, writer);
    }

    @Override
    public boolean isTable() {
        return false;
    }

    abstract String pathToLocaleFile(@Nonnull String rootFolder, @Nonnull String localeId);

}
