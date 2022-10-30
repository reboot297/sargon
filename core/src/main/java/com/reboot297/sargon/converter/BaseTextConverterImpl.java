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
import java.util.Map;

/**
 * Base implementation for text convertors.
 *
 * @param <R> type of remote data
 */
abstract class BaseTextConverterImpl<R> extends BaseConverterImpl<Map<String, R>, List<BaseItem>> {
    /**
     * Constructor.
     *
     * @param formatter         default formatter
     * @param parser            default parser
     * @param reader            file reader
     * @param writer            file writer
     * @param localeManager     locale manager
     * @param propertiesManager properties manager
     */
    BaseTextConverterImpl(@Nonnull BaseFormatter<List<BaseItem>, Map<String, R>> formatter,
                          @Nonnull BaseParser<Map<String, R>, List<BaseItem>> parser,
                          @Nonnull BaseFileReader<Map<String, R>> reader,
                          @Nonnull BaseFileWriter<Map<String, R>> writer,
                          @Nonnull TextLocaleManager localeManager,
                          @Nonnull PropertiesManager propertiesManager) {
        super(formatter, parser, reader, writer, localeManager, propertiesManager);
    }

    @Override
    public boolean isTable() {
        return false;
    }

    /**
     * Get Locale manager for text converters.
     *
     * @return locale manager
     */
    @Nonnull
    @Override
    public TextLocaleManager getLocaleManager() {
        return (TextLocaleManager) super.getLocaleManager();
    }
}
