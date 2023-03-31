package com.rrayy.spawn.util;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class gui {
    private Inventory inv;

    public gui(JavaPlugin plugin, int rows, String title){
        inv = plugin.getServer().createInventory(null, rows*9, title);
    }

    public void showto(Player player){
        player.openInventory(inv);
    }

    public void setitem(int column, int row, ItemStack item){
        inv.setItem(row*column - 1, item);
    }

    public boolean isclicked(InventoryClickEvent event){
        if (event.getClickedInventory()==inv) return true;
        else return false;
    }
}
