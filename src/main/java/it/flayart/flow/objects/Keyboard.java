package it.flayart.flow.objects;

import com.google.common.collect.Lists;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Keyboard {
    private final List<List<InlineKeyboardButton>> keyboard;
    private final InlineKeyboardMarkup keyboardMarkup;

    private InlineKeyboardButton button;
    private List<InlineKeyboardButton> row;

    public Keyboard() {
        this.keyboard = Lists.newArrayList();
        this.keyboardMarkup = new InlineKeyboardMarkup();
    }

    public Keyboard row() {
        this.row = Lists.newArrayList();
        return this;
    }

    public Keyboard button(String text, String callbackData) {
        this.button = new InlineKeyboardButton();
        this.button.setText(text);
        this.button.setCallbackData(callbackData);
        this.row.add(button);
        return this;
    }

    public Keyboard buttonUrl(String text, String url) {
        this.button = new InlineKeyboardButton();
        this.button.setText(text);
        this.button.setUrl(url);
        this.row.add(button);
        return this;
    }

    public Keyboard endRow() {
        this.keyboard.add(this.row);
        this.row = null;
        return this;
    }

    public InlineKeyboardMarkup create() {
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
