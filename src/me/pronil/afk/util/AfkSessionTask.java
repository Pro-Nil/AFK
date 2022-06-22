package me.pronil.afk.util;

import me.pronil.afk.main;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AfkSessionTask extends BukkitRunnable {
    public AfkSessionTask() {
        (new AfkNoticeTask()).start();
    }

    public void run() {
        AfkSession.getAfkSessions().forEach(as -> {
            if (!as.isAfk()) {
                as.setAfk(true);
            } else {
                as.addTime();
            }
        });
    }

    public void start() {
        runTaskTimer((Plugin) main.getInstance(), 0L, 20L);
    }
}
