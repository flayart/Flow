package it.flayart.flow.callback;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor @Getter
public abstract class Callback {
    private final String callback;

    public abstract void execute(Update update);
}
