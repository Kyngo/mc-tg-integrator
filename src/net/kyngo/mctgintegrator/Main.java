/*
 * Minecraft / Telegram Integrator
 * By Kyngo (github.com/kyngo)
 * Copyright (c) 2018
 */

package net.kyngo.mctgintegrator;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author kyngo
 */
public class Main extends JavaPlugin {    
    @Override
    public void onEnable() {
        System.out.println("=================================================");
        System.out.println(" Telegram / Minecraft Integrator is now running! ");
        System.out.println("=================================================");
        getServer().getPluginManager().registerEvents(new MessageListener(), this);
        return;
    }

    @Override
    public void onDisable() {
        System.out.println("=======================================================");
        System.out.println(" Telegram / Minecraft Integrator stopeed successfully! ");
        System.out.println("=======================================================");
        return;
    }

}
