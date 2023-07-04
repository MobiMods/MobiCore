package com.github.agentallandev.mobimod.mobicore.integration;

import com.github.agentallandev.mobimod.mobicore.MobiCore;
import com.github.agentallandev.mobimod.mobicore.recipe.AlloySmelterRecipe;
import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class AlloySmelterRecipeCategory implements IRecipeCategory<AlloySmelterRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(MobiCore.MODID, "alloy_smelting");
    public final static ResourceLocation TEXTURE = new ResourceLocation(MobiCore.MODID, "textures/gui/alloy_smelter_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public AlloySmelterRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ContentRegistry.ALLOY_SMELTER.get()));
    }

    @Override
    public RecipeType<AlloySmelterRecipe> getRecipeType() {
        return JEIMobiCorePlugin.ALLOY_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Alloy Smelter");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlloySmelterRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 34, 40).addIngredients(Ingredient.of(ItemTags.COALS));
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 18).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 103, 18).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getResultItem(RegistryAccess.EMPTY));
    }
}
