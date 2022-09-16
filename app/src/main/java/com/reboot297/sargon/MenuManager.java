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
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.cli.ParseException;

public class MenuManager {
    /**
     * App Manager instance.
     */
    @Inject
    AppManager appManager;

    /**
     * List of options.
     */
    private final Options options = new Options();
    /**
     * Command line parser instance.
     */
    private final CommandLineParser parser = new DefaultParser();

    /**
     * Constructor for MenuManager.
     */
    public MenuManager() {
        App.appComponent.inject(this);
        var commands = new HashSet<String>(); //TODO make set of commands
        //TODO add set of default commands
        createOptions(commands);
    }

    private void createOptions(Set<String> availableCommands) {
        for (String cm : availableCommands) {
            options.addOption(Option.builder()
                    .longOpt(cm)
                    .desc("Convert xls table to android xml")
                    .build());
        }

        options.addOption(Option.builder()
                .option("v")
                .longOpt("version")
                .desc("Display app version")
                .build());
    }

    private void parse(String[] args) {
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("version")) {
                printVersion();
            }
        } catch (ParseException e) {
            printHelp();
        }
    }

    /**
     * Print app version.
     */
    public void printVersion() {
        System.out.println("Sargon");
        System.out.println(getClass().getPackage().getImplementationVersion());
    }

    /**
     * Display help.
     */
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Sargon", options);
    }


    /**
     * Open menu.
     * @param args arguments.
     */
    public void start(String[] args) {
        parse(args);
    }

}
