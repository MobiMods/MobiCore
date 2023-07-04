package com.github.agentallandev.mobimod.mobicore.items;

import java.util.function.Supplier;

import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum ContentTiers implements Tier {
    EMERALD(3, 1561, 8.0F, 3.0F, 10, () -> {
        return Ingredient.of(Items.EMERALD);
    }),
    AMETHYST(3, 1561, 8.0F, 3.0F, 10, () -> {
        return Ingredient.of(Items.AMETHYST_SHARD);
    }),
    HARDENED_REDSTONE(0, 32, 12.0F, 0.0F, 22, () -> {
        return Ingredient.of(ContentRegistry.HARDENED_REDSTONE.get());
    }),
    COPPER(2, 500, 7.0F, 2.5F, 14, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),
    STEEL(2, 500, 7.0F, 2.5F, 14, () -> {
        return Ingredient.of(ContentRegistry.STEEL_INGOT.get());
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ContentTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
