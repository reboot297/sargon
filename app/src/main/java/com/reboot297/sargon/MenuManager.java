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

import com.reboot297.sargon.command.Command;
import com.reboot297.sargon.command.ConvertersCommand;
import com.reboot297.sargon.command.DefaultCommand;
import com.reboot297.sargon.manager.AppManager;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
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
     * List of available commands.
     */
    private final List<Command> commands = new LinkedList<>();

    /**
     * Constructor for MenuManager.
     */
    public MenuManager() {
        App.appComponent.inject(this);
        createOptions(generateCommands());
    }

    private List<Command> generateCommands() {
        var ids = appManager.getAvailableIds();
        for (var from : ids) {
            for (var to : ids) {
                if (!from.equals(to)) {
                    commands.add(new ConvertersCommand(from, to, () -> appManager.convert(from, to)));
                }
            }
        }
        commands.add(new DefaultCommand("g", "generate-properties",
                "Generate default properties file", this::generateProperties));
        commands.add(new DefaultCommand("v", "version", "Display app version", this::printVersion));
        commands.add(new DefaultCommand("h", "help", "Display help", this::printHelp));
        return commands;
    }

    private void createOptions(@Nonnull List<Command> availableCommands) {
        for (Command cm : availableCommands) {
            if (cm instanceof ConvertersCommand) {
                addConverterCommandOption((ConvertersCommand) cm);
            } else {
                addDefaultCommandOption((DefaultCommand) cm);
            }
        }
    }

    private void addDefaultCommandOption(@Nonnull DefaultCommand command) {
        options.addOption(Option.builder()
                .option(command.getShortName())
                .longOpt(command.getLongName())
                .desc(command.getDescription())
                .build());
    }

    private void addConverterCommandOption(@Nonnull ConvertersCommand command) {
        options.addOption(Option.builder()
                .longOpt(command.getLongName())
                .desc(command.getDescription())
                .build());
    }

    /**
     * Generate properties file.
     */
    private void generateProperties() {
        System.out.println("Generation properties");
        appManager.generateProperties();
        System.out.println("Generating properties completed");
    }

    /**
     * Print app version.
     */
    public void printVersion() {
        System.out.println("Sargon");
        System.out.println(getClass().getPackage().getImplementationVersion());
        System.out.println("The tool to convert string data between different formats.");
        System.out.println("Author: Viktor Pop");
        System.out.println("Source code: https://github.com/reboot297/sargon");
        System.out.println("License Apache 2.0 https://github.com/reboot297/sargon/blob/main/LICENSE");
    }

    /**
     * Display help.
     */
    public void printHelp() {
        var formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp("Sargon", options);
    }

    /**
     * Open menu.
     *
     * @param args arguments.
     */
    public void start(String[] args) {
        parseArgs(args);
    }

    /**
     * Parse Arguments from command line.
     *
     * @param args arguments
     */
    private void parseArgs(String[] args) {
        try {
            var cmd = parser.parse(options, args);
            var longName = cmd.getOptions()[0].getLongOpt();
            var command = findCommand(longName);
            command.getRunnable().run();
        } catch (ParseException | ArrayIndexOutOfBoundsException e) {
            printHelp();
        }
    }

    /**
     * Find command by long name.
     *
     * @param longName long name.
     * @return command
     */
    private Command findCommand(@Nonnull String longName) {
        return commands.stream()
                .filter(c -> c.getLongName().equals(longName))
                .findFirst()
                .orElse(null);
    }

}
