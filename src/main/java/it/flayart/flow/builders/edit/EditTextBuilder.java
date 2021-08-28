package it.flayart.flow.builders.edit;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Getter @Setter
public class EditTextBuilder {
    private final EditMessageText editMessage;
    private InlineKeyboardMarkup keyboard;

    public EditTextBuilder(Message message) {
        this.editMessage = new EditMessageText();

        this.editMessage.setMessageId(message.getMessageId());
        this.editMessage.setChatId(String.valueOf(message.getChatId()));
        this.editMessage.setText(message.getText());
    }

    @SneakyThrows
    public EditMessageText build() {
        if(keyboard != null) this.editMessage.setReplyMarkup(keyboard);
        this.editMessage.enableMarkdown(true);

        return editMessage;
    }
}
