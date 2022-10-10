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

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * This class help us to write android formatted string to the files.
 */
final class AndroidFileWriter implements BaseFileWriter<Map<String, String>> {

    /**
     * LocaleManage instance.
     */
    private final AndroidLocaleManager localeManager;

    @Inject
    AndroidFileWriter(AndroidLocaleManager localeManager) {
        this.localeManager = localeManager;
    }

    /**
     * Write android xml data into the files.
     *
     * @param source        map of {locale-key, xml-formatted-data}
     * @param resFolderPath path to android "res" folder
     * @return true if success.
     */
    @Override
    public boolean write(@Nonnull Map<String, String> source, @Nonnull String resFolderPath) {
        boolean success = false;
        for (var key : source.keySet()) {
            var pathToFile = localeManager.pathToLocaleFile(resFolderPath, key);
            success |= writeFile(source.get(key), pathToFile);
        }
        return success;
    }

    /**
     * Write formatted text data in to the file.
     *
     * @param source source data
     * @param path   path to the file
     * @return true if success
     */
    private boolean writeFile(String source, @Nonnull String path) {
        try {
            Path p = Path.of(path);
            Files.createDirectories(p.getParent());
            Files.writeString(p, source);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
