package it.flayart.flow.builders;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineBuilder {

    private final Long chatid;
    private final String message;

    private List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    private List<InlineKeyboardButton> row = null;

    public InlineBuilder(Long chatid, String message) {
        this.chatid = chatid;
        this.message = message;
    }

    public InlineBuilder row() {
        this.row = new ArrayList<>();
        return this;
    }

    public InlineBuilder button(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        row.add(button);
        return this;
    }

    public InlineBuilder buttonUrl(String text, String url) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setUrl(url);
        row.add(button);
        return this;
    }

    public InlineBuilder endRow() {
        this.keyboard.add(this.row);
        this.row = null;
        return this;
    }

    @SneakyThrows
    public SendMessage build() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(this.message);
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatid));

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);

        return sendMessage;
    }
}
