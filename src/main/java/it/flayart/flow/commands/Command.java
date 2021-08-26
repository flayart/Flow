package it.flayart.flow.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor @Getter
public abstract class Command {
    private final String command;

    public abstract void execute(Update update);
}
