package me.pronil.afk.util;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static me.pronil.afk.main.pl;

public class AfkSession {
    private static final Set<AfkSession> afkSessions = new HashSet<>();

    private final Player player;

    private final UUID uuid;

    private boolean isAfk;

    private int time;

    private int kickTimeout;

    private boolean isKick;

    public static Set<AfkSession> getAfkSessions() {
        return afkSessions;
    }

    public Player getPlayer() {
        return this.player;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public boolean isAfk() {
        return this.isAfk;
    }

    public int getTime() {
        return this.time;
    }

    public int getKickTimeout() {
        return this.kickTimeout;
    }

    public boolean isKick() {
        return this.isKick;
    }

    public void setKick(boolean isKick) {
        this.isKick = isKick;
    }

    public AfkSession(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.isAfk = false;
        this.time = 0;
        this.kickTimeout = 10;
        this.isKick = false;
        afkSessions.add(this);
    }

    public void addTime() {
        String kick_before_time= pl.getConfig().getString("kick-after-inactivity-time") ;
        this.time++;
        if (this.time >= Integer.parseInt(kick_before_time)) {
            if (!this.isKick) {
                this.isKick = true;
                this.player.sendMessage(TextUtil.colorize(pl.getConfig().getString("player-kick-warning")));
            }
            this.kickTimeout--;
        }
    }

    public void setAfk(boolean bool) {
        if (!bool) {
            this.time = 0;
            this.isKick = false;
            this.kickTimeout = 10;
        }
        this.isAfk = bool;
    }

    public static AfkSession getAfkSession(Player player) {
        return afkSessions.stream().filter(as -> as.getUuid().equals(player.getUniqueId())).findFirst().orElse(null);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AfkSession that = (AfkSession)o;
        return this.uuid.equals(that.uuid);
    }

    public int hashCode() {
        return Objects.hash(this.uuid);
    }
}

