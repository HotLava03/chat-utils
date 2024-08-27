package io.github.hotlava03.chatutils.util;

import net.minecraft.text.Text;

public class AntiSpamReturn {
    private boolean isSpam;
    private Text message;

    public AntiSpamReturn() {
        this.isSpam = false;
        this.message = Text.empty();
    }

    public void setIsSpam(boolean bool) {this.isSpam = bool;}
    public void setMessage(Text text) {this.message = text;}

    public boolean getIsSpam() {return this.isSpam;}
    public Text getMessage() {return this.message;}

}
