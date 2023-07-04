package com.github.agentallandev.mobimod.common.datagen;

import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        axeRecipe(consumer, ContentRegistry.STEEL_AXE.get(), ContentRegistry.STEEL_INGOT.get());
        bootsRecipe(consumer, ContentRegistry.STEEL_BOOTS.get(), ContentRegistry.STEEL_INGOT.get());
        chestplateRecipe(consumer, ContentRegistry.STEEL_CHESTPLATE.get(), ContentRegistry.STEEL_INGOT.get());
        helmetRecipe(consumer, ContentRegistry.STEEL_HELMET.get(), ContentRegistry.STEEL_INGOT.get());
        hoeRecipe(consumer, ContentRegistry.STEEL_HOE.get(), ContentRegistry.STEEL_INGOT.get());
        leggingsRecipe(consumer, ContentRegistry.STEEL_LEGGINGS.get(), ContentRegistry.STEEL_INGOT.get());
        pickaxeRecipe(consumer, ContentRegistry.STEEL_PICKAXE.get(), ContentRegistry.STEEL_INGOT.get());
        shovelRecipe(consumer, ContentRegistry.STEEL_SHOVEL.get(), ContentRegistry.STEEL_INGOT.get());
        swordRecipe(consumer, ContentRegistry.STEEL_SWORD.get(), ContentRegistry.STEEL_INGOT.get());
        axeRecipe(consumer, ContentRegistry.EMERALD_AXE.get(), Items.EMERALD);
        bootsRecipe(consumer, ContentRegistry.EMERALD_BOOTS.get(), Items.EMERALD);
        chestplateRecipe(consumer, ContentRegistry.EMERALD_CHESTPLATE.get(), Items.EMERALD);
        helmetRecipe(consumer, ContentRegistry.EMERALD_HELMET.get(), Items.EMERALD);
        hoeRecipe(consumer, ContentRegistry.EMERALD_HOE.get(), Items.EMERALD);
        leggingsRecipe(consumer, ContentRegistry.EMERALD_LEGGINGS.get(), Items.EMERALD);
        pickaxeRecipe(consumer, ContentRegistry.EMERALD_PICKAXE.get(), Items.EMERALD);
        shovelRecipe(consumer, ContentRegistry.EMERALD_SHOVEL.get(), Items.EMERALD);
        swordRecipe(consumer, ContentRegistry.EMERALD_SWORD.get(), Items.EMERALD);
        axeRecipe(consumer, ContentRegistry.COPPER_AXE.get(), Items.COPPER_INGOT);
        bootsRecipe(consumer, ContentRegistry.COPPER_BOOTS.get(), Items.COPPER_INGOT);
        chestplateRecipe(consumer, ContentRegistry.COPPER_CHESTPLATE.get(), Items.COPPER_INGOT);
        helmetRecipe(consumer, ContentRegistry.COPPER_HELMET.get(), Items.COPPER_INGOT);
        hoeRecipe(consumer, ContentRegistry.COPPER_HOE.get(), Items.COPPER_INGOT);
        leggingsRecipe(consumer, ContentRegistry.COPPER_LEGGINGS.get(), Items.COPPER_INGOT);
        pickaxeRecipe(consumer, ContentRegistry.COPPER_PICKAXE.get(), Items.COPPER_INGOT);
        shovelRecipe(consumer, ContentRegistry.COPPER_SHOVEL.get(), Items.COPPER_INGOT);
        swordRecipe(consumer, ContentRegistry.COPPER_SWORD.get(), Items.COPPER_INGOT);
        axeRecipe(consumer, ContentRegistry.AMETHYST_AXE.get(), Items.AMETHYST_SHARD);
        bootsRecipe(consumer, ContentRegistry.AMETHYST_BOOTS.get(), Items.AMETHYST_SHARD);
        chestplateRecipe(consumer, ContentRegistry.AMETHYST_CHESTPLATE.get(), Items.AMETHYST_SHARD);
        helmetRecipe(consumer, ContentRegistry.AMETHYST_HELMET.get(), Items.AMETHYST_SHARD);
        hoeRecipe(consumer, ContentRegistry.AMETHYST_HOE.get(), Items.AMETHYST_SHARD);
        leggingsRecipe(consumer, ContentRegistry.AMETHYST_LEGGINGS.get(), Items.AMETHYST_SHARD);
        pickaxeRecipe(consumer, ContentRegistry.AMETHYST_PICKAXE.get(), Items.AMETHYST_SHARD);
        shovelRecipe(consumer, ContentRegistry.AMETHYST_SHOVEL.get(), Items.AMETHYST_SHARD);
        swordRecipe(consumer, ContentRegistry.AMETHYST_SWORD.get(), Items.AMETHYST_SHARD);
        axeRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_AXE.get(), ContentRegistry.HARDENED_REDSTONE.get());
        bootsRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_BOOTS.get(), ContentRegistry.HARDENED_REDSTONE.get());
        chestplateRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_CHESTPLATE.get(), ContentRegistry.HARDENED_REDSTONE.get());
        helmetRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_HELMET.get(), ContentRegistry.HARDENED_REDSTONE.get());
        hoeRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_HOE.get(), ContentRegistry.HARDENED_REDSTONE.get());
        leggingsRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_LEGGINGS.get(), ContentRegistry.HARDENED_REDSTONE.get());
        pickaxeRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_PICKAXE.get(), ContentRegistry.HARDENED_REDSTONE.get());
        shovelRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_SHOVEL.get(), ContentRegistry.HARDENED_REDSTONE.get());
        swordRecipe(consumer, ContentRegistry.HARDENED_REDSTONE_SWORD.get(), ContentRegistry.HARDENED_REDSTONE.get());



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ContentRegistry.ALLOY_SMELTER.get()).define('C', Items.COPPER_INGOT).define('S', Blocks.SMOOTH_STONE).define('F', Blocks.FURNACE)
                .pattern("CCC")
                .pattern("CFC")
                .pattern("SSS")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.FURNACE).build()))
                .save(consumer);

    }

    protected void axeRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('S', Items.STICK).define('M', material)
                .pattern("MM")
                .pattern("MS")
                .pattern(" S")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }

    protected void bootsRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('M', material)
                .pattern("M M")
                .pattern("M M")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }

    protected void chestplateRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('M', material)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }

    protected void helmetRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('M', material)
                .pattern("MMM")
                .pattern("M M")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }

    protected void hoeRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('S', Items.STICK).define('M', material)
                .pattern("MM")
                .pattern(" S")
                .pattern(" S")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }

    protected void leggingsRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('M', material)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }

    protected void pickaxeRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('S', Items.STICK).define('M', material)
                .pattern("MMM")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }

    protected void shovelRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('S', Items.STICK).define('M', material)
                .pattern("M")
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }

    protected void swordRecipe(Consumer<FinishedRecipe> consumer, Item output, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output).define('S', Items.STICK).define('M', material)
                .pattern("M")
                .pattern("M")
                .pattern("S")
                .unlockedBy("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(material).build()))
                .save(consumer);
    }
}
