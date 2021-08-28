package it.flayart.flow.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@RequiredArgsConstructor @Getter
public abstract class Command {
    private final String command;

    public abstract void execute(Update update, Message message, User user);
}
