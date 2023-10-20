package cn.gtcommunity.epimorphism.common.metatileentities.multiblock.part;

import cn.gtcommunity.epimorphism.api.capability.EPCapabilities;
import cn.gtcommunity.epimorphism.api.capability.ICatalyst;
import cn.gtcommunity.epimorphism.api.capability.impl.WrappedCatalyst;
import cn.gtcommunity.epimorphism.client.textures.EPTextures;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class EPMetaTileEntityCatalystHatch extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<ICatalyst> {
    private final ItemStackHandler itemStack = new ItemStackHandler(1){
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return stack.getItem() instanceof ICatalyst;
        }

        @Override
        protected void onLoad() {
            onContentsChanged(0);
        }

        @Override
        protected void onContentsChanged(int slot) {
            needUpdate = true;
            ItemStack item = this.getStackInSlot(0);
            catalyst.update(item.isEmpty() ? () -> Optional.of("") : (ICatalyst)item.getItem() );
        }
    };
    private final WrappedCatalyst catalyst = new WrappedCatalyst(Optional::empty){
        @Override
        public void consumeCatalyst(int amount) {
            ItemStack item = itemStack.getStackInSlot(0);
            if(!item.isEmpty() && item.isItemStackDamageable()){
                int left = item.getMaxDamage() - item.getItemDamage();
                if(left>amount){
                    item.setItemDamage(item.getItemDamage()+amount);
                }
                else {
                    item.shrink(1);
                }
            }

        }
    };

    private boolean needUpdate = false;


    public EPMetaTileEntityCatalystHatch(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 4);
    }


    @Override
    public void update() {
        super.update();
        if(needUpdate){
            needUpdate = false;
            this.markDirty();
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new EPMetaTileEntityCatalystHatch(this.metaTileEntityId);
    }

//    @Override
//    public void addToMultiBlock(MultiblockControllerBase controllerBase) {
//        super.addToMultiBlock(controllerBase);
//    }
//
//    @Override
//    public void removeFromMultiBlock(MultiblockControllerBase controllerBase) {
//        super.removeFromMultiBlock(controllerBase);
//
//    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (this.shouldRenderOverlay()){
            EPTextures.MULTIPART_CATALYST_HATCH.renderSided(getFrontFacing(), renderState, translation, pipeline);
        }

    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 209)
                .bindPlayerInventory(entityPlayer.inventory, 126)
                .widget(new SlotWidget(this.itemStack,0, 88-9,50,true,true,true)
                        .setBackgroundTexture(GuiTextures.SLOT)
                        .setChangeListener(this::markDirty))
                .widget(new LabelWidget(88,20,"epimorphism.multipart.catalyst.only")
                        .setXCentered(true));

        return builder.build(this.getHolder(),entityPlayer);
    }

    @Override
    public MultiblockAbility<ICatalyst> getAbility() {
        return EPCapabilities.CATALYST_MULTIBLOCK_ABILITY;
    }

    @Override
    public void registerAbilities(List<ICatalyst> list) {
        list.add(catalyst);
    }

    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(this.needUpdate);
        buf.writeCompoundTag(itemStack.serializeNBT());
    }

    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.needUpdate = buf.readBoolean();
        try {
            itemStack.deserializeNBT(buf.readCompoundTag());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("needUpdate", this.needUpdate);
        data.setTag("item", this.itemStack.serializeNBT());
        return data;
    }

    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (data.hasKey("needUpdate")) {
            this.needUpdate = data.getBoolean("needUpdate");
        }

        if (data.hasKey("item")) {
            itemStack.deserializeNBT(data.getCompoundTag("item"));
        }

    }

    @Override
    public void clearMachineInventory(NonNullList<ItemStack> itemBuffer) {
        clearInventory(itemBuffer, this.itemStack);
    }

    @Override
    protected boolean shouldSerializeInventories() {
        return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        return capability== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ?
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStack) : super.getCapability(capability, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("epimorphism.multipart.catalyst.only"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addToolUsages(ItemStack stack, @javax.annotation.Nullable World world, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"));
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"));
        super.addToolUsages(stack, world, tooltip, advanced);
    }
}
