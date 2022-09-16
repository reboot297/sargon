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

import javax.annotation.Nonnull;

/**
 * Base class for all commands.
 */
public abstract class Command {

    /**
     * Long name for the command.
     */
    @Nonnull
    private final String longName;

    /**
     * Description for the command. It will be displayed in the help menu
     */
    @Nonnull
    private final String description;

    /**
     * The block of the code that will be executed when user run the command.
     */
    @Nonnull
    private final Runnable runnable;

    Command(@Nonnull String longName, @Nonnull String description, @Nonnull Runnable runnable) {
        this.longName = longName;
        this.description = description;
        this.runnable = runnable;
    }

    /**
     * Long name of the command.
     *
     * @return string value.
     */
    @Nonnull
    public String getLongName() {
        return longName;
    }

    /**
     * Description of the command.
     *
     * @return string value.
     */
    @Nonnull
    public String getDescription() {
        return description;
    }

    /**
     * The block of the code that will be executed when user run command.
     *
     * @return runnable object.
     */
    @Nonnull
    public Runnable getRunnable() {
        return runnable;
    }
}
