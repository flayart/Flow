package it.flayart.flow.builders;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineBuilder {
    private final SendMessage sendMessage;
    private final InlineKeyboardMarkup keyboardMarkup;

    private InlineKeyboardButton button;

    private List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    private List<InlineKeyboardButton> row;

    public InlineBuilder(String chatid, String message) {
        this.sendMessage = new SendMessage();
        this.keyboardMarkup = new InlineKeyboardMarkup();

        this.sendMessage.setText(message);
        this.sendMessage.setChatId(chatid);
    }

    public InlineBuilder row() {
        this.row = new ArrayList<>();
        return this;
    }

    public InlineBuilder button(String text, String callbackData) {
        this.button = new InlineKeyboardButton();
        this.button.setText(text);
        this.button.setCallbackData(callbackData);
        this.row.add(button);
        return this;
    }

    public InlineBuilder buttonUrl(String text, String url) {
        this.button = new InlineKeyboardButton();
        this.button.setText(text);
        this.button.setUrl(url);
        this.row.add(button);
        return this;
    }

    public InlineBuilder endRow() {
        this.keyboard.add(this.row);
        this.row = null;
        return this;
    }

    @SneakyThrows
    public SendMessage build() {
        this.sendMessage.enableMarkdown(true);

        this.keyboardMarkup.setKeyboard(keyboard);
        this.sendMessage.setReplyMarkup(keyboardMarkup);

        return sendMessage;
    }
}
