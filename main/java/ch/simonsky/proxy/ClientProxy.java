package ch.simonsky.proxy;

import static ch.simonsky.Main.*;

import org.lwjgl.input.Keyboard;

import ch.simonsky.event.Events;
import ch.simonsky.keybinding.KeyHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends ServerProxy {
	
	//Key Bindings
	public static KeyBinding keybinding = new KeyBinding("keyBinding.key", Keyboard.KEY_M, "keyBinding.category");
	
	@Override
	public void registerRenderThings() {
		//KeyBinding Registry
		ClientRegistry.registerKeyBinding(keybinding);
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		
		//Events
		MinecraftForge.EVENT_BUS.register(new Events());
	}

}
