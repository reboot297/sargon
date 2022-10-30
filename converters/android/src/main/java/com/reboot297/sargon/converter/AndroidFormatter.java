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

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of formatter for android.
 */
final class AndroidFormatter implements BaseFormatter<List<BaseItem>, Map<String, String>> {

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
     * @return map of {locale-key, xml-formatted-data}
     */
    @Override
    @Nonnull
    public Map<String, String> format(@Nonnull List<BaseItem> items) {
        var localeContent = new HashMap<String, StringBuilder>();

        var builder = new StringBuilder();
        for (var item : items) {
            if (item.getType() == ItemType.STRING) {

                var id = createAndroidId(((StringItem) item).getId());
                var values = ((StringItem) item).getValues();

                for (var key : values.keySet()) {
                    builder = localeContent.get(key);
                    if (builder == null) {
                        builder = new StringBuilder();
                        localeContent.put(key, builder);
                        builder.append(Constants.XML_HEADER).append(Constants.END_LINE)
                                .append(Constants.XML_TAG_RESOURCES_START).append(Constants.END_LINE);
                    }
                    writeStringItem(builder, id, values.get(key));
                }
            }
        }

        var result = new HashMap<String, String>();
        for (var key : localeContent.keySet()) {
            builder = localeContent.get(key);
            builder.append(Constants.XML_TAG_RESOURCES_END).append(Constants.END_LINE);
            result.put(key, builder.toString());
        }

        return result;
    }

    /**
     * Create android compatible id from item id.
     *
     * @param id item id
     * @return android compatibleId
     */
    private String createAndroidId(@Nonnull String id) { // todo add unit tests
        return id.replaceAll(" ", "_");
    }

    /**
     * Write string item.
     *
     * @param builder string builder
     * @param id      string item id
     * @param value   string value
     */
    private static void writeStringItem(@Nonnull StringBuilder builder, @Nonnull String id, @Nonnull String value) {
        builder.append(Constants.XML_TAG_ANDROID_STRING_START).append(Constants.SPACE)
                .append(Constants.XML_ATTRIBUTE_ANDROID_STRING_NAME)
                .append(Constants.XML_ANDROID_ARGUMENT_DELIMITER)
                .append(Constants.QUOTE)
                .append(id)
                .append(Constants.QUOTE)
                .append(Constants.TAG_END)
                .append(value)
                .append(Constants.XML_TAG_ANDROID_STRING_END)
                .append(Constants.END_LINE);
    }

}
