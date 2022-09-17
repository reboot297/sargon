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
import com.reboot297.sargon.model.LocaleItem;
import com.reboot297.sargon.model.StringItem;
import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * XLS Parser.
 */
final class XLSParser implements BaseParser<Workbook, List<LocaleItem>> {
    @Inject
    XLSParser() {

    }

    @Nullable
    @Override
    public List<LocaleItem> parse(@Nonnull Workbook source) {
        var items = new ArrayList<BaseItem>();
        var sheet = source.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            var row = sheet.getRow(i);
            if (row != null) {
                items.add(new StringItem(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue()));
            }
        }
        var localeItem = new LocaleItem("", items);
        return List.of(localeItem);
    }
}
