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

import static com.reboot297.sargon.converter.XLSUtils.HEADER_FONT_HEIGHT;
import com.reboot297.sargon.model.BaseItem;
import com.reboot297.sargon.model.ItemType;
import com.reboot297.sargon.model.LocaleItem;
import static com.reboot297.sargon.model.LocaleItem.DEFAULT_LOCALE_VALUE_NAME;
import com.reboot297.sargon.model.StringItem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.List;

/**
 * Formatter for XLS files.
 */
final class XLSFormatter implements BaseFormatter<List<LocaleItem>, Workbook> {


    /**
     * Default column width.
     */
    private static final int COLUMN_WIDTH = 6000;

    /**
     * Name for column with id item.
     */
    private static final String COLUMN_NAME_ID = "ID";

    /**
     * Style for header cell.
     */
    @Nonnull
    private CellStyle headerStyle;

    /**
     * Style for cells.
     */
    @Nonnull
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
        font.setFontHeightInPoints(HEADER_FONT_HEIGHT);
        font.setBold(true);
        headerStyle.setFont(font);

        cellStyle = workBook.createCellStyle();
        cellStyle.setWrapText(true);
        return workBook;
    }

    @Nonnull
    @Override
    public Workbook format(@Nonnull List<LocaleItem> localeItems) {

        Workbook workbook = createTable();

        var sheet = workbook.createSheet(XLSUtils.DEFAULT_SHEET_NAME);
        sheet.setColumnWidth(XLSUtils.INDEX_COLUMN_ID, COLUMN_WIDTH);
        sheet.setColumnWidth(XLSUtils.INDEX_COLUMN_DEFAULT_VALUE, COLUMN_WIDTH);

        Row headerRow = sheet.createRow(XLSUtils.INDEX_ROW_HEADER);
        addHeaderCell(headerRow, XLSUtils.INDEX_COLUMN_ID, COLUMN_NAME_ID);
        addHeaderCell(headerRow, XLSUtils.INDEX_COLUMN_DEFAULT_VALUE, DEFAULT_LOCALE_VALUE_NAME);

        var defaultLocaleItem = localeItems.get(0);
        var items = defaultLocaleItem.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getType() != ItemType.EMPTY) {
                addRow(items.get(i), sheet.createRow(i + 1));
            }
        }
        return workbook;
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

    /**
     * Add cells with id and default value.
     *
     * @param item item
     * @param row  row
     */
    private void addRow(BaseItem item, Row row) {
        if (item.getType() == ItemType.STRING) {
            var cell = row.createCell(XLSUtils.INDEX_COLUMN_ID);
            cell.setCellValue(((StringItem) item).getId());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(XLSUtils.INDEX_COLUMN_DEFAULT_VALUE);
            cell.setCellValue(((StringItem) item).getValue());
            cell.setCellStyle(cellStyle);
        }
    }
}
