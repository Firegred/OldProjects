package me.Firegred.NoteBlock;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_10_R1.NBTTagCompound;

public class Reflection {
	
	public static ItemStack setMonsterEggType(ItemStack item, EntityType type) {
		net.minecraft.server.v1_10_R1.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound idTag = new NBTTagCompound();
        idTag.setString("id", type.getName());
        NBTTagCompound tag = stack.hasTag() ? stack.getTag() : new NBTTagCompound();
        tag.set("EntityTag", idTag);
        stack.setTag(tag);
        return CraftItemStack.asBukkitCopy(stack);
    }
}
