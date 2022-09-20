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
import javax.inject.Singleton;

/**
 * PropertiesManager for android module.
 */
@Singleton
final class AndroidPropertiesManager extends PropertiesManager {

    @Inject
    AndroidPropertiesManager() {
        addProperty("input-res-folder",
                "./sample_data/android/res",
                "Path to android resource folder from which strings will be generated");

        addProperty("output-res-folder",
                "./target/android/res",
                "Path to android resource folder into which strings will be generated");
    }

    @Nonnull
    @Override
    public String getCommand() {
        return "android";
    }

    @Override
    String getInputKey() {
        return "android.input-res-folder";
    }

    @Override
    String getOutputKey() {
        return "android.output-res-folder";
    }
}
