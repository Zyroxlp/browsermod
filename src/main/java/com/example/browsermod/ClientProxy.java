package com.example.browsermod;

import fr.aym.acsguis.api.ACsGuiApi;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    private KeyBinding key;
    private Minecraft mc = Minecraft.getMinecraft();

    public static ClientProxy INSTANCE;

    public ClientProxy() {
        System.out.println("ClientProxy initialized");
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("ClientProxy preInit called");
        super.preInit(event);
        INSTANCE = this;

    }

    @Override
    public void init(FMLInitializationEvent event) {
        // Client-specific initialization
        key = new KeyBinding("Open Browser", Keyboard.KEY_F9, "key.categories.custom");
        ClientRegistry.registerKeyBinding(key);
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println("Keybinding initialized");
    }

    @SubscribeEvent
    public void opengui(InputEvent.KeyInputEvent event){
        if(key.isPressed()){
            ACsGuiApi.asyncLoadThenShowGui("PhoneGui",PhoneStartScreen::new);
        }
    }
}
