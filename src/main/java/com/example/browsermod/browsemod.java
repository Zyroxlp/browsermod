package com.example.browsermod;
import fr.aym.acsguis.api.ACsGuiApi;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

@Mod(modid = browsemod.MODID, name = browsemod.NAME, version = browsemod.VERSION)
public class browsemod {


    @SidedProxy(clientSide = "com.example.browsermod.ClientProxy", serverSide = "com.example.browsermod.CommonProxy")
    public static CommonProxy proxy;

    public static final String MODID = "browser";
    public static final String NAME = "Test Mod";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        ACsGuiApi.registerStyleSheetToPreload(TestGui.RESOURCE_LOCATION);
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println(NAME + " is loading!");
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);

    }
}
