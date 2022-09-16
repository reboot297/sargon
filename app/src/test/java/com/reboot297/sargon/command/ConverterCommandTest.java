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

package com.reboot297.sargon.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ConverterCommandTest {

    @Test
    public void testGeneratingCommandLongName() {
        var command = new ConvertersCommand("fromId", "toId", () ->{});
        assertEquals("fromId-to-toId", command.getLongName());
    }

    @Test
    public void testGeneratingCommandDescription() {
        var command = new ConvertersCommand("fromId", "toId", () ->{});
        assertEquals("Convert fromId to toId", command.getDescription());
    }
}
