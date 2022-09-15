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

import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Converter for xls.
 */
final class XlsConverter extends BaseConverterImpl<Workbook> {
    /**
     * Constructor.
     *
     * @param formatter default formatter
     * @param parser    default parser
     * @param reader    file reader
     * @param writer    file writer
     */
    @Inject
    XlsConverter(@Nonnull XLSFormatter formatter,
                 @Nonnull XLSParser parser,
                 @Nonnull XLSFileReader reader,
                 @Nonnull XLSFileWriter writer) {
        super(formatter, parser, reader, writer);
    }

    @Nonnull
    @Override
    public String getCommand() {
        return "xls";
    }
}
