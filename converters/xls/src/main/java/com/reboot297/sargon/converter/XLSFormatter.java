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
import static com.reboot297.sargon.converter.XLSUtils.INDEX_COLUMN_ID;
import com.reboot297.sargon.model.BaseItem;
import com.reboot297.sargon.model.ItemType;
import com.reboot297.sargon.model.LocaleGroup;
import com.reboot297.sargon.model.StringItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Formatter for XLS files.
 */
final class XLSFormatter implements BaseFormatter<List<LocaleGroup>, Workbook> {


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
    private CellStyle headerStyle;

    /**
     * Style for cells.
     */
    private CellStyle cellStyle;

    /**
     * LocaleManager instance.
     */
    private final BaseLocaleManager localeManager;

    @Inject
    XLSFormatter(@Nonnull XlsLocaleManager localeManager) {
        this.localeManager = localeManager;
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

    /**
     * Convert list of localized strings into the table.
     *
     * @param localeItems source data
     * @return workbook
     */
    @Nonnull
    @Override
    public Workbook format(@Nonnull List<LocaleGroup> localeItems) {

        var workbook = createTable();
        var sheet = workbook.createSheet(XLSUtils.DEFAULT_SHEET_NAME);
        sheet.setColumnWidth(INDEX_COLUMN_ID, COLUMN_WIDTH);
        sheet.setColumnWidth(XLSUtils.INDEX_COLUMN_DEFAULT_VALUE, COLUMN_WIDTH);

        addHeaderRow(sheet, localeItems);
        var defaultItems = localeItems.get(0).getItems();
        for (int i = 0; i < defaultItems.size(); i++) {
            if (defaultItems.get(i).getType() != ItemType.EMPTY) {
                addRow(getRowValues(i, localeItems), sheet.createRow(i + 1));
            }
        }

        return workbook;
    }

    /**
     * Get values for the row.
     *
     * @param rowPosition position in table
     * @param localeItems locales
     * @return list of base items
     */
    @Nonnull
    private static List<BaseItem> getRowValues(int rowPosition, @Nonnull List<LocaleGroup> localeItems) {
        return localeItems.stream()
                .map(LocaleGroup::getItems)
                .map(list -> list.get(rowPosition))
                .collect(Collectors.toList());
    }

    /**
     * Add header row.
     *
     * @param sheet       sheet
     * @param localeItems list of locale items
     */
    private void addHeaderRow(@Nonnull Sheet sheet, @Nonnull List<LocaleGroup> localeItems) {
        var headerRow = sheet.createRow(XLSUtils.INDEX_ROW_HEADER);
        addHeaderCell(headerRow, INDEX_COLUMN_ID, COLUMN_NAME_ID);

        for (int i = 0; i < localeItems.size(); i++) {
            var name = localeManager.nameFromLocale(localeItems.get(i).getLocale());
            addHeaderCell(headerRow, i + 1, name);
        }
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
     * Add cells with id and values.
     *
     * @param items items
     * @param row   row
     */
    private void addRow(List<BaseItem> items, Row row) {
        Cell cell;
        if (items.get(INDEX_COLUMN_ID).getType() == ItemType.STRING) {
            cell = row.createCell(INDEX_COLUMN_ID);
            cell.setCellValue(((StringItem) items.get(INDEX_COLUMN_ID)).getId());
            cell.setCellStyle(cellStyle);
        }

        for (int i = 0; i < items.size(); i++) {
            cell = row.createCell(i + 1);
            cell.setCellValue(((StringItem) items.get(i)).getValue());
            cell.setCellStyle(cellStyle);
        }
    }
}
