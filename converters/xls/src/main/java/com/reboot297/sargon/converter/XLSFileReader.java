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

import javax.inject.Singleton;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Reader for the XLS files.
 */
@Singleton
final class XLSFileReader implements BaseFileReader<Workbook> {

    @Inject
    XLSFileReader() {

    }

    /**
     * Open file and read it into workbook object.
     *
     * @param fileLocation path to file
     * @returnn workbook or null
     */
    @Override
    public Workbook readFile(@Nonnull String fileLocation) {
        try {
            return new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
