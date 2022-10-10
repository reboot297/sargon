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
import com.reboot297.sargon.model.ItemType;
import com.reboot297.sargon.model.StringItem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static com.reboot297.sargon.converter.XlsLocaleManager.DEFAULT_LOCALE_NAME;

/**
 * Formatter for XLS files.
 */
@Singleton
final class XLSFormatter implements BaseFormatter<List<BaseItem>, Workbook> {


    /**
     * The width for index column.
     */
    private static final int COLUMN_WIDTH_ID = 6000;
    /**
     * The width for value columns.
     */
    private static final int COLUMN_WIDTH_VALUE = 1000;

    /**
     * Name for column with id item.
     */
    private static final String COLUMN_NAME_ID = "ID";

    /**
     * Style for header cell.
     */
    private CellStyle headerStyle;

    /**
     * Style for cells.
     */
    private CellStyle cellStyle;

    @Inject
    XLSFormatter() {

    }


    /**
     * Create table.
     *
     * @return workbook
     */

    @Nonnull
    public Workbook createTable() {

        var workBook = new XSSFWorkbook();
        headerStyle = workBook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workBook.createFont();
        font.setFontName("Arial");
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints(XLSUtils.HEADER_FONT_HEIGHT);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        cellStyle = workBook.createCellStyle();
        cellStyle.setWrapText(true);
        return workBook;
    }

    /**
     * Convert list of localized strings into the table.
     *
     * @param items source data
     * @return workbook
     */
    @Nonnull
    @Override
    public Workbook format(@Nonnull List<BaseItem> items) {

        var workbook = createTable();
        var sheet = workbook.createSheet(XLSUtils.DEFAULT_SHEET_NAME);
        sheet.setDefaultColumnWidth(COLUMN_WIDTH_VALUE);
        sheet.setColumnWidth(XLSUtils.INDEX_COLUMN_ID, COLUMN_WIDTH_ID);

        //set header for id column
        var headerRow = sheet.createRow(XLSUtils.INDEX_ROW_HEADER);
        addHeaderCell(headerRow, XLSUtils.INDEX_COLUMN_ID, COLUMN_NAME_ID);
        addHeaderCell(headerRow, XLSUtils.INDEX_COLUMN_DEFAULT_VALUE, DEFAULT_LOCALE_NAME);

        Row row;
        for (int i = 0; i < items.size(); i++) {
            row = sheet.createRow(XLSUtils.INDEX_ROW_DATA_START + i);
            var item = items.get(i);
            if (item.getType() == ItemType.STRING) {
                //set id
                var idCell = row.createCell(XLSUtils.INDEX_COLUMN_ID);
                idCell.setCellStyle(cellStyle);
                idCell.setCellValue(((StringItem) item).getId());
                //set values
                var values = ((StringItem) item).getValues();
                for (var key : values.keySet()) {
                    var columnId = getOrCreateColumn(headerRow, key);
                    var cell = row.createCell(columnId);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(values.get(key));
                }
            }
        }

        return workbook;
    }


    @Nonnull
    private int getOrCreateColumn(@Nonnull Row headerRow, @Nonnull String key) {
        if (key.isEmpty()) {
            key = DEFAULT_LOCALE_NAME;
        }

        var columnCount = headerRow.getLastCellNum();
        for (int i = XLSUtils.INDEX_COLUMN_DEFAULT_VALUE; i < columnCount; i++) {
            if (headerRow.getCell(i).getStringCellValue().equals(key)) { // TODO check NPE
                return i;
            }
        }

        addHeaderCell(headerRow, columnCount, key);
        return columnCount;
    }

    /**
     * Add cell to header row.
     *
     * @param header   header row
     * @param columnId column id
     * @param value    cell value
     */
    private void addHeaderCell(@Nonnull Row header, int columnId, @Nonnull String value) {
        var headerCell = header.createCell(columnId);
        headerCell.setCellValue(value);
        headerCell.setCellStyle(headerStyle);
    }
}
