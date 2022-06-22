package me.pronil.afk.util;

import me.pronil.afk.main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static me.pronil.afk.main.pl;

public class AfkNoticeTask extends BukkitRunnable {
    public void run() {
        AfkSession.getAfkSessions().stream().filter(as -> (as.isAfk() && as.isKick())).forEach(as -> {
            Player player = as.getPlayer();
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
            if (as.getKickTimeout() <= 0) {
                as.setKick(false);
                player.sendMessage(TextUtil.colorize(pl.getConfig().getString("message-on-kick")));
                kickPlayer(player);
            }
        });
    }

    public void start() {
        runTaskTimer(main.getInstance(), 0L, 2L);
    }

    private void kickPlayer(Player player) {
        if(pl.getConfig().getBoolean("BungeeCord.Support")) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF(pl.getConfig().getString("BungeeCord.afk-kick-server"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            player.sendPluginMessage(main.getInstance(), "BungeeCord", b.toByteArray());
            main.getInstance().getLogger().info(String.format("%s were kicked for inactivity", player.getName()));
        } else if (pl.getConfig().getBoolean("Spigot.Support")) {
            player.kickPlayer(TextUtil.colorize(pl.getConfig().getString("message-on-kick")));
        }

    }
    }


