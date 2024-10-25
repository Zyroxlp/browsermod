package com.example.browsermod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.montoyo.mcef.api.*;
import net.montoyo.mcef.utilities.Log;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy implements IJSQueryHandler, IDisplayHandler {

    private KeyBinding key;
    private Minecraft mc = Minecraft.getMinecraft();
    private static BrowserScreen backup = null;
    public static ScreenCfg hudBrowser = null;
    private API api;

    public static ClientProxy INSTANCE;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        INSTANCE = this;

        // Client-specific pre-initialization code
        api = MCEFApi.getAPI();
        if (api != null) {
            api.registerDisplayHandler(this);
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        // Client-specific initialization
        key = new KeyBinding("Open Browser", Keyboard.KEY_F10, "key.categories.misc");
        ClientRegistry.registerKeyBinding(key);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public API getAPI() {
        return api;
    }

    public static void setBackup(BrowserScreen bu) {
        backup = bu;
    }

    public static boolean hasBackup() {
        return backup != null;
    }

    public void showScreen(String url) {
        if (mc.currentScreen instanceof BrowserScreen) {
            ((BrowserScreen) mc.currentScreen).loadURL(url);
        } else if (hasBackup()) {
            mc.displayGuiScreen(backup);
            backup.loadURL(url);
            backup = null;
        } else {
            BrowserScreen newScreen = new BrowserScreen(url);
            mc.displayGuiScreen(newScreen);
            newScreen.loadURL(url);
        }
    }

    public IBrowser getBrowser() {
        if(mc.currentScreen instanceof BrowserScreen)
            return ((BrowserScreen) mc.currentScreen).browser;
        else if(backup != null)
            return backup.browser;
        else
            return null;
    }

    @SubscribeEvent
    public void onTick(TickEvent ev) {
        if (ev.phase == TickEvent.Phase.START && ev.side == Side.CLIENT && ev.type == TickEvent.Type.CLIENT) {
            if (key.isPressed() && !(mc.currentScreen instanceof BrowserScreen)) {
                mc.player.sendMessage(new TextComponentString("Opening Browser..."));
                showScreen("https://www.google.com/");
                backup = null;
            }
        }
    }

    @Override
    public boolean handleQuery(IBrowser b, long queryId, String query, boolean persistent, IJSQueryCallback cb) {
        if(b != null && query.equalsIgnoreCase("username")) {
            if(b.getURL().startsWith("mod://")) {
                //Only allow MCEF URLs to get the player's username to keep his identity secret

                mc.addScheduledTask(() -> {
                    //Add this to a scheduled task because this is NOT called from the main Minecraft thread...

                    try {
                        String name = mc.getSession().getUsername();
                        cb.success(name);
                    } catch(Throwable t) {
                        cb.failure(500, "Internal error.");
                        Log.warning("Could not get username from JavaScript:");
                        t.printStackTrace();
                    }
                });
            } else
                cb.failure(403, "Can't access username from external page");

            return true;
        }

        return false;
    }

    @Override
    public void cancelQuery(IBrowser iBrowser, long l) {

    }

    @SubscribeEvent
    public void onDrawHUD(RenderGameOverlayEvent.Post ev) {
        if (hudBrowser != null) {
            hudBrowser.drawScreen(0, 0, 0.f);
        }
    }

    // Implementing IDisplayHandler methods
    @Override
    public void onAddressChange(IBrowser browser, String url) {
        // Called by MCEF if a browser's URL changes. Forward this event to the screen.
        if(mc.currentScreen instanceof BrowserScreen)
            ((BrowserScreen) mc.currentScreen).onUrlChanged(browser, url);
        else if(hasBackup())
            backup.onUrlChanged(browser, url);
    }

    @Override
    public void onTitleChange(IBrowser browser, String title) {
        // Implement any behavior when the browser title changes (optional)
    }

    @Override
    public void onTooltip(IBrowser browser, String text) {
        // Implement any behavior for showing tooltips (optional)
    }

    @Override
    public void onStatusMessage(IBrowser browser, String value) {
        // Implement any behavior for status messages (optional)
    }
}
