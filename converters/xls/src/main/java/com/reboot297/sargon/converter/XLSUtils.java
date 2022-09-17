/*
 * Copyright (c) 2022. Viktor Pop
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.reboot297.sargon.converter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.Nonnull;

/**
 * Constants for parsing/formatting XLS tables.
 */
final class XLSUtils {

    /**
     * Font height in points.
     */
    static final short HEADER_FONT_HEIGHT = 12;
    /**
     * Name of the main sheet.
     */
    static final String DEFAULT_SHEET_NAME = "Default Strings";
    /**
     * To mark comments in table we always use id = "//comment_id".
     */
    static final String COMMENT_ID = "//comment";
    /**
     * Invalid index.
     */
    static final int INDEX_INVALID = -1;
    /**
     * Index of header row. It is 0 by default but could be changed, if you need to.
     */
    static final int INDEX_ROW_HEADER = 0;
    /**
     * Index of the first row after the header.
     */
    static final int INDEX_ROW_DATA_START = 1;
    /**
     * The first two column reserved for ID and default value. The next ones for translations.
     */
    static final int INDEX_COLUMN_TRANSLATIONS_START = 2;
    /**
     * The first column is reserved for item id.
     */
    static final int INDEX_COLUMN_ID = 0;
    /**
     * Second column is reserved for default value.
     */
    static final int INDEX_COLUMN_DEFAULT_VALUE = 1;

    /**
     * Get header row.
     *
     * @param sheet sheet
     * @return header row
     */
    @Nonnull
    static Row getHeaderRow(@Nonnull Sheet sheet) {
        return sheet.getRow(XLSUtils.INDEX_ROW_HEADER);
    }

    /**
     * Get default sheet.
     *
     * @param workbook workbook
     * @return sheet or null
     */
    @Nonnull
    static Sheet getDefaultSheet(@Nonnull Workbook workbook) {
        return workbook.getSheet(XLSUtils.DEFAULT_SHEET_NAME);
    }

    private XLSUtils() {

    }
}
