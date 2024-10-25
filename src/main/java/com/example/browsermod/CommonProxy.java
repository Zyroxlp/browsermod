package com.example.browsermod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.montoyo.mcef.api.API;
import net.montoyo.mcef.api.IBrowser;

public class CommonProxy {

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
}
