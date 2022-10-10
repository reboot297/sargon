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

import static com.reboot297.sargon.converter.XLSUtils.INDEX_COLUMN_DEFAULT_VALUE;
import static com.reboot297.sargon.converter.XLSUtils.INDEX_COLUMN_ID;

import com.reboot297.sargon.model.BaseItem;
import com.reboot297.sargon.model.StringItem;
import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * XLS Parser.
 */
final class XLSParser implements BaseParser<Workbook, List<BaseItem>> {

    /**
     * Default constructor.
     */
    @Inject
    XLSParser() {


    }

    @Nullable
    @Override
    public List<BaseItem> parse(@Nonnull Workbook source) {
        var sheet = source.getSheetAt(0);
        var items = new ArrayList<BaseItem>();
        var headerRow = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            var row = sheet.getRow(i);
            if (row != null) {
                var idCell = row.getCell(INDEX_COLUMN_ID);
                if (idCell == null || idCell.getStringCellValue() == null || idCell.getStringCellValue().isBlank()) {
                    //TODO empty item or comment
                    System.out.println("Empty Item or comment");
                } else {
                    var id = idCell.getStringCellValue();
                    var values = new HashMap<String, String>();
                    for (int j = INDEX_COLUMN_DEFAULT_VALUE; j < row.getLastCellNum(); j++) {
                        var valueCell = row.getCell(j);
                        if (valueCell != null && valueCell.getStringCellValue() != null
                                && !valueCell.getStringCellValue().isBlank()) {
                            var value = valueCell.getStringCellValue();
                            var locale = headerRow.getCell(j).getStringCellValue();
                            if (locale.equals("Default")) {
                                locale = "";
                            }
                            values.put(locale, value);
                        }
                    }
                    items.add(new StringItem(id, values));
                }
            }
        }

        return items;
    }
}
