package com.github.agentallandev.mobimod.mobicore.integration;

import com.github.agentallandev.mobimod.mobicore.MobiCore;
import com.github.agentallandev.mobimod.mobicore.recipe.AlloySmelterRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIMobiCorePlugin implements IModPlugin {
   public static RecipeType<AlloySmelterRecipe> ALLOY_TYPE = new RecipeType<>(AlloySmelterRecipeCategory.UID, AlloySmelterRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MobiCore.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AlloySmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<AlloySmelterRecipe> recipesAlloys = rm.getAllRecipesFor(AlloySmelterRecipe.Type.INSTANCE);
        registration.addRecipes(ALLOY_TYPE, recipesAlloys);
    }
}
