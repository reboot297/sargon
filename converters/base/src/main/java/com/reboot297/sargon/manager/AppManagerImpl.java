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

package com.reboot297.sargon.manager;

import com.reboot297.sargon.converter.AndroidConverter;
import com.reboot297.sargon.converter.BaseConverter;
import com.reboot297.sargon.converter.XlsConverter;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link AppManager}
 */
class AppManagerImpl implements AppManager {
    private final Map<String, BaseConverter> converters = new HashMap<>();

    @Inject
    AppManagerImpl() {
        converters.put("xls", new XlsConverter());
        converters.put("android", new AndroidConverter());
    }


    @Override
    public boolean convert(String from, String to) {
        //TODO not implemented
        return false;
    }

    @Override
    public List<String> getAvailableCommands() {
        return List.of("android", "xml");
    }
}
