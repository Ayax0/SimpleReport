package ch.simonsky.event;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.TextEvent;

import ch.simonsky.codec.DataInput_Stream;
import ch.simonsky.gui.Gui;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.stream.ChatController.ChatChannelListener;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Chat;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tv.twitch.chat.ChatEvent;

public class Events {
	
	@SubscribeEvent
	public void onServerChat(ClientChatReceivedEvent e){
		 String msgname = EnumChatFormatting.getTextWithoutFormattingCodes(e.message.getUnformattedText());
	        String[] msg_array=(msgname.split( " " ));
	        String name = msg_array[0].replace( ":","" );
	        e.message.setChatStyle( new ChatStyle().setChatClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/chatlog " + name ) ) );

	        if(msg_array[0].equalsIgnoreCase("Der")){
	        	if(msg_array[1].equalsIgnoreCase("Chatlog")){
	        		if(msg_array[7].equalsIgnoreCase("Adresse")){
			            String reportname = msg_array[3];
			            String reportlink = msg_array[10];
		
			            Gui.player.add( reportname );
			            Gui.url.add( reportlink.replace( "https://chatlog.gommehd.net/","" ) );
	        		}
	        	}
	        }
	}
	
	@SubscribeEvent
	public void onInteract(GuiScreenEvent e){
		
	}
	

}
