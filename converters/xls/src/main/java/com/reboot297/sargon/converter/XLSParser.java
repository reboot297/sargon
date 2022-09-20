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
import com.reboot297.sargon.model.LocaleGroup;
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
final class XLSParser implements BaseParser<Workbook, List<LocaleGroup>> {

    /**
     * Locale manager instance.
     */
    private final BaseLocaleManager localeManager;

    /**
     * Default constructor.
     *
     * @param localeManager locale manager.
     */
    @Inject
    XLSParser(@Nonnull XlsLocaleManager localeManager) {
        this.localeManager = localeManager;
    }

    @Nullable
    @Override
    public List<LocaleGroup> parse(@Nonnull Workbook source) {
        var sheet = source.getSheetAt(0);
        var localeItems = new ArrayList<LocaleGroup>();
        var headerRow = sheet.getRow(0);
        for (int i = 1; headerRow.getCell(i) != null; i++) {
            var localeName = headerRow.getCell(i).getStringCellValue();
            var locale = localeManager.extractLocale(localeName);
            localeItems.add(new LocaleGroup(locale, new ArrayList<>()));
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            var row = sheet.getRow(i);
            if (row != null) {
                var cellId = row.getCell(INDEX_COLUMN_ID);
                if (cellId != null) {
                    for (int j = INDEX_COLUMN_DEFAULT_VALUE; j < row.getLastCellNum(); j++) {
                        localeItems.get(j - 1).getItems().add(
                                new StringItem(cellId.getStringCellValue(), row.getCell(j).getStringCellValue()));
                    }
                }
            }
        }
        return localeItems;
    }
}
