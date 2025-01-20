package com.example.browsermod;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.panel.GuiTabbedPane;
import fr.aym.acsguis.component.textarea.GuiLabel;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class PhoneStartScreen extends GuiFrame {
    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation("browser", "css/startscreen.css");
    public PhoneStartScreen() {
        super(new GuiScaler.Identity());
        GuiPanel frame_0 = new GuiPanel();
        frame_0.setCssClass("frame_0");

        add(frame_0);
        GuiButton button_0 = new GuiButton("Home");

        add(button_0);
    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(
                new ResourceLocation("dynamxmod:css/admin.css")
        );
    }
}

