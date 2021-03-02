package me.blha303;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Mossifier extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		ItemStack item = event.getItem();
		if (item == null) return;
		Block block = event.getClickedBlock();
		if (block == null) return;

		if (item.getType() == Material.BONE_MEAL) { // If item is bonemeal...
			if (block.getType() == Material.COBBLESTONE) { // ... and the block clicked is cobblestone...
				block.setType(Material.MOSSY_COBBLESTONE); // ... set the block to moss stone,
				if (p.getGameMode().equals(GameMode.CREATIVE)) {
					return;
				}
				item.setAmount(item.getAmount() - 1); // and take one bonemeal from the player
				p.getInventory().setItemInHand(item);
				return;
			} else if (block.getType() == Material.STONE_BRICKS) { // ... and the block clicked is stone bricks... 
				block.setType(Material.MOSSY_STONE_BRICKS);
				if (p.getGameMode().equals(GameMode.CREATIVE)) {
					return;
				}
				item.setAmount(item.getAmount() - 1); // and take one bonemeal from the player.
				p.getInventory().setItemInHand(item);
				return;
			}
		}
		return;
	}
}
