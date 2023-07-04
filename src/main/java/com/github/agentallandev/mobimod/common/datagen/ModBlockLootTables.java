package com.github.agentallandev.mobimod.common.datagen;

import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ContentRegistry.STEEL_BLOCK.get());
        dropSelf(ContentRegistry.ALLOY_SMELTER.get());
        //add(Blocks.DIAMOND_ORE, (block) -> createOreDrop(Blocks.DIAMOND_ORE, Items.DIAMOND));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ContentRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
