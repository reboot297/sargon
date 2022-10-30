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

import com.reboot297.sargon.model.ItemType;
import com.reboot297.sargon.model.StringItem;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AndroidParserTest {

    @Test
    public void testParsingStrings() {
        final String source = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "<string name=\"simple_string\">Simple String</string>\n" +
                "</resources>\n";

        final String sourceEn = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "<string name=\"simple_string\">Simple En String</string>\n" +
                "</resources>\n";


        final String sourceEnUS = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "<string name=\"simple_string\">Simple En_US String</string>\n" +
                "</resources>\n";

        var sourceMap = Map.of(
                "", source,
                "en", sourceEn,
                "en_US", sourceEnUS
        );

        var parser = new AndroidParser();
        var items = parser.parse(sourceMap);

        assertEquals(1, items.size());
        assertEquals(ItemType.STRING, items.get(0).getType());
        assertEquals("simple_string", ((StringItem) items.get(0)).getId());
        assertEquals("Simple String", ((StringItem) items.get(0)).getValues().get(""));
        assertEquals("Simple En String", ((StringItem) items.get(0)).getValues().get("en"));
        assertEquals("Simple En_US String", ((StringItem) items.get(0)).getValues().get("en_US"));
    }
}
