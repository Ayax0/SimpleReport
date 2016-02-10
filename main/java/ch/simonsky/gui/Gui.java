package ch.simonsky.gui;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class Gui extends GuiScreen{
	
	public static List<String> player = new ArrayList<String>();
	public static List<String> url = new ArrayList<String>();
	
	public static int fromPublic = 0;
	public static int toPublic = 8;
	
	public static int page = 1;
	
	@Override
	public void initGui() {
		
		int from = fromPublic;
		int to = toPublic;
		
		//Report Buttons
		for(int id = fromPublic; id < player.size(); id++){
			if(id >= from && id < to){
				buttonList.add(new GuiButton(id, width / 2 + 100, height / 2 - (80 - (20 * (id - fromPublic))), 50, 20, "Report"));
			}
		}
		
		//info Buttons
		for(int id = fromPublic; id < player.size(); id++){
			if(id >= from && id < to){
				buttonList.add(new GuiButton(id+10000, width / 2 + 150, height / 2 - (80 - (20 * (id - fromPublic))), 20, 20, EnumChatFormatting.AQUA.toString() + EnumChatFormatting.ITALIC + "i"));
			}
		}
		
		//Delete Buttons
		for(int id = fromPublic; id < player.size(); id++){
			if(id >= from && id < to){
				buttonList.add(new GuiButton(id+1000000, width / 2 + 170, height / 2 - (80 - (20 * (id - fromPublic))), 20, 20, EnumChatFormatting.RED + "x"));
			}
		}
		
		//next last
		buttonList.add(new GuiButton(1800, width / 2 + 20, height / 2 + 100, 15, 20, ">"));
		buttonList.add(new GuiButton(1900, width / 2 - 50, height / 2 + 100, 15, 20, "<"));
		
		//loggin
		buttonList.add(new GuiButton(2000, 5, height - 25, 50, 20, "Login"));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		
		fontRendererObj.drawString("Chat-Reports", width / 2 - 40, height / 2 - 100, 16777215);
		fontRendererObj.drawString("Page" + " " + page, width / 2 - 25, height / 2 + 105, 16777215);
		
		int from = fromPublic;
		int to = toPublic;
		
		for(int i = 0; i < player.size(); i++){
			if(i >= from && i < to){
				fontRendererObj.drawString(player.get(i), width / 2 - 150, height / 2 - (70 - (20 * (i - fromPublic))), 16777215);
			}
		}
		
		for(int i = 0; i < player.size(); i++){
			if(i >= from && i < to){
				fontRendererObj.drawString(EnumChatFormatting.BLUE.toString() + EnumChatFormatting.UNDERLINE + url.get(i), width / 2 - 30, height / 2 - (72 - (20 * (i - fromPublic))), 16777215);
			}
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id){
		case 1800: next();
		break;
		case 1900: last();
		break;
		case 2000: Minecraft.getMinecraft().displayGuiScreen(new LogginGui());
		}
		if(button.id >= 10000 && button.id < 1000000){
			for(int i = 10000; i < player.size() + 10000; i ++){
				int id = i - 10000;
				if(button.id == i){
					Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
					if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
						try{
							String u = url.get(id);
							desktop.browse(URI.create("https://chatlog.gommehd.net/" + u));
						} catch (Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}
		else if(button.id >= 1000000){
			for(int i = 1000000; i < player.size() + 1000000; i ++){
				int id = i - 1000000;
				if(button.id == i){
					player.remove(id);
					url.remove(id);
					
					Point p = MouseInfo.getPointerInfo().getLocation();
					
					Minecraft.getMinecraft().displayGuiScreen(null);
					Minecraft.getMinecraft().displayGuiScreen(new Gui());
					
					try {
						new Robot().mouseMove(p.x, p.y);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void next(){
		if(!((fromPublic + 8) >= player.size())){
			fromPublic += 8;
			toPublic += 8;
			page += 1;
			Point p = MouseInfo.getPointerInfo().getLocation();
			
			Minecraft.getMinecraft().displayGuiScreen(null);
			Minecraft.getMinecraft().displayGuiScreen(new Gui());
			
			try {
				new Robot().mouseMove(p.x, p.y);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void last(){
		if(fromPublic - 8 >= 0){
			fromPublic -= 8;
			toPublic -= 8;
			page -= 1;
			Point p = MouseInfo.getPointerInfo().getLocation();
			
			Minecraft.getMinecraft().displayGuiScreen(null);
			Minecraft.getMinecraft().displayGuiScreen(new Gui());
			
			try {
				new Robot().mouseMove(p.x, p.y);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
