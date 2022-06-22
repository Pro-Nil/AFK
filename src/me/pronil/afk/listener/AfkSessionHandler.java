package me.pronil.afk.listener;

import me.pronil.afk.util.AfkSession;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AfkSessionHandler implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        new AfkSession(player);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        AfkSession.getAfkSessions().remove(AfkSession.getAfkSession(player));
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onMove(PlayerMoveEvent e) {
        AfkSession.getAfkSession(e.getPlayer()).setAfk(false);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player))
            return;
        AfkSession.getAfkSession((Player)e.getEntity()).setAfk(false);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        AfkSession.getAfkSession(e.getPlayer()).setAfk(false);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerInteract(PlayerInteractEvent e) {
        AfkSession.getAfkSession(e.getPlayer()).setAfk(false);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBlockPlace(BlockPlaceEvent e) {
        AfkSession.getAfkSession(e.getPlayer()).setAfk(false);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBlockBreak(BlockBreakEvent e) {
        AfkSession.getAfkSession(e.getPlayer()).setAfk(false);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        AfkSession.getAfkSession(e.getPlayer()).setAfk(false);
    }
}

