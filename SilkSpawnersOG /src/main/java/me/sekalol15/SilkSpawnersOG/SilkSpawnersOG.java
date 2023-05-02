package me.sekalol15.SilkSpawnersOG;
import me.sekalol15.SilkSpawnersOG.out.production.SilkSpawnersOG.me.sekalol15.SilkSpawnersOG.rightClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SilkSpawnersOG extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new breakBlockListener(), this);
        this.getServer().getPluginManager().registerEvents(new rightClickListener(), this);
        getServer().getConsoleSender().sendMessage("functioning");
    }
}

