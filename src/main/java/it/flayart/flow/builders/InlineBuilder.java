package it.flayart.flow.builders;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Getter @Setter
public class InlineBuilder {
    private final SendMessage sendMessage;
    private InlineKeyboardMarkup keyboard;

    public InlineBuilder(String chatid, String message) {
        this.sendMessage = new SendMessage();

        this.sendMessage.setText(message);
        this.sendMessage.setChatId(chatid);
    }

    @SneakyThrows
    public SendMessage build() {
        if(keyboard != null) this.sendMessage.setReplyMarkup(keyboard);
        this.sendMessage.enableMarkdown(true);

        return sendMessage;
    }
}
