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
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Reader for Android xml files.
 */
final class AndroidFileReader implements BaseFileReader<String> {

    @Inject
    AndroidFileReader() {

    }

    /**
     * Open android xml file and read it as a string.
     *
     * @param path path to file
     * @return string or null
     */
    @Nullable
    @Override
    public String readFile(@Nonnull String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
