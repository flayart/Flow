package it.flayart.flow.objects;

import com.google.common.collect.Lists;
import it.flayart.flow.callback.Callback;
import it.flayart.flow.commands.Command;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.List;

@Data
public class TelegramMap {
    private final List<Command> commandList = Lists.newArrayList();
    private final List<Callback> callbackList = Lists.newArrayList();

    public void executeCommand(Update update) {
        if(!update.hasMessage() && !update.getMessage().hasText()) return;

        for(Command command : this.commandList) {
            if(!update.getMessage().getText().startsWith(command.getCommand())) continue;
            command.execute(update, update.getMessage(), update.getMessage().getFrom());
        }
    }

    public void executeCallback(Update update) {
        if(!update.hasCallbackQuery()) return;

        for(Callback callback : this.callbackList) {
            if(!callback.getCallback().equals(update.getCallbackQuery().getId())) continue;
            callback.execute(update, update.getMessage(), update.getCallbackQuery().getFrom());
        }
    }
}
