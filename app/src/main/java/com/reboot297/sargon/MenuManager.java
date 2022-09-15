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

package com.reboot297.sargon;

import com.reboot297.sargon.manager.AppManager;

import javax.inject.Inject;

public class MenuManager {
    /**
     * App Manager instance.
     */
    @Inject
    AppManager appManager;

    /**
     * Constructor for MenuManager.
     */
    public MenuManager() {
        App.appComponent.inject(this);
    }

    /**
     * Open menu.
     */
    public void start() {
        System.out.println("Available convertors: ");
        System.out.println(appManager.getAvailableCommands());
        System.out.println("Convert");
        appManager.convert("xls", "android");
    }

}
