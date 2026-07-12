package wtf.flonxi;

import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getOnlinePlayers().forEach(this::handleNetherFire);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        handleNetherFire(e.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        handleNetherFire(e.getPlayer());
    }

    private void handleNetherFire(Player p) {
        if (p.getWorld().getEnvironment() == World.Environment.NETHER) {
            p.addPotionEffect(new PotionEffect(
                    PotionEffectType.FIRE_RESISTANCE,
                    Integer.MAX_VALUE,
                    0,
                    true,
                    false));
        } else {
            p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
    }

    @Override
    public void onDisable() {
        getServer().getOnlinePlayers().forEach(p -> p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE));
    }
}