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
import com.reboot297.sargon.model.StringItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Parser for android strings.
 */
final class AndroidParser implements BaseParser<String, List<BaseItem>> {

    /**
     * Default constructor.
     */
    @Inject
    AndroidParser() {

    }

    /**
     * Parse android source data into the list of items.
     *
     * @param source like xml, json, xls
     * @return list of data.
     */
    @Nullable
    @Override
    public List<BaseItem> parse(@Nonnull String source) {
        var result = new ArrayList<BaseItem>();

        int position = 0;
        int nextPosition;
        String nextWord;
        do {
            nextPosition = getNextPosition(source, position);
            nextWord = source.substring(position, nextPosition);
            if (Constants.XML_TAG_RESOURCES_START.equals(nextWord)) { // inside resources block
                nextPosition = parseResourcesBlock(source, result, nextPosition);
            }
            position = nextPosition + 1;
        } while (nextPosition < source.length() - 1);

        return result;
    }

    private int parseResourcesBlock(String source, List<BaseItem> result, int nextPosition) {
        String nextWord;
        do {
            int position = nextPosition + 1;
            nextPosition = getNextPosition(source, position);
            nextWord = source.substring(position, nextPosition);

            switch (nextWord.trim()) {
                case Constants.XML_TAG_ANDROID_STRING_START:
                    nextPosition = parseAndroidString(source, result, nextPosition);
                    break;
                default:
                    break;
            }
        } while (!Constants.XML_TAG_RESOURCES_END.equals(nextWord));

        return nextPosition;
    }

    /**
     * Parse String Item.
     *
     * @param source        string
     * @param result        AndroidStringItem
     * @param startPosition start position for parsing
     * @return new position
     */
    private static int parseAndroidString(String source, List<BaseItem> result, int startPosition) {

        int endPosition = source.indexOf(Constants.XML_TAG_ANDROID_STRING_END, startPosition);

        int f = source.indexOf(Constants.TAG_END, startPosition);
        String argumentsSource = source.substring(startPosition, f);

        String name = null;
        for (AndroidStringArgument arg : parseStringItemArguments(argumentsSource.trim())) {
            if (Constants.XML_ATTRIBUTE_ANDROID_STRING_NAME.equals(arg.name)) {
                name = arg.value;
            }
        }
        if (name == null) {
            throw new IllegalArgumentException("Cannot extract name from string resource " + argumentsSource);
        }

        String value = source.substring(f + 1, endPosition);
        result.add(new StringItem(name, value));
        return endPosition;
    }

    /**
     * Parsing android string resource arguments into list.
     *
     * @param source string like - name="simple_string" translatable=false
     * @return list of arguments
     */
    private static List<AndroidStringArgument> parseStringItemArguments(String source) {
        List<AndroidStringArgument> list = new LinkedList<>();
        for (String s : source.split(" ")) {
            list.add(parseStringArgument(s.trim()));
        }

        return list;
    }

    /**
     * Parsing string like - name="simple_string" into AndroidString ArgumentClass.
     *
     * @param trimmedSource source, should be trimmed
     * @return argument item
     */
    private static AndroidStringArgument parseStringArgument(String trimmedSource) {
        int delimiterPosition = trimmedSource.indexOf(Constants.XML_ANDROID_ARGUMENT_DELIMITER);
        //left part
        String name = trimmedSource.substring(0, delimiterPosition);
        //right part
        String value = trimmedSource.substring(delimiterPosition + 1).replaceAll("\"", "");
        return new AndroidStringArgument(name, value);
    }


    private static int getNextPosition(String source, int position) {
        return Stream.of(" ", "\n", "\r\n")
                .map(item -> source.indexOf(item, position))
                .filter(index -> index > -1)
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
    }

    /**
     * Class contains info about argument in android string resources.
     */
    private static class AndroidStringArgument {
        /**
         * Name of argument.
         */
        private final String name;
        /**
         * Argument value.
         */
        private final String value;

        AndroidStringArgument(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
