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
import javax.annotation.Nullable;

/**
 * The class describe default cli command.
 */
public final class DefaultCommand extends Command {

    /**
     * Short name for the command.
     */
    @Nullable
    private final String shortName;

    /**
     * Default constructor.
     *
     * @param shortName   short name for cli interface
     * @param longName    long name for cli interface
     * @param description simple text description
     * @param runnable    the block of code that will be executed when command be chosen
     */
    public DefaultCommand(@Nullable String shortName,
                          @Nonnull String longName,
                          @Nonnull String description,
                          @Nonnull Runnable runnable) {
        super(longName, description, runnable);
        this.shortName = shortName;
    }

    /**
     * SHort name for the command.
     *
     * @return string value
     */
    @Nullable
    public String getShortName() {
        return shortName;
    }
}
