package it.flayart.flow;

import it.flayart.flow.objects.TelegramMap;
import lombok.Getter;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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

    @SneakyThrows
    public void start() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
    }

    public abstract void onEnable();
}
