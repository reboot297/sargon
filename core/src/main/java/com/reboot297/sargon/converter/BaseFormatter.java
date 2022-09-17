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

/**
 * Base Formatter for all typer of data.
 *
 * @param <S> type of the source data.
 * @param <R> type of the result.
 */
interface BaseFormatter<S, R> {
    /**
     * Convert items to different files format.
     *
     * @param source source data
     * @return result date
     */
    @Nonnull
    R format(S source);
}
