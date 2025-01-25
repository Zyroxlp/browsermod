package com.example.browsermod;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.utils.GuiTextureSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.util.*;
import java.util.List;

public class phoneOff extends GuiFrame {


    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation("browser", "css/test.css");
    private final ResourceLocation iconTexture = new ResourceLocation("minecraft", "textures/items/apple.png");

    public static GuiPanel phoneDisplay;
    public static GuiPanel phoneButtonVolumeUp;
    public static GuiPanel phoneButtonVolumeDown;
    public static GuiPanel phoneButtonStart;
    private static HashMap<EntityPlayer, GuiFrame> openPhoneHashMap = new HashMap<>();

    private final TextFormatting[] rainbowColors = {
            TextFormatting.RED,
            TextFormatting.GOLD,
            TextFormatting.YELLOW,
            TextFormatting.GREEN,
            TextFormatting.BLUE,
            TextFormatting.DARK_PURPLE,
            TextFormatting.LIGHT_PURPLE
    };
    //private GuiLabel title;

    private GuiButton rainbowButton;

    private int colorIndex = 0;

    public phoneOff() {
        super(new GuiScaler.Identity());
        style.setBackgroundColor(Color.TRANSLUCENT);
        System.out.println("Hey ich bin eine GUi");
        GuiPanel phoneBody = new GuiPanel(0, 0, 0, 0);
        phoneBody.setCssClass("phoneBody");

        GuiPanel phoneButtonVolumeUp = new GuiPanel();


        GuiPanel phoneDisplayCamera = new GuiPanel();
        phoneDisplayCamera.setCssClass("phoneDisplayCamera");
        phoneBody.add(phoneDisplayCamera);

        GuiLabel phoneDisplayLogo = new GuiLabel("HumanPhone");
        phoneDisplayLogo.setCssClass("phoneDisplayLogo");
        phoneBody.add(phoneDisplayLogo);

        phoneDisplay = new GuiPanel();
        phoneDisplay.setCssClass("phoneDisplay");
        phoneBody.add(phoneDisplay);

        GuiPanel iconPanel = new GuiPanel(); // Position und Größe des Icons
        iconPanel.setCssClass("iconpanel");
        iconPanel.getStyle().setTexture(new GuiTextureSprite(iconTexture)); // Textur setzen
        iconPanel.addClickListener((x, y, button) -> System.out.println("Icon wurde geklickt!")); // Klickaktion

        phoneButtonVolumeUp = new GuiPanel();
        phoneButtonVolumeUp.setCssClass("phoneButtonVolumeUp");
        phoneBody.add(phoneButtonVolumeUp);

        phoneButtonVolumeDown = new GuiPanel();
        phoneButtonVolumeDown.setCssClass("phoneButtonVolumeDown");
        phoneBody.add(phoneButtonVolumeDown);

        phoneButtonStart = new GuiPanel();
        phoneButtonStart.setCssClass("phoneButtonStart");
        phoneBody.add(phoneButtonStart);

        phoneBody.add(iconPanel);

        //title = new GuiLabel(0, 0, 0, 0, "UwU");
       // title.setCssClass("title");
       // phoneBody.add(title);

        // Panel zur GUI hinzufügen
        add(phoneBody);

        // Regenbogenfarben starten
        //startRainbowEffect();

        openPhoneHashMap.put(ClientProxy.mc.player,this);
    }

    private void startRainbowEffect() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Nächste Farbe aus dem Regenbogen-Array setzen
                //title.setText(rainbowColors[colorIndex] + "UwU"); // Dynamischen Text mit Farbe setzen
                colorIndex = (colorIndex + 1) % rainbowColors.length; // Nächste Farbe
            }
        }, 0, 500); // Alle 500ms die Farbe wechseln
    }


    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(phoneOff.RESOURCE_LOCATION);
    }

    @Override
    public boolean doesPauseGame() {
        return false;
    }

    @Override
    public boolean needsCssReload() {
        return true;
    }

}
