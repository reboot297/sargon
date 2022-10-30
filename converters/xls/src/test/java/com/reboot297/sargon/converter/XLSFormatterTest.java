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
import com.reboot297.sargon.model.StringItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Test cases for XLS formatter.
 */
public class XLSFormatterTest {

    /**
     * Creating workbook from list of items.
     */
    @Test
    public void testFormatXlsString() {

        var formatter = new XLSFormatter();
        var items = new ArrayList<BaseItem>();
        var values = new LinkedHashMap<String, String>();
        values.put("", "Simple String");
        values.put("en", "Simple En String");
        values.put("en_US", "Simple En_US String");
        items.add(new StringItem("simple string", values));

        var workbook = formatter.format(items);

        var sheet = workbook.getSheet("Default Strings");
        var headerRow = sheet.getRow(0);
        assertEquals("ID", headerRow.getCell(0).getStringCellValue());
        assertEquals("Default", headerRow.getCell(1).getStringCellValue());
        assertEquals("en", headerRow.getCell(2).getStringCellValue());
        assertEquals("en_US", headerRow.getCell(3).getStringCellValue());

        var dataRow = sheet.getRow(1);
        assertEquals("simple string", dataRow.getCell(0).getStringCellValue());
        assertEquals("Simple String", dataRow.getCell(1).getStringCellValue());
        assertEquals("Simple En String", dataRow.getCell(2).getStringCellValue());
        assertEquals("Simple En_US String", dataRow.getCell(3).getStringCellValue());
    }
}
