package ch.simonsky.gui;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.IOException;

import ch.simonsky.codec.DataInput_Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatComponentText;

public class LogginGui extends GuiScreen{
	
	private static GuiTextField username;
	private static GuiTextField passwort;
	
	@Override
	public void initGui(){
		
		buttonList.add(new GuiButton(10, width / 2 + 60, height / 2 - 20, 30, 20, "Edit"));
		buttonList.add(new GuiButton(11, width / 2 + 60, height / 2 + 20, 30, 20, "Edit"));
		
		buttonList.add(new GuiButton(15, width / 2 - 52, height / 2 + 50, 142, 20, "Apply"));
		
		username = new GuiTextField(0, fontRendererObj, width / 2 - 50, height / 2 - 20, 100, 20);
		passwort = new GuiTextField(1, fontRendererObj, width / 2 - 50, height / 2 + 20, 100, 20);
		
		username.setCanLoseFocus(false);
		passwort.setCanLoseFocus(false);
		
		username.setEnabled(false);
		passwort.setEnabled(false);
		
		username.setFocused(false);
		passwort.setEnabled(false);
		
		if(!(DataInput_Stream.read() == null)){
			String[] passuser = DataInput_Stream.read().split(",");
			
			username.setText(passuser[0]);
			passwort.setText(getChar());
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		drawDefaultBackground();
		
		fontRendererObj.drawString("Username", width / 2 - 50, height / 2 - 32, 16777215);
		fontRendererObj.drawString("Passwort", width / 2 - 50, height / 2 + 8, 16777215);
		
		username.drawTextBox();
		passwort.drawTextBox();
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void updateScreen() {
		username.updateCursorCounter();
		passwort.updateCursorCounter();
		super.updateScreen();
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		username.textboxKeyTyped(typedChar, keyCode);
		passwort.textboxKeyTyped(typedChar, keyCode);
		
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id){
			case 10: Edit1();
			break;
			case 11: Edit2();
			break;
			case 15: Apply();
			break;
		}
	}
	
	public static void update(){
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
	
	public static void Edit1(){
		username.setFocused(true);
		passwort.setFocused(false);
		
		username.setEnabled(true);
		passwort.setEnabled(false);
	}
	
	public static void Edit2(){
		username.setFocused(false);
		passwort.setFocused(true);
		
		username.setEnabled(false);
		passwort.setEnabled(true);
	}
	
	public static void Apply(){
		username.setFocused(false);
		passwort.setFocused(false);
		
		username.setEnabled(false);
		passwort.setEnabled(false);
		
		DataInput_Stream.run(username.getText() + "," + passwort.getText()+",null");
		
		passwort.setText(getChar());
	}
	
	public static String getChar(){
		String output = "";
		String[] s = DataInput_Stream.read().split(",");
		String[] s1 = s[1].split("");
		for(int i = 0; i < s1.length; i++){
			output += "*";
		}
		
		return output;
	}

}
