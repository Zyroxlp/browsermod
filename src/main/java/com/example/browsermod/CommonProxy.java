package com.example.browsermod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.montoyo.mcef.api.API;
import net.montoyo.mcef.api.IBrowser;

public abstract class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        // Server-side or shared initialization code
    }

    public void init(FMLInitializationEvent event) {
        // Server-side or shared initialization code
    }

    public API getAPI() {
        // Server-side logic doesn't need API
        return null;
    }

    public abstract void onAddressChange(IBrowser browser, String url);

    public abstract void onTitleChange(IBrowser browser, String title);

    public abstract void onTooltip(IBrowser browser, String text);

    public abstract void onStatusMessage(IBrowser browser, String value);
}
