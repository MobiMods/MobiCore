package com.github.agentallandev.mobimod.common.datagen;

import com.github.agentallandev.mobimod.mobicore.MobiCore;
import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MobiCore.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ContentRegistry.HARDENED_REDSTONE);
        simpleItem(ContentRegistry.STEEL_INGOT);
        simpleItem(ContentRegistry.STEEL_BOOTS);
        simpleItem(ContentRegistry.STEEL_CHESTPLATE);
        simpleItem(ContentRegistry.STEEL_HELMET);
        simpleItem(ContentRegistry.STEEL_LEGGINGS);
        handheldItem(ContentRegistry.STEEL_AXE);
        handheldItem(ContentRegistry.STEEL_HOE);
        handheldItem(ContentRegistry.STEEL_PICKAXE);
        handheldItem(ContentRegistry.STEEL_SHOVEL);
        handheldItem(ContentRegistry.STEEL_SWORD);
        simpleItem(ContentRegistry.EMERALD_BOOTS);
        simpleItem(ContentRegistry.EMERALD_CHESTPLATE);
        simpleItem(ContentRegistry.EMERALD_HELMET);
        simpleItem(ContentRegistry.EMERALD_LEGGINGS);
        handheldItem(ContentRegistry.EMERALD_AXE);
        handheldItem(ContentRegistry.EMERALD_HOE);
        handheldItem(ContentRegistry.EMERALD_PICKAXE);
        handheldItem(ContentRegistry.EMERALD_SHOVEL);
        handheldItem(ContentRegistry.EMERALD_SWORD);
        simpleItem(ContentRegistry.AMETHYST_BOOTS);
        simpleItem(ContentRegistry.AMETHYST_CHESTPLATE);
        simpleItem(ContentRegistry.AMETHYST_HELMET);
        simpleItem(ContentRegistry.AMETHYST_LEGGINGS);
        handheldItem(ContentRegistry.AMETHYST_AXE);
        handheldItem(ContentRegistry.AMETHYST_HOE);
        handheldItem(ContentRegistry.AMETHYST_PICKAXE);
        handheldItem(ContentRegistry.AMETHYST_SHOVEL);
        handheldItem(ContentRegistry.AMETHYST_SWORD);
        simpleItem(ContentRegistry.HARDENED_REDSTONE_BOOTS);
        simpleItem(ContentRegistry.HARDENED_REDSTONE_CHESTPLATE);
        simpleItem(ContentRegistry.HARDENED_REDSTONE_HELMET);
        simpleItem(ContentRegistry.HARDENED_REDSTONE_LEGGINGS);
        handheldItem(ContentRegistry.HARDENED_REDSTONE_AXE);
        handheldItem(ContentRegistry.HARDENED_REDSTONE_HOE);
        handheldItem(ContentRegistry.HARDENED_REDSTONE_PICKAXE);
        handheldItem(ContentRegistry.HARDENED_REDSTONE_SHOVEL);
        handheldItem(ContentRegistry.HARDENED_REDSTONE_SWORD);
        simpleItem(ContentRegistry.COPPER_BOOTS);
        simpleItem(ContentRegistry.COPPER_CHESTPLATE);
        simpleItem(ContentRegistry.COPPER_HELMET);
        simpleItem(ContentRegistry.COPPER_LEGGINGS);
        handheldItem(ContentRegistry.COPPER_AXE);
        handheldItem(ContentRegistry.COPPER_HOE);
        handheldItem(ContentRegistry.COPPER_PICKAXE);
        handheldItem(ContentRegistry.COPPER_SHOVEL);
        handheldItem(ContentRegistry.COPPER_SWORD);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(MobiCore.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/handheld")).texture("layer0", new ResourceLocation(MobiCore.MODID, "item/" + item.getId().getPath()));
    }
}
