package ch.simonsky.keybinding;

import ch.simonsky.Main;
import ch.simonsky.gui.Gui;
import ch.simonsky.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyHandler {
	
	@SubscribeEvent
	public void onKeyPressed(KeyInputEvent e){
		if(ClientProxy.keybinding.isKeyDown()){
			Minecraft.getMinecraft().displayGuiScreen(new Gui());
		}
	}

}
