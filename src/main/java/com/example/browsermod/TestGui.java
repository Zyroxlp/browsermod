package com.example.browsermod;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.utils.GuiTextureSprite;
import net.minecraft.util.ResourceLocation;

import net.minecraft.util.text.TextFormatting;
import org.lwjgl.Sys;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestGui extends GuiFrame
{
    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation("browser", "css/test.css");
    private final ResourceLocation iconTexture = new ResourceLocation("minecraft", "textures/items/apple.png");

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

    private GuiLabel load;
    public TestGui()
    {
        super(new GuiScaler.Identity());
        style.setBackgroundColor(Color.TRANSLUCENT);
        System.out.println("Hey ich bin eine GUi");
        setCssClass("home");
        GuiPanel PD = new GuiPanel(0, 0, 300, 200);
        PD.setCssId("PD");



        GuiPanel iconPanel = new GuiPanel(50, 50, 0, 0); // Position und Größe des Icons
        iconPanel.setCssId("icon_panel");
        iconPanel.getStyle().setTexture(new GuiTextureSprite(iconTexture, 0, 0, 10, 10)); // Textur setzen
        iconPanel.addClickListener((x, y, button) -> System.out.println("Icon wurde geklickt!")); // Klickaktion

        PD.add(iconPanel);

        //title = new GuiLabel(0, 0, 0, 0, "UwU");
       // title.setCssClass("title");
       // PD.add(title);

        // Panel zur GUI hinzufügen
        add(PD);

        // Regenbogenfarben starten
        //startRainbowEffect();
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
        return Arrays.asList(TestGui.RESOURCE_LOCATION);
    }

    @Override
    public boolean doesPauseGame() {
        return false;
    }


}
