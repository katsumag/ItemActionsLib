package me.katsumag.itemactionslib;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Utils {

    public static boolean isJumping(Player p) {

        Vector velocity = p.getVelocity();
        // Check if the player is moving "up"
        if (velocity.getY() > 0)
        {
            // Default jump velocity
            double jumpVelocity = 0.42; // Default jump velocity
            if (p.hasPotionEffect(PotionEffectType.JUMP))
            {
                // If player has jump potion add it to jump velocity
                jumpVelocity += (double) ((float) Objects.requireNonNull(p.getPotionEffect(PotionEffectType.JUMP)).getAmplifier() + 1) * 0.1F;
            }
            // Check if player is not on ladder and if jump velocity calculated is equals to player Y velocity
            return p.getLocation().getBlock().getType() != Material.LADDER && Double.compare(velocity.getY(), jumpVelocity) == 0;
        }
        return false;
    }

}
