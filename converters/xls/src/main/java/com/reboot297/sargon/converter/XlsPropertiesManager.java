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
 * PropertiesManager for xls module.
 */
@Singleton
final class XlsPropertiesManager extends PropertiesManager {

    @Inject
    XlsPropertiesManager() {
        addProperty("input-file",
                "./sample_data/Sample_XLSX.xlsx",
                "Path to xls file from which strings will be generated");

        addProperty("output-file",
                "./target/xls/output_XLSX.xlsx",
                "Path to xls file into which strings will be generated");
    }

    @Nonnull
    @Override
    public String getCommand() {
        return "xls";
    }

    @Override
    String getInputKey() {
        return "xls.input-file";
    }

    @Override
    String getOutputKey() {
        return "xls.output-file";
    }
}
