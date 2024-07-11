package cn.ksmcbrigade.lastserver.mixin;

import cn.ksmcbrigade.lastserver.LastServer;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JoinMultiplayerScreen.class)
public abstract class ServersListScreenMixin extends Screen {
    protected ServersListScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "init",at = @At("TAIL"))
    public void init(CallbackInfo ci){
        if(this.minecraft==null) return;
        Button button = Button.builder(Component.translatable("ls.text"), ref-> {
            if(this.minecraft!=null){
                ConnectScreen.startConnecting(this, this.minecraft, ServerAddress.parseString(LastServer.lastIp), new ServerData("",LastServer.lastIp, ServerData.Type.OTHER), false);
            }
        }).size(100,20).pos(this.minecraft.getWindow().getGuiScaledWidth()+(LastServer.right?0:-this.minecraft.getWindow().getGuiScaledWidth()/2)-154,10).build();
        button.active = !LastServer.lastIp.isEmpty();
        this.addRenderableWidget(button);
    }
}
