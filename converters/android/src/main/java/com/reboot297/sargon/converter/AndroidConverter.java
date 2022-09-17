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
import java.io.File;

/**
 * Converter for Android resources.
 */
final class AndroidConverter extends BaseTextConverterImpl<String> {
    @Inject
    AndroidConverter(
            @Nonnull AndroidFormatter formatter,
            @Nonnull AndroidParser parser,
            @Nonnull AndroidFileReader reader,
            @Nonnull AndroidFileWriter writer) {
        super(formatter, parser, reader, writer);
    }

    @Nonnull
    @Override
    public String getCommand() {
        return "android";
    }

    @Override
    String pathToLocaleFile(@Nonnull String rootFolder, @Nonnull String localeId) {
        //TODO add unit test for generating path
        String folderSuffix = localeId
                .replace("Default", "")
                .replace("_", "-r");

        var builder = new StringBuilder();
        builder.append(rootFolder).append(File.separator)
                .append("values");
        if (!folderSuffix.isEmpty()) {
            builder.append("-").append(folderSuffix);
        }
        builder.append(File.separator)
                .append("strings.xml");

        return builder.toString();
    }
}
