/*
 * Minecraft / Telegram Integrator
 * By Kyngo (github.com/kyngo)
 * Copyright (c) 2018
 */

package net.kyngo.mctgintegrator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author kyngo
 */
public class MessageListener implements Listener {
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        String player = event.getPlayer().getName();
        String payload = String.format("[MC] %s : %s", player, message);
        TelegramWrapper tg = new TelegramWrapper();
        tg.Send(payload);
    }
}
