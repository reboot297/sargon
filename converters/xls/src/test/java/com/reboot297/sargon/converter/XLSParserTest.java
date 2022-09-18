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

import com.reboot297.sargon.model.ItemType;
import com.reboot297.sargon.model.StringItem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XLSParserTest {

    @Test
    public void testParsingString() {
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet();

        var headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Default");
        headerRow.createCell(2).setCellValue("en");
        headerRow.createCell(3).setCellValue("en_US");


        var row = sheet.createRow(1);
        row.createCell(0).setCellValue("simple_string");
        row.createCell(1).setCellValue("SimplE STring");
        row.createCell(2).setCellValue("SimplE en STring");
        row.createCell(3).setCellValue("SimplE En-US STring");


        var xlsParser = new XLSParser(new XlsLocaleManager());
        var localeItems = xlsParser.parse(workbook);

        //noinspection ConstantConditions
        var defaultLocale = localeItems.get(0);
        assertEquals("", defaultLocale.getLocale().getLanguage());
        assertEquals("", defaultLocale.getLocale().getCountry());
        var items = defaultLocale.getItems();
        assertEquals(ItemType.STRING, items.get(0).getType());
        assertEquals("simple_string", ((StringItem) items.get(0)).getId());
        assertEquals("SimplE STring", ((StringItem) items.get(0)).getValue());

        var enLocale = localeItems.get(1);
        assertEquals("en", enLocale.getLocale().getLanguage());
        assertEquals("", enLocale.getLocale().getCountry());
        items = enLocale.getItems();
        assertEquals(ItemType.STRING, items.get(0).getType());
        assertEquals("simple_string", ((StringItem) items.get(0)).getId());
        assertEquals("SimplE en STring", ((StringItem) items.get(0)).getValue());

        var enUSLocale = localeItems.get(2);
        assertEquals("en", enUSLocale.getLocale().getLanguage());
        assertEquals("US", enUSLocale.getLocale().getCountry());
        items = enUSLocale.getItems();
        assertEquals(ItemType.STRING, items.get(0).getType());
        assertEquals("simple_string", ((StringItem) items.get(0)).getId());
        assertEquals("SimplE En-US STring", ((StringItem) items.get(0)).getValue());
    }
}
