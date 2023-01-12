package me.mattyhd0.chatcolor.listener;

import me.mattyhd0.chatcolor.CPlayer;
import me.mattyhd0.chatcolor.configuration.Config;
import me.mattyhd0.chatcolor.pattern.api.IPattern;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(final AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        CPlayer cPlayer = new CPlayer(player);
        IPattern pattern = cPlayer.getPattern();
        cPlayer.setLastMessages(event.getMessage());

        if (pattern != null) {

            boolean showPatternIfHasPerm = Config.getBoolean("config.show-pattern-only-if-has-permissions");
            String message = ChatColor.stripColor(event.getMessage());
            String coloredMessage = pattern.getText(message);

            if (showPatternIfHasPerm && cPlayer.canUsePattern(pattern)) {
                event.setMessage(coloredMessage);
                cPlayer.setLastMessages(coloredMessage);
            } else if (!showPatternIfHasPerm) {
                event.setMessage(coloredMessage);
                cPlayer.setLastMessages(coloredMessage);
            }

        }

    }

}