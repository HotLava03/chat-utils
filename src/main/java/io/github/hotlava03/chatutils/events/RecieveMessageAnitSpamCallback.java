package io.github.hotlava03.chatutils.events;

import io.github.hotlava03.chatutils.util.AntiSpamReturn;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;

import java.util.List;

public interface RecieveMessageAnitSpamCallback {

    AntiSpamReturn antiSpamReturn = new AntiSpamReturn();
    Event<RecieveMessageAnitSpamCallback> EVENT = EventFactory.createArrayBacked(
            RecieveMessageAnitSpamCallback.class,
            (listeners) -> (text, lines) -> {
                for (RecieveMessageAnitSpamCallback listener : listeners) {
                    AntiSpamReturn asr = listener.accept(text, lines);
                    if(asr != null) {
                        antiSpamReturn.setMessage(asr.getMessage());
                        antiSpamReturn.setIsSpam(asr.getIsSpam());
                    }
                }
                return antiSpamReturn;
            });

    AntiSpamReturn accept(Text text, List<ChatHudLine.Visible> lines);
}