package com.github.agentallandev.mobimod.mobicore.util;

import com.github.agentallandev.mobimod.mobicore.MobiCore;
import com.github.agentallandev.mobimod.mobicore.blocks.AlloySmelterBlock;
import com.github.agentallandev.mobimod.mobicore.blocks.entity.AlloySmelterBlockEntity;
import com.github.agentallandev.mobimod.mobicore.items.ContentMaterials;
import com.github.agentallandev.mobimod.mobicore.items.ContentTiers;
import com.github.agentallandev.mobimod.mobicore.recipe.AlloySmelterRecipe;
import com.github.agentallandev.mobimod.mobicore.screen.AlloySmelterMenu;
import com.google.common.eventbus.EventBus;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContentRegistry {

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MobiCore.MODID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobiCore.MODID);
    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MobiCore.MODID);
    public static DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MobiCore.MODID);
    public static DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MobiCore.MODID);

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", ()-> new Block(BlockBehaviour.Properties.of(Material.METAL)));
    public static final RegistryObject<Item> STEEL_BLOCK_ITEM = ITEMS.register("steel_block", ()-> new BlockItem(STEEL_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Block> ALLOY_SMELTER = BLOCKS.register("alloy_smelter", ()-> new AlloySmelterBlock(BlockBehaviour.Properties.of(Material.METAL)));
    public static final RegistryObject<Item> ALLOY_SMELTER_ITEM = ITEMS.register("alloy_smelter", ()-> new BlockItem(ALLOY_SMELTER.get(), new Item.Properties()));


    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", ()-> new SwordItem(ContentTiers.STEEL, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", ()-> new ShovelItem(ContentTiers.STEEL, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", ()-> new PickaxeItem(ContentTiers.STEEL, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", ()-> new AxeItem(ContentTiers.STEEL, 6.0F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", ()-> new HoeItem(ContentTiers.STEEL, -2, -1.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet", ()-> new ArmorItem(ContentMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate", ()-> new ArmorItem(ContentMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings", ()-> new ArmorItem(ContentMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots", ()-> new ArmorItem(ContentMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet", ()-> new ArmorItem(ContentMaterials.EMERALD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate", ()-> new ArmorItem(ContentMaterials.EMERALD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings", ()-> new ArmorItem(ContentMaterials.EMERALD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots", ()-> new ArmorItem(ContentMaterials.EMERALD, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_SWORD = ITEMS.register("emerald_sword", ()-> new SwordItem(ContentTiers.EMERALD, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel", ()-> new ShovelItem(ContentTiers.EMERALD, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe", ()-> new PickaxeItem(ContentTiers.EMERALD, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_AXE = ITEMS.register("emerald_axe", ()-> new AxeItem(ContentTiers.EMERALD, 5.0F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_HOE = ITEMS.register("emerald_hoe", ()-> new HoeItem(ContentTiers.EMERALD, -3, 0.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet", ()-> new ArmorItem(ContentMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", ()-> new ArmorItem(ContentMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", ()-> new ArmorItem(ContentMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots", ()-> new ArmorItem(ContentMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", ()-> new SwordItem(ContentTiers.COPPER, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", ()-> new ShovelItem(ContentTiers.COPPER, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", ()-> new PickaxeItem(ContentTiers.COPPER, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", ()-> new AxeItem(ContentTiers.COPPER, 5.0F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", ()-> new HoeItem(ContentTiers.COPPER, -3, 0.0F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet", ()-> new ArmorItem(ContentMaterials.AMETHYST, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate", ()-> new ArmorItem(ContentMaterials.AMETHYST, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings", ()-> new ArmorItem(ContentMaterials.AMETHYST, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots", ()-> new ArmorItem(ContentMaterials.AMETHYST, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", ()-> new SwordItem(ContentTiers.AMETHYST, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", ()-> new ShovelItem(ContentTiers.AMETHYST, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", ()-> new PickaxeItem(ContentTiers.AMETHYST, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", ()-> new AxeItem(ContentTiers.AMETHYST, 5.0F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", ()-> new HoeItem(ContentTiers.AMETHYST, -3, 0.0F, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_HELMET = ITEMS.register("hardened_redstone_helmet", ()-> new ArmorItem(ContentMaterials.HARDENED_REDSTONE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_CHESTPLATE = ITEMS.register("hardened_redstone_chestplate", ()-> new ArmorItem(ContentMaterials.HARDENED_REDSTONE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_LEGGINGS = ITEMS.register("hardened_redstone_leggings", ()-> new ArmorItem(ContentMaterials.HARDENED_REDSTONE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_BOOTS = ITEMS.register("hardened_redstone_boots", ()-> new ArmorItem(ContentMaterials.HARDENED_REDSTONE, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_SWORD = ITEMS.register("hardened_redstone_sword", ()-> new SwordItem(ContentTiers.HARDENED_REDSTONE, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_SHOVEL = ITEMS.register("hardened_redstone_shovel", ()-> new ShovelItem(ContentTiers.HARDENED_REDSTONE, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_PICKAXE = ITEMS.register("hardened_redstone_pickaxe", ()-> new PickaxeItem(ContentTiers.HARDENED_REDSTONE, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_AXE = ITEMS.register("hardened_redstone_axe", ()-> new AxeItem(ContentTiers.HARDENED_REDSTONE, 5.0F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE_HOE = ITEMS.register("hardened_redstone_hoe", ()-> new HoeItem(ContentTiers.HARDENED_REDSTONE, -3, 0.0F, new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_REDSTONE = ITEMS.register("hardened_redstone", ()-> new Item(new Item.Properties()));

    public static final RegistryObject<BlockEntityType<AlloySmelterBlockEntity>> ALLOY_SMELTER_ENTITY = BLOCK_ENTITIES.register("alloy_smelter", ()-> BlockEntityType.Builder.of(AlloySmelterBlockEntity::new, ALLOY_SMELTER.get()).build(null));
    public static final RegistryObject<MenuType<AlloySmelterMenu>> ALLOY_SMELTER_MENU = registerMenuType(AlloySmelterMenu::new, "alloy_smelter_menu");
    public static final RegistryObject<RecipeSerializer<AlloySmelterRecipe>> ALLOY_SMELTER_SERIALIZER = SERIALIZERS.register("alloy_smelter", ()-> AlloySmelterRecipe.Serializer.INSTANCE);


    public static void register(IEventBus event){
        BLOCKS.register(event);
        ITEMS.register(event);
        BLOCK_ENTITIES.register(event);
        SERIALIZERS.register(event);
        MENUS.register(event);
    }

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}
