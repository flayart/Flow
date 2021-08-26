package it.flayart.flow;

import it.flayart.flow.objects.TelegramMap;
import lombok.Getter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public abstract class TelegramBot extends TelegramLongPollingBot {
    private final String username;
    private final String token;

    private final TelegramMap telegramMap;

    public TelegramBot(String username, String token) {
        this.username = username;
        this.token = token;

        this.telegramMap = new TelegramMap();

        onEnable();
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        this.telegramMap.executeCommand(update);
        this.telegramMap.executeCallback(update);
    }

    public abstract void onEnable();
}
