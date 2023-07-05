package com.github.agentallandev.mobimod.mobicore.blocks.entity;


import com.github.agentallandev.mobimod.mobicore.recipe.AlloySmelterRecipe;
import com.github.agentallandev.mobimod.mobicore.screen.AlloySmelterMenu;
import com.github.agentallandev.mobimod.mobicore.util.ContentRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

public class AlloySmelterBlockEntity extends BlockEntity implements MenuProvider {
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 76;

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public AlloySmelterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ContentRegistry.ALLOY_SMELTER_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AlloySmelterBlockEntity.this.progress;
                    case 1 -> AlloySmelterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> AlloySmelterBlockEntity.this.progress = pValue;
                    case 1 -> AlloySmelterBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Alloy Smelter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AlloySmelterMenu(pContainerId, pInventory, this, this.data);
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


    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if (isOutputSlotEmptyOrReceivable() && hasRecipe()) {
            increaseCraftingProcess();
            setChanged(level, pPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craftItem() {
        Optional<AlloySmelterRecipe> recipe = getCurrentRecipe();

        this.itemHandler.extractItem(0, 1, false);
        this.itemHandler.extractItem(1, 1, false);
        this.itemHandler.extractItem(2, 1, false);

        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());
        this.itemHandler.setStackInSlot(3, new ItemStack(resultItem.getItem(), this.itemHandler.getStackInSlot(3).getCount() + resultItem.getCount()));
        resetProgress();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProcess() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<AlloySmelterRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty())
            return false;

        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());
        return canInsertAmountIntoOutputSlot(resultItem.getCount()) && canInsertItemIntoOutputSlot(resultItem.getItem());
    }

    private Optional<AlloySmelterRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < this.itemHandler.getSlots(); i++){
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(AlloySmelterRecipe.Type.INSTANCE, inventory, level);
    }

    private static boolean hasNotReachedStackLimit(AlloySmelterBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(3).getCount() < entity.itemHandler.getStackInSlot(3).getMaxStackSize() || entity.itemHandler.getStackInSlot(3).isEmpty();
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(3).isEmpty() || this.itemHandler.getStackInSlot(3).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(3).getMaxStackSize() >=
                this.itemHandler.getStackInSlot(3).getCount() + count;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(3).isEmpty() ||
                this.itemHandler.getStackInSlot(3).getCount() < this.itemHandler.getStackInSlot(3).getMaxStackSize();
    }
}