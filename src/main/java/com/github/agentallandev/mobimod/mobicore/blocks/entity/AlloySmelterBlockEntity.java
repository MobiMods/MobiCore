package com.github.agentallandev.mobimod.mobicore.blocks.entity;


import com.github.agentallandev.mobimod.mobicore.recipe.AlloySmelterRecipe;
import com.github.agentallandev.mobimod.mobicore.screen.AlloySmelterMenu;
import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;

public class AlloySmelterBlockEntity extends BlockEntity implements MenuProvider {
    private static int progress = 0;
    private static int maxProgress = 76;

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public AlloySmelterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ContentRegistry.ALLOY_SMELTER_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Alloy Smelter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AlloySmelterMenu(pContainerId, pInventory, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("alloy_smelter_progress", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("alloy_smelter_progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, AlloySmelterBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)){
            progress++;

            if(progress >= maxProgress){
                craftItem(pBlockEntity);
                setChanged(pLevel, pPos, pState);
            }
        }

        else{
            progress = 0;
        }
    }

    private static void craftItem(AlloySmelterBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        boolean hasCoalInFirstSlot = entity.itemHandler.getStackInSlot(0).is(ItemTags.COALS);
        Optional<AlloySmelterRecipe> recipe = level.getRecipeManager().getRecipeFor(AlloySmelterRecipe.Type.INSTANCE, inventory, level);
        if(hasRecipe(entity)) {
            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.extractItem(2, 1, false);

            entity.itemHandler.setStackInSlot(3, new ItemStack(recipe.get().getResultItem(RegistryAccess.EMPTY).getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + recipe.get().getResultItem(RegistryAccess.EMPTY).getCount()));

            progress = 0;
        }
    }

    private static boolean hasRecipe(AlloySmelterBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());

        boolean hasCoalInFirstSlot = entity.itemHandler.getStackInSlot(0).is(ItemTags.COALS);

        Optional<AlloySmelterRecipe> recipe = level.getRecipeManager().getRecipeFor(AlloySmelterRecipe.Type.INSTANCE, inventory, level);

        return hasCoalInFirstSlot && recipe.isPresent();
    }

    private static boolean hasNotReachedStackLimit(AlloySmelterBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(3).getCount() < entity.itemHandler.getStackInSlot(3).getMaxStackSize() || entity.itemHandler.getStackInSlot(3).isEmpty();
    }

    public int getProgress(){
        return progress;
    }

    public int getMaxProgress(){
        return maxProgress;
    }
}