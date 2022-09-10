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

/**
 * Class with constants.
 */
final class Constants {

    /**
     * Xml header in android string resource file.
     */
    static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    /**
     * Tag for start resources block.
     */
    static final String XML_TAG_RESOURCES_START = "<resources>";
    /**
     * Tag for end resources block.
     */
    static final String XML_TAG_RESOURCES_END = "</resources>";

    /**
     * Tag for start string block.
     */
    static final String XML_TAG_ANDROID_STRING_START = "<string";
    /**
     * Tag for end string block.
     */
    static final String XML_TAG_ANDROID_STRING_END = "</string>";

    /**
     * String name attribute.
     */
    static final String XML_ATTRIBUTE_ANDROID_STRING_NAME = "name";

    /**
     * Arguments delimiter.
     */
    static final Character XML_ANDROID_ARGUMENT_DELIMITER = '=';

    /**
     * End line.
     */
    static final Character END_LINE = '\n';
    /**
     * End of tag character.
     */
    static final Character TAG_END = '>';
    /**
     * Quote character.
     */
    static final Character QUOTE = '"';
    /**
     * Space character.
     */
    static final Character SPACE = ' ';

    private Constants() {

    }
}
