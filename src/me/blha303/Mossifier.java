package me.blha303;

import java.util.HashSet;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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

	public Block getFirstNotAir(List<Block> lob) {
		for (Block b : lob) {
			if (b.getType() != Material.AIR) {
				return b;
			}
		}
		return null;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		ItemStack item = event.getItem();
		// Block block = event.getClickedBlock();
		HashSet<Byte> ignore = new HashSet<Byte>();
		ignore.add((byte) Material.AIR.hashCode());
		List<Block> los = p.getLineOfSight(null, 10);
		Block block = getFirstNotAir(los);
		
		if (block == null) {
			return;
		}

		if (item.getTypeId() == 351 && item.getDurability() == 15) {
			if (block.getTypeId() == 4) {
				block.setTypeId(48);
				item.setAmount(item.getAmount() - 1);
				p.getInventory().setItemInHand(item);
				return;
			} else if (block.getTypeId() == 9) {
				Location lploc = block.getRelative(BlockFace.UP).getLocation();
				lploc.getBlock().setTypeId(111);
				return;
			} else if (block.getTypeId() == 98 && block.getData() == 0) { 
				block.setData((byte) 1);
				item.setAmount(item.getAmount() - 1);
				p.getInventory().setItemInHand(item);
				return;
			} else { return; }
		}
		return;
	}
}
