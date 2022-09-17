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
        var row = sheet.createRow(1);
        var idCell = row.createCell(0);
        idCell.setCellValue("simple_string");
        var valueCell = row.createCell(1);
        valueCell.setCellValue("SimplE STring");


        var xlsParser = new XLSParser();
        var items = xlsParser.parse(workbook).get(0).getItems();

        assertEquals(ItemType.STRING, items.get(0).getType());
        assertEquals("simple_string", ((StringItem) items.get(0)).getId());
        assertEquals("SimplE STring", ((StringItem) items.get(0)).getValue());
    }
}
