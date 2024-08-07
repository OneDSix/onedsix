package net.onedsix.server.command;

import com.mojang.brigadier.CommandDispatcher;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

public class TestCommand {

    public static void registerTestCommand() {
        CommandDispatcher<Object> dispatcher = new CommandDispatcher<>();

        dispatcher.register(
            literal("foo")
                .then(
                    argument("bar", integer())
                        .executes(c -> {
                            System.out.println("Bar is " + getInteger(c, "bar"));
                            return 1;
                        }
                    )
                )
                .executes(c -> {
                    System.out.println("Called foo with no arguments");
                    return 1;
                }
            )
        );
    }

}
