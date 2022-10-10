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

import com.reboot297.sargon.model.BaseItem;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Base implementation of converter for the tables.
 *
 * @param <R> - type of remote data
 */
abstract class BaseTableConverterImpl<R> extends BaseConverterImpl<R, List<BaseItem>> {
    /**
     * Constructor.
     *
     * @param formatter         default formatter
     * @param parser            default parser
     * @param reader            file reader
     * @param writer            file writer
     * @param localeManager     localeManager
     * @param propertiesManager properties manager
     */
    BaseTableConverterImpl(@Nonnull BaseFormatter<List<BaseItem>, R> formatter,
                           @Nonnull BaseParser<R, List<BaseItem>> parser,
                           @Nonnull BaseFileReader<R> reader,
                           @Nonnull BaseFileWriter<R> writer,
                           @Nonnull BaseLocaleManager localeManager,
                           @Nonnull PropertiesManager propertiesManager) {
        super(formatter, parser, reader, writer, localeManager, propertiesManager);
    }

    @Override
    public boolean isTable() {
        return true;
    }
}
