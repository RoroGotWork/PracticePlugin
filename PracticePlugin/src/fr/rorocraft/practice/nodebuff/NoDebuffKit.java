package fr.rorocraft.practice.nodebuff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.rorocraft.practice.Main;

public class NoDebuffKit {

	
	 public ItemStack makeItem(Material m, int amount) {
		 ItemStack it = new ItemStack(m, amount);
		
		 return it;
	 }
	 
	 public ItemStack makeItem(Material m, int amount, byte data) {
		 ItemStack it = new ItemStack(m, amount, data);
		
		 return it;
	 }
	 
	 public ItemStack makeItem(Material m, int amount, Enchantment enchant1, int level) {
		 ItemStack it = new ItemStack(m, amount);
		 it.addEnchantment(enchant1, level);
		 return it;
	 }
	 
	 public ItemStack makeItem(Material m, int amount, Enchantment enchant1, int level, Enchantment enchant2, int level2 ) {
		 ItemStack it = new ItemStack(m);
		 it.addEnchantment(enchant1, level);
		 it.addEnchantment(enchant2, level2);
		 return it;
	 }
	 
	 public ItemStack makeItem(Material m, int amount, Enchantment enchant1, int level, Enchantment enchant2, int level2 , Enchantment enchant3, int level3 ) {
		 ItemStack it = new ItemStack(m);
		 it.addEnchantment(enchant1, level);
		 it.addEnchantment(enchant2, level2);
		 it.addEnchantment(enchant3, level3);
		 
		 return it;
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	public void addKit(Player  p, Main main) {
		 p.getInventory().clear();
			
			if(main.dataConfig.get("kit." + p.getUniqueId().toString() +".nodebuff.items") != null) {
				
				// loadKit(p, main);

				ItemStack[] items = ((List<ItemStack>) main.dataConfig.get("kit." + p.getUniqueId().toString() +".nodebuff.items")).toArray(new ItemStack[0]);
				ItemStack[] armor = ((List<ItemStack>) main.dataConfig.get("kit." + p.getUniqueId().toString() +".nodebuff.armor")).toArray(new ItemStack[0]);
				
				p.getInventory().setContents(items);
				p.getInventory().setArmorContents(armor);
	 } else {
		 
		 p.getInventory().setItem(0, makeItem(Material.DIAMOND_SWORD, 1, Enchantment.DAMAGE_ALL, 3, Enchantment.DURABILITY, 3, Enchantment.FIRE_ASPECT, 2));
		 
		 // Fire resistance 8:00
		 p.getInventory().setItem(1, makeItem(Material.POTION, 1, (byte) 8259));
		 p.getInventory().setItem(16, makeItem(Material.POTION, 1, (byte) 8259));
		 
		 // speed 1:30
		 p.getInventory().setItem(2,  makeItem(Material.POTION,  (byte) 8206));
		 p.getInventory().setItem(17,  makeItem(Material.POTION,  (byte) 8206));
		 p.getInventory().setItem(26,  makeItem(Material.POTION,  (byte) 8206));
		 p.getInventory().setItem(35,  makeItem(Material.POTION,  (byte) 8206));
		 
		 //ender pearl
		 p.getInventory().setItem(8, makeItem(Material.ENDER_PEARL, 16));
		 
		 // heal
		 for(int i = 0; i < 36; i ++) {
			if(p.getInventory().getItem(i) == null) {
				p.getInventory().setItem(i, makeItem(Material.POTION, 1, (byte) 16389));
			}
		 }
		 
		 
	 }
			
	 
	 }
	 
}
