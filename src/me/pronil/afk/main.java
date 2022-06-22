package me.pronil.afk;

import me.pronil.afk.listener.AfkSessionHandler;
import me.pronil.afk.util.AfkSessionTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    public Server server = getServer();
    String pluginName = getDescription().getName();
    String pluginVersion = getDescription().getVersion();
    public static main pl ;
    private static main instance;


    public void onEnable() {
        pl=this;
        instance = this;
        registerEvents();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        init();
        (new AfkSessionTask()).start();
    }
    public static main getInstance() {
        return instance;
    }
    private void registerEvents() {
        PluginManager manager = this.server.getPluginManager();
        manager.registerEvents(new AfkSessionHandler(), this);

    }

    private void init() {


            Bukkit.getConsoleSender().sendMessage("-------------------------------------------------");
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r "));
            Bukkit.getConsoleSender().sendMessage(this.pluginName + " v" + this.pluginVersion);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r "));
            Bukkit.getConsoleSender().sendMessage("Successfully Loaded");
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r "));
            Bukkit.getConsoleSender().sendMessage("Author - Pro_Nil");
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r "));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Running Java &f" + System.getProperty("java.version")));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Running &f" + Bukkit.getServer().getName() + " &7fork &fv" + Bukkit.getServer().getBukkitVersion()));
            Bukkit.getConsoleSender().sendMessage("-------------------------------------------------");
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r "));

    }
}
