package com.huyle.ms.command;

@FunctionalInterface
public interface CommandHandler<C extends Command, P> {
    public P handle(C command);
}
