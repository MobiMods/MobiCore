package com.github.agentallandev.mobimod.mobicore;

import com.github.agentallandev.mobimod.mobicore.screen.AlloySmelterScreen;
import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MobiCore.MODID)
public class MobiCore
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "mobicore";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MobiCore()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);
        ContentRegistry.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event){}

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == CreativeModeTabs.COMBAT){
            event.accept(ContentRegistry.STEEL_HELMET);
            event.accept(ContentRegistry.STEEL_CHESTPLATE);
            event.accept(ContentRegistry.STEEL_LEGGINGS);
            event.accept(ContentRegistry.STEEL_BOOTS);
            event.accept(ContentRegistry.STEEL_SWORD);
            event.accept(ContentRegistry.COPPER_HELMET);
            event.accept(ContentRegistry.COPPER_CHESTPLATE);
            event.accept(ContentRegistry.COPPER_LEGGINGS);
            event.accept(ContentRegistry.COPPER_BOOTS);
            event.accept(ContentRegistry.COPPER_SWORD);
            event.accept(ContentRegistry.EMERALD_HELMET);
            event.accept(ContentRegistry.EMERALD_CHESTPLATE);
            event.accept(ContentRegistry.EMERALD_LEGGINGS);
            event.accept(ContentRegistry.EMERALD_BOOTS);
            event.accept(ContentRegistry.EMERALD_SWORD);
            event.accept(ContentRegistry.AMETHYST_HELMET);
            event.accept(ContentRegistry.AMETHYST_CHESTPLATE);
            event.accept(ContentRegistry.AMETHYST_LEGGINGS);
            event.accept(ContentRegistry.AMETHYST_BOOTS);
            event.accept(ContentRegistry.AMETHYST_SWORD);
            event.accept(ContentRegistry.HARDENED_REDSTONE_HELMET);
            event.accept(ContentRegistry.HARDENED_REDSTONE_CHESTPLATE);
            event.accept(ContentRegistry.HARDENED_REDSTONE_LEGGINGS);
            event.accept(ContentRegistry.HARDENED_REDSTONE_BOOTS);
            event.accept(ContentRegistry.HARDENED_REDSTONE_SWORD);
        }
        if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ContentRegistry.STEEL_SHOVEL);
            event.accept(ContentRegistry.STEEL_AXE);
            event.accept(ContentRegistry.STEEL_PICKAXE);
            event.accept(ContentRegistry.STEEL_HOE);
            event.accept(ContentRegistry.COPPER_SHOVEL);
            event.accept(ContentRegistry.COPPER_AXE);
            event.accept(ContentRegistry.COPPER_PICKAXE);
            event.accept(ContentRegistry.COPPER_HOE);
            event.accept(ContentRegistry.EMERALD_SHOVEL);
            event.accept(ContentRegistry.EMERALD_AXE);
            event.accept(ContentRegistry.EMERALD_PICKAXE);
            event.accept(ContentRegistry.EMERALD_HOE);
            event.accept(ContentRegistry.AMETHYST_SHOVEL);
            event.accept(ContentRegistry.AMETHYST_AXE);
            event.accept(ContentRegistry.AMETHYST_PICKAXE);
            event.accept(ContentRegistry.AMETHYST_HOE);
            event.accept(ContentRegistry.HARDENED_REDSTONE_SHOVEL);
            event.accept(ContentRegistry.HARDENED_REDSTONE_AXE);
            event.accept(ContentRegistry.HARDENED_REDSTONE_PICKAXE);
            event.accept(ContentRegistry.HARDENED_REDSTONE_HOE);
        }
        if(event.getTab() == CreativeModeTabs.INGREDIENTS){
            event.accept(ContentRegistry.HARDENED_REDSTONE);
            event.accept(ContentRegistry.STEEL_INGOT);
        }
        if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ContentRegistry.STEEL_BLOCK);
        }
        if(event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ContentRegistry.ALLOY_SMELTER);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event){}

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            MenuScreens.register(ContentRegistry.ALLOY_SMELTER_MENU.get(), AlloySmelterScreen::new);
        }}
}
