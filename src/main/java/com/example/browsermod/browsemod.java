package com.example.browsermod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import net.montoyo.mcef.api.*;



@Mod(modid = "browser", name = "browser", version = "1.0")
public class browsemod implements IDisplayHandler, IJSQueryHandler {

    @SidedProxy(clientSide = "com.example.browsermod.ClientProxy", serverSide = "com.example.browsermod.CommonProxy",modId = "browser")
    public static CommonProxy proxy;

    public static browsemod INSTANCE = new browsemod();;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);  // Handle client/server-specific pre-initialization
    }



    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);  // Handle client/server-specific initialization
    }

    public API getAPI() {
        return proxy.getAPI();
    }

    @Override
    public void onAddressChange(IBrowser iBrowser, String s) {

    }

    @Override
    public void onTitleChange(IBrowser iBrowser, String s) {

    }

    @Override
    public void onTooltip(IBrowser iBrowser, String s) {

    }

    @Override
    public void onStatusMessage(IBrowser iBrowser, String s) {

    }

    @Override
    public boolean handleQuery(IBrowser iBrowser, long l, String s, boolean b, IJSQueryCallback ijsQueryCallback) {
        return false;
    }

    @Override
    public void cancelQuery(IBrowser iBrowser, long l) {

    }
}
