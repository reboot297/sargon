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
import com.reboot297.sargon.model.ItemType;
import com.reboot297.sargon.model.StringItem;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Implementation of formatter for android.
 */
final class AndroidFormatter implements BaseFormatter<List<BaseItem>, String> {

    /**
     * Default constructor.
     */
    @Inject
    AndroidFormatter() {

    }

    /**
     * Convert list of items into xml data.
     *
     * @param items source data
     * @return formatted data
     */
    @Override
    @Nonnull
    public String format(@Nonnull List<BaseItem> items) {
        var builder = new StringBuilder();
        builder.append(Constants.XML_HEADER).append(Constants.END_LINE)
                .append(Constants.XML_TAG_RESOURCES_START).append(Constants.END_LINE);
        for (var item : items) {
            if (item.getType() == ItemType.EMPTY) {
                builder.append(Constants.END_LINE);
            } else if (item.getType() == ItemType.STRING) {
                writeStringItem(builder, (StringItem) item);
            }
        }

        builder.append(Constants.XML_TAG_RESOURCES_END).append(Constants.END_LINE);
        return builder.toString();
    }

    /**
     * Write string item.
     *
     * @param builder string builder
     * @param item    string item
     */
    private static void writeStringItem(@Nonnull StringBuilder builder, @Nonnull StringItem item) {
        builder.append(Constants.XML_TAG_ANDROID_STRING_START).append(Constants.SPACE)
                .append(Constants.XML_ATTRIBUTE_ANDROID_STRING_NAME)
                .append(Constants.XML_ANDROID_ARGUMENT_DELIMITER)
                .append(Constants.QUOTE)
                .append(item.getId())
                .append(Constants.QUOTE)
                .append(Constants.TAG_END)
                .append(item.getValue())
                .append(Constants.XML_TAG_ANDROID_STRING_END)
                .append(Constants.END_LINE);
    }

}
