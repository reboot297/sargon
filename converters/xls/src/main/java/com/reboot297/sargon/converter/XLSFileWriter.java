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

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writer for XLS files.
 */
@Singleton
final class XLSFileWriter implements BaseFileWriter<Workbook> {
    @Inject
    XLSFileWriter() {

    }

    /**
     * Write workbook into the file.
     *
     * @param source workbook
     * @param path   path to file
     * @return true if success
     */
    @Override
    public boolean writeFile(@Nonnull Workbook source, @Nonnull String path) {
        try {
            Path p = Path.of(path);
            Files.createDirectories(p.getParent());
            OutputStream outputStream = Files.newOutputStream(p);
            source.write(outputStream);
            source.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
