package com.github.agentallandev.mobimod.mobicore.items;

import java.util.EnumMap;
import java.util.function.Supplier;

import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ContentMaterials implements StringRepresentable, ArmorMaterial {
    EMERALD("emerald", 33, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266649_) -> {
        p_266649_.put(ArmorItem.Type.BOOTS, 3);
        p_266649_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266649_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266649_.put(ArmorItem.Type.HELMET, 3);
    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
        return Ingredient.of(Items.EMERALD);
    }),
    HARDENED_REDSTONE("hardened_redstone", 7, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266650_) -> {
        p_266650_.put(ArmorItem.Type.BOOTS, 1);
        p_266650_.put(ArmorItem.Type.LEGGINGS, 3);
        p_266650_.put(ArmorItem.Type.CHESTPLATE, 5);
        p_266650_.put(ArmorItem.Type.HELMET, 2);
    }), 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.of(ContentRegistry.HARDENED_REDSTONE.get());
    }),
    COPPER("copper", 7, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266650_) -> {
        p_266650_.put(ArmorItem.Type.BOOTS, 2);
        p_266650_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266650_.put(ArmorItem.Type.CHESTPLATE, 7);
        p_266650_.put(ArmorItem.Type.HELMET, 2);
    }), 25, SoundEvents.ARMOR_EQUIP_IRON, 0.5F, 0.0F, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),
    AMETHYST("amethyst", 33, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266649_) -> {
        p_266649_.put(ArmorItem.Type.BOOTS, 3);
        p_266649_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266649_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266649_.put(ArmorItem.Type.HELMET, 3);
    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
        return Ingredient.of(Items.AMETHYST_SHARD);
    }),
    STEEL("steel", 25, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266654_) -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 7);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
        return Ingredient.of(ContentRegistry.STEEL_INGOT.get());
    });

    public static final StringRepresentable.EnumCodec<net.minecraft.world.item.ArmorMaterials> CODEC = StringRepresentable.fromEnum(net.minecraft.world.item.ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ContentMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionFunctionForType, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = protectionFunctionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionFunctionForType.get(type);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String getSerializedName() {
        return this.name;
    }
}