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
 * This class describes the command for converters.
 */
public final class ConvertersCommand extends Command {

    /**
     * Id of the converter which will read data.
     */
    @Nonnull
    private final String idFrom;
    /**
     * Id of the converter which will write data.
     */
    @Nonnull
    private final String idTo;

    /**
     * Default constructor.
     *
     * @param idFrom   id of the converter which will read data.
     * @param idTo     id of the converter which will write data.
     * @param runnable the block of code that will be executed when command run.
     */
    public ConvertersCommand(@Nonnull String idFrom,
                             @Nonnull String idTo,
                             @Nonnull Runnable runnable) {
        super("", "", runnable);
        this.idFrom = idFrom;
        this.idTo = idTo;
    }

    /**
     * Id of the converter for reading data.
     *
     * @return string value.
     */
    @Nonnull
    public String getIdTo() {
        return idTo;
    }

    /**
     * Id of the converter for writing data.
     *
     * @return string value.
     */
    @Nonnull
    public String getIdFrom() {
        return idFrom;
    }

    /**
     * The long name for command.
     * It is concatenation of converter ids.
     *
     * @return string value.
     */
    @Nonnull
    @Override
    public String getLongName() {
        return this.idFrom + "-to-" + this.idTo;
    }

    /**
     * Description of the command.
     * It is generated depending on converter ids.
     *
     * @return string vale
     */
    @Nonnull
    @Override
    public String getDescription() {
        return String.format("Convert %s to %s", idFrom, idTo);
    }
}
