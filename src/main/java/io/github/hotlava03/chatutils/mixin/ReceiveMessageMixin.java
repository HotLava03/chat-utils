package io.github.hotlava03.chatutils.mixin;

import io.github.hotlava03.chatutils.events.RecieveMessageAnitSpamCallback;
import io.github.hotlava03.chatutils.util.AntiSpamReturn;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(ChatHud.class)
public class ReceiveMessageMixin {
    @Shadow @Final private List<ChatHudLine.Visible> visibleMessages;

    @ModifyVariable(
            method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V",
            at = @At("HEAD"), argsOnly = true)
    public Text addMessage(Text message) {
        AntiSpamReturn asr = RecieveMessageAnitSpamCallback.EVENT.invoker().accept(message, visibleMessages);
        if(asr.getIsSpam()) {
            return asr.getMessage();
        }
        return message;
    }
}
