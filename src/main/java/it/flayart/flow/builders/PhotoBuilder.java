package it.flayart.flow.builders;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Getter @Setter
public class PhotoBuilder {
    private final SendPhoto sendPhoto;
    private InlineKeyboardMarkup keyboard;

    public PhotoBuilder(String chatid, InputFile photo) {
        this.sendPhoto = new SendPhoto();

        this.sendPhoto.setChatId(chatid);
        this.sendPhoto.setPhoto(photo);
    }

    public SendPhoto build() {
        if(keyboard != null) this.sendPhoto.setReplyMarkup(keyboard);
        return sendPhoto;
    }
}
