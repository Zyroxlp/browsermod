package com.example.browsermod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public CommonProxy() {
        System.out.println("CommonProxy initialized");
    }

    public void preInit(FMLPreInitializationEvent event) {
        // Server-side or shared initialization code
    }

    public void init(FMLInitializationEvent event) {
        // Server-side or shared initialization code
    }

}
