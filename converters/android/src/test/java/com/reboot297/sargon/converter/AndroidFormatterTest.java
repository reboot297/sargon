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
import com.reboot297.sargon.model.EmptyItem;
import com.reboot297.sargon.model.StringItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class AndroidFormatterTest {

    @Test
    public void testStringCreating() {
        final String output = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "\n" +
                "<string name=\"simple_string\">Simple String</string>\n" +
                "</resources>\n";

        final String outputEn = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "<string name=\"simple_string\">Simple En String</string>\n" +
                "\n" +
                "</resources>\n";


        final String outputEnUS = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "\n" +
                "<string name=\"simple_string\">Simple En_US String</string>\n" +
                "</resources>\n";


        AndroidFormatter formatter = new AndroidFormatter();


        var items = new ArrayList<BaseItem>();
        var setValues = new LinkedHashSet<String>();
        setValues.add("");
        setValues.add("en_US");
        items.add(new EmptyItem(setValues));
        var values = new LinkedHashMap<String, String>();
        values.put("", "Simple String");
        values.put("en", "Simple En String");
        values.put("en_US", "Simple En_US String");
        items.add(new StringItem("simple string", values));
        setValues = new LinkedHashSet<>();
        setValues.add("en");
        items.add(new EmptyItem(setValues));

        var result = formatter.format(items);
        assertEquals(output, result.get(""));
        assertEquals(outputEn, result.get("en"));
        assertEquals(outputEnUS, result.get("en_US"));
    }
}
