package com.m3z0id.gunGame.kit;

import com.m3z0id.gunGame.utils.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Kits {
    public static final KitCreator KIT_1 = new KitCreator(null, null, null, null, new ItemStack(Material.WOODEN_AXE));
    public static final KitCreator KIT_2 = new KitCreator(null, null, null, null, new ItemStack(Material.WOODEN_SWORD));
    public static final KitCreator KIT_3 = new KitCreator(null, new ItemStack(Material.LEATHER_CHESTPLATE), null, null, KIT_2.getWeapon());
    public static final KitCreator KIT_4 = new KitCreator(null, KIT_3.getChestplate(), new ItemStack(Material.LEATHER_LEGGINGS), null, KIT_3.getWeapon());
    public static final KitCreator KIT_5 = new KitCreator(null, KIT_4.getChestplate(), KIT_4.getLeggings(), new ItemStack(Material.LEATHER_BOOTS), KIT_4.getWeapon());
    public static final KitCreator KIT_6 = new KitCreator(new ItemStack(Material.LEATHER_HELMET), KIT_5.getChestplate(), KIT_5.getLeggings(), KIT_5.getBoots(), KIT_5.getWeapon());
    public static final KitCreator KIT_7 = new KitCreator(KIT_6.getHelmet(), KIT_6.getChestplate(), KIT_6.getLeggings(), KIT_6.getBoots(), CustomItemStack.newstack(new ItemStack(Material.WOODEN_AXE), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_8 = new KitCreator(KIT_7.getHelmet(), KIT_7.getChestplate(), KIT_7.getLeggings(), KIT_7.getBoots(), CustomItemStack.newstack(new ItemStack(Material.WOODEN_SWORD), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_9 = new KitCreator(KIT_8.getHelmet(), CustomItemStack.newstack(new ItemStack(Material.LEATHER_CHESTPLATE), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_8.getLeggings(), KIT_8.getBoots(), KIT_8.getWeapon());
    public static final KitCreator KIT_10 = new KitCreator(KIT_9.getHelmet(), KIT_9.getChestplate(), CustomItemStack.newstack(new ItemStack(Material.LEATHER_LEGGINGS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_9.getBoots(), KIT_9.getWeapon());
    public static final KitCreator KIT_11 = new KitCreator(KIT_10.getHelmet(), KIT_10.getChestplate(), KIT_10.getLeggings(), CustomItemStack.newstack(new ItemStack(Material.LEATHER_BOOTS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_10.getWeapon());
    public static final KitCreator KIT_12 = new KitCreator(CustomItemStack.newstack(new ItemStack(Material.LEATHER_HELMET), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_11.getChestplate(), KIT_11.getLeggings(), KIT_11.getBoots(), KIT_11.getWeapon());

    public static final KitCreator KIT_13 = new KitCreator(KIT_12.getHelmet(), KIT_12.getChestplate(), KIT_12.getLeggings(), KIT_12.getBoots(), new ItemStack(Material.GOLDEN_AXE));
    public static final KitCreator KIT_14 = new KitCreator(KIT_13.getHelmet(), KIT_13.getChestplate(), KIT_13.getLeggings(), KIT_13.getBoots(), new ItemStack(Material.GOLDEN_SWORD));
    public static final KitCreator KIT_15 = new KitCreator(KIT_14.getHelmet(), new ItemStack(Material.GOLDEN_CHESTPLATE), KIT_14.getLeggings(), KIT_14.getBoots(), KIT_14.getWeapon());
    public static final KitCreator KIT_16 = new KitCreator(KIT_15.getHelmet(), KIT_15.getChestplate(), new ItemStack(Material.GOLDEN_LEGGINGS), KIT_15.getBoots(), KIT_15.getWeapon());
    public static final KitCreator KIT_17 = new KitCreator(KIT_16.getHelmet(), KIT_16.getChestplate(), KIT_16.getLeggings(), new ItemStack(Material.GOLDEN_BOOTS), KIT_16.getWeapon());
    public static final KitCreator KIT_18 = new KitCreator(new ItemStack(Material.GOLDEN_HELMET), KIT_17.getChestplate(), KIT_17.getLeggings(), KIT_17.getBoots(), KIT_17.getWeapon());
    public static final KitCreator KIT_19 = new KitCreator(KIT_18.getHelmet(), KIT_18.getChestplate(), KIT_18.getLeggings(), KIT_18.getBoots(), CustomItemStack.newstack(new ItemStack(Material.GOLDEN_AXE), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_20 = new KitCreator(KIT_19.getHelmet(), KIT_19.getChestplate(), KIT_19.getLeggings(), KIT_19.getBoots(), CustomItemStack.newstack(new ItemStack(Material.GOLDEN_SWORD), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_21 = new KitCreator(KIT_20.getHelmet(), CustomItemStack.newstack(new ItemStack(Material.GOLDEN_CHESTPLATE), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_20.getLeggings(), KIT_20.getBoots(), KIT_20.getWeapon());
    public static final KitCreator KIT_22 = new KitCreator(KIT_21.getHelmet(), KIT_21.getChestplate(), CustomItemStack.newstack(new ItemStack(Material.GOLDEN_LEGGINGS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_21.getBoots(), KIT_21.getWeapon());
    public static final KitCreator KIT_23 = new KitCreator(KIT_22.getHelmet(), KIT_22.getChestplate(), KIT_22.getLeggings(), CustomItemStack.newstack(new ItemStack(Material.GOLDEN_BOOTS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_22.getWeapon());
    public static final KitCreator KIT_24 = new KitCreator(CustomItemStack.newstack(new ItemStack(Material.GOLDEN_HELMET), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_23.getChestplate(), KIT_23.getLeggings(), KIT_23.getBoots(), KIT_23.getWeapon());

    public static final KitCreator KIT_25 = new KitCreator(KIT_24.getHelmet(), KIT_24.getChestplate(), KIT_24.getLeggings(), KIT_24.getBoots(), new ItemStack(Material.STONE_AXE));
    public static final KitCreator KIT_26 = new KitCreator(KIT_25.getHelmet(), KIT_25.getChestplate(), KIT_25.getLeggings(), KIT_25.getBoots(), new ItemStack(Material.STONE_SWORD));
    public static final KitCreator KIT_27 = new KitCreator(KIT_26.getHelmet(), new ItemStack(Material.CHAINMAIL_CHESTPLATE), KIT_26.getLeggings(), KIT_26.getBoots(), KIT_26.getWeapon());
    public static final KitCreator KIT_28 = new KitCreator(KIT_27.getHelmet(), KIT_27.getChestplate(), new ItemStack(Material.CHAINMAIL_LEGGINGS), KIT_27.getBoots(), KIT_27.getWeapon());
    public static final KitCreator KIT_29 = new KitCreator(KIT_28.getHelmet(), KIT_28.getChestplate(), KIT_28.getLeggings(), new ItemStack(Material.CHAINMAIL_BOOTS), KIT_28.getWeapon());
    public static final KitCreator KIT_30 = new KitCreator(new ItemStack(Material.CHAINMAIL_HELMET), KIT_29.getChestplate(), KIT_29.getLeggings(), KIT_29.getBoots(), KIT_29.getWeapon());
    public static final KitCreator KIT_31 = new KitCreator(KIT_30.getHelmet(), KIT_30.getChestplate(), KIT_30.getLeggings(), KIT_30.getBoots(), CustomItemStack.newstack(new ItemStack(Material.STONE_AXE), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_32 = new KitCreator(KIT_31.getHelmet(), KIT_31.getChestplate(), KIT_31.getLeggings(), KIT_31.getBoots(), CustomItemStack.newstack(new ItemStack(Material.STONE_SWORD), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_33 = new KitCreator(KIT_32.getHelmet(), CustomItemStack.newstack(new ItemStack(Material.CHAINMAIL_CHESTPLATE), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_32.getLeggings(), KIT_32.getBoots(), KIT_32.getWeapon());
    public static final KitCreator KIT_34 = new KitCreator(KIT_33.getHelmet(), KIT_33.getChestplate(), CustomItemStack.newstack(new ItemStack(Material.CHAINMAIL_LEGGINGS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_33.getBoots(), KIT_33.getWeapon());
    public static final KitCreator KIT_35 = new KitCreator(KIT_34.getHelmet(), KIT_34.getChestplate(), KIT_34.getLeggings(), CustomItemStack.newstack(new ItemStack(Material.CHAINMAIL_BOOTS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_34.getWeapon());
    public static final KitCreator KIT_36 = new KitCreator(CustomItemStack.newstack(new ItemStack(Material.CHAINMAIL_HELMET), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_35.getChestplate(), KIT_35.getLeggings(), KIT_35.getBoots(), KIT_35.getWeapon());

    public static final KitCreator KIT_37 = new KitCreator(KIT_36.getHelmet(), KIT_36.getChestplate(), KIT_36.getLeggings(), KIT_36.getBoots(), new ItemStack(Material.IRON_AXE));
    public static final KitCreator KIT_38 = new KitCreator(KIT_37.getHelmet(), KIT_37.getChestplate(), KIT_37.getLeggings(), KIT_37.getBoots(), new ItemStack(Material.IRON_SWORD));
    public static final KitCreator KIT_39 = new KitCreator(KIT_38.getHelmet(), new ItemStack(Material.IRON_CHESTPLATE), KIT_38.getLeggings(), KIT_38.getBoots(), KIT_38.getWeapon());
    public static final KitCreator KIT_40 = new KitCreator(KIT_39.getHelmet(), KIT_39.getChestplate(), new ItemStack(Material.IRON_LEGGINGS), KIT_39.getBoots(), KIT_39.getWeapon());
    public static final KitCreator KIT_41 = new KitCreator(KIT_40.getHelmet(), KIT_40.getChestplate(), KIT_40.getLeggings(), new ItemStack(Material.IRON_BOOTS), KIT_40.getWeapon());
    public static final KitCreator KIT_42 = new KitCreator(new ItemStack(Material.IRON_HELMET), KIT_41.getChestplate(), KIT_41.getLeggings(), KIT_41.getBoots(), KIT_41.getWeapon());
    public static final KitCreator KIT_43 = new KitCreator(KIT_42.getHelmet(), KIT_42.getChestplate(), KIT_42.getLeggings(), KIT_42.getBoots(), CustomItemStack.newstack(new ItemStack(Material.IRON_AXE), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_44 = new KitCreator(KIT_43.getHelmet(), KIT_43.getChestplate(), KIT_43.getLeggings(), KIT_43.getBoots(), CustomItemStack.newstack(new ItemStack(Material.IRON_SWORD), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_45 = new KitCreator(KIT_44.getHelmet(), CustomItemStack.newstack(new ItemStack(Material.IRON_CHESTPLATE), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_44.getLeggings(), KIT_44.getBoots(), KIT_44.getWeapon());
    public static final KitCreator KIT_46 = new KitCreator(KIT_45.getHelmet(), KIT_45.getChestplate(), CustomItemStack.newstack(new ItemStack(Material.IRON_LEGGINGS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_45.getBoots(), KIT_45.getWeapon());
    public static final KitCreator KIT_47 = new KitCreator(KIT_46.getHelmet(), KIT_46.getChestplate(), KIT_46.getLeggings(), CustomItemStack.newstack(new ItemStack(Material.IRON_BOOTS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_46.getWeapon());
    public static final KitCreator KIT_48 = new KitCreator(CustomItemStack.newstack(new ItemStack(Material.IRON_HELMET), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_47.getChestplate(), KIT_47.getLeggings(), KIT_47.getBoots(), KIT_47.getWeapon());

    public static final KitCreator KIT_49 = new KitCreator(KIT_48.getHelmet(), KIT_48.getChestplate(), KIT_48.getLeggings(), KIT_48.getBoots(), new ItemStack(Material.DIAMOND_AXE));
    public static final KitCreator KIT_50 = new KitCreator(KIT_49.getHelmet(), KIT_49.getChestplate(), KIT_49.getLeggings(), KIT_49.getBoots(), new ItemStack(Material.DIAMOND_SWORD));
    public static final KitCreator KIT_51 = new KitCreator(KIT_50.getHelmet(), new ItemStack(Material.DIAMOND_CHESTPLATE), KIT_50.getLeggings(), KIT_50.getBoots(), KIT_50.getWeapon());
    public static final KitCreator KIT_52 = new KitCreator(KIT_51.getHelmet(), KIT_51.getChestplate(), new ItemStack(Material.DIAMOND_LEGGINGS), KIT_51.getBoots(), KIT_51.getWeapon());
    public static final KitCreator KIT_53 = new KitCreator(KIT_52.getHelmet(), KIT_52.getChestplate(), KIT_52.getLeggings(), new ItemStack(Material.DIAMOND_BOOTS), KIT_52.getWeapon());
    public static final KitCreator KIT_54 = new KitCreator(new ItemStack(Material.DIAMOND_HELMET), KIT_53.getChestplate(), KIT_53.getLeggings(), KIT_53.getBoots(), KIT_53.getWeapon());
    public static final KitCreator KIT_55 = new KitCreator(KIT_54.getHelmet(), KIT_54.getChestplate(), KIT_54.getLeggings(), KIT_54.getBoots(), CustomItemStack.newstack(new ItemStack(Material.DIAMOND_AXE), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_56 = new KitCreator(KIT_55.getHelmet(), KIT_55.getChestplate(), KIT_55.getLeggings(), KIT_55.getBoots(), CustomItemStack.newstack(new ItemStack(Material.DIAMOND_SWORD), Enchantment.DAMAGE_ALL));
    public static final KitCreator KIT_57 = new KitCreator(KIT_56.getHelmet(), CustomItemStack.newstack(new ItemStack(Material.DIAMOND_CHESTPLATE), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_56.getLeggings(), KIT_56.getBoots(), KIT_56.getWeapon());
    public static final KitCreator KIT_58 = new KitCreator(KIT_57.getHelmet(), KIT_57.getChestplate(), CustomItemStack.newstack(new ItemStack(Material.DIAMOND_LEGGINGS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_57.getBoots(), KIT_57.getWeapon());
    public static final KitCreator KIT_59 = new KitCreator(KIT_58.getHelmet(), KIT_58.getChestplate(), KIT_58.getLeggings(), CustomItemStack.newstack(new ItemStack(Material.DIAMOND_BOOTS), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_58.getWeapon());
    public static final KitCreator KIT_60 = new KitCreator(CustomItemStack.newstack(new ItemStack(Material.DIAMOND_HELMET), Enchantment.PROTECTION_ENVIRONMENTAL), KIT_59.getChestplate(), KIT_59.getLeggings(), KIT_59.getBoots(), KIT_59.getWeapon());

    public static final List<KitCreator> KITS = List.of(KIT_1, KIT_2, KIT_3, KIT_4, KIT_5, KIT_6, KIT_7, KIT_8, KIT_9, KIT_10, KIT_11, KIT_12, KIT_13, KIT_14, KIT_15, KIT_16, KIT_17, KIT_18, KIT_19, KIT_20, KIT_21, KIT_22, KIT_23, KIT_24, KIT_25, KIT_26, KIT_27, KIT_28, KIT_29, KIT_30, KIT_31, KIT_32, KIT_33, KIT_34, KIT_35, KIT_36, KIT_37, KIT_38, KIT_39, KIT_40, KIT_41, KIT_42, KIT_43, KIT_44, KIT_45, KIT_46, KIT_47, KIT_48, KIT_49, KIT_50, KIT_51, KIT_52, KIT_53, KIT_54, KIT_55, KIT_56, KIT_57, KIT_58, KIT_59, KIT_60);
}
