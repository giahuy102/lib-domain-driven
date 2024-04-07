package com.huyle.ms.command;

@FunctionalInterface
public interface CommandHandler<C extends Command> {
    public void handle(C command);
}
