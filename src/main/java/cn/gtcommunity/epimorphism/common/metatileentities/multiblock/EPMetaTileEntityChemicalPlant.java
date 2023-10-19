package cn.gtcommunity.epimorphism.common.metatileentities.multiblock;

import cn.gtcommunity.epimorphism.api.EPValues;
import cn.gtcommunity.epimorphism.api.block.impl.WrappedIntTired;
import cn.gtcommunity.epimorphism.api.pattern.EPTraceabilityPredicate;
import cn.gtcommunity.epimorphism.api.recipe.EPRecipeMaps;
import cn.gtcommunity.epimorphism.api.recipe.properties.CasingTierProperty;
import cn.gtcommunity.epimorphism.api.utils.EPUniverUtil;
import cn.gtcommunity.epimorphism.client.textures.EPTextures;
import cn.gtcommunity.epimorphism.api.capability.EPCapabilities;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockWireCoil;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nonnull;
import java.util.List;

import static gregtech.api.GTValues.VA;

public class EPMetaTileEntityChemicalPlant extends RecipeMapMultiblockController {
    private int coilLevel;
    private int casingTier;
    private int tubeTier;
    private int voltageTier;

    private int tier;

    public EPMetaTileEntityChemicalPlant(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, EPRecipeMaps.CHEMICAL_PLANT);
        this.recipeMapWorkable = new ChemicalPlantLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new EPMetaTileEntityChemicalPlant(metaTileEntityId);
    }

    @Override
    public void update() {
        super.update();

        if(this.getWorld().isRemote && this.casingTier == 0){
            this.writeCustomData(EPValues.EP_CHANNEL_2, buf -> {});
        }
    }



    @SuppressWarnings("SpellCheckingInspection")
    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("EEEEEEE", "C#####C", "C#####C", "C#####C", "C#####C", "C#####C", "CCCCCCC")
                .aisle("EMMMMME", "#MMMMM#", "#######", "#######", "#######", "#MMMMM#", "CCCCCCC")
                .aisle("EMMMMME", "#MXXXM#", "##TTT##", "##XXX##", "##TTT##", "#MXXXM#", "CCCCCCC")
                .aisle("EMMMMME", "#MXAXM#", "##TAT##", "##XAX##", "##TAT##", "#MXAXM#", "CCCCCCC")
                .aisle("EMMMMME", "#MXXXM#", "##TTT##", "##XXX##", "##TTT##", "#MXXXM#", "CCCCCCC")
                .aisle("EMMMMME", "#MMMMM#", "#######", "#######", "#######", "#MMMMM#", "CCCCCCC")
                .aisle("EEESEEE", "C#####C", "C#####C", "C#####C", "C#####C", "C#####C", "CCCCCCC")
                .where('S', selfPredicate())
                .where('E', EPTraceabilityPredicate.EP_CP_CASING.get()
                        .or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setExactLimit(1))
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS).setMinGlobalLimited(1).setPreviewCount(1))
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(1).setPreviewCount(1))
                        .or(abilities(EPCapabilities.CATALYST_MULTIBLOCK_ABILITY).setMaxGlobalLimited(2).setPreviewCount(1))
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(2).setPreviewCount(1)))
                .where('C',EPTraceabilityPredicate.EP_CP_CASING.get())
                .where('X', heatingCoils())
                .where('M', EPTraceabilityPredicate.EP_MACHINE_CASINGS.get())
                .where('T',EPTraceabilityPredicate.EP_CP_TUBE.get())
                .where('#', any())
                .where('A',air())
                .build();
    }
    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return EPTextures.CHEMICAL_PLANT_OVERLAY;
    }

    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.recipeMapWorkable.isActive(), this.recipeMapWorkable.isWorkingEnabled());
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        switch (this.casingTier){
            case (2):{
                return Textures.SOLID_STEEL_CASING;
            }
            case (3):{
                return Textures.FROST_PROOF_CASING;
            }
            case (4):{
                return Textures.CLEAN_STAINLESS_STEEL_CASING;
            }
            case (5):{
                return Textures.STABLE_TITANIUM_CASING;
            }
            case (6):{
                return Textures.ROBUST_TUNGSTENSTEEL_CASING;
            }
            default:{
                return Textures.BRONZE_PLATED_BRICKS;
            }
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if(dataId == EPValues.EP_CHANNEL_3){
            this.casingTier = buf.readInt();
        }
        if(dataId == EPValues.EP_CHANNEL_2){
            this.writeCustomData(EPValues.EP_CHANNEL_3,buf1 -> buf1.writeInt(this.casingTier));
        }
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentString(String.format("coilTire: %d",coilLevel)));
        textList.add(new TextComponentString(String.format("casingTire: %d",casingTier)));
        textList.add(new TextComponentString(String.format("tubeTire: %d",tubeTier)));
        textList.add(new TextComponentString(String.format("tire: %d",tier)));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object coilType = context.get("CoilType");
        Object casingTier = context.get("ChemicalPlantCasingTiredStats");
        Object tubeTier = context.get("ChemicalPlantTubeTiredStats");
        Object voltageTier = context.get("MachineCasingTypeTiredStats");
        this.coilLevel = EPUniverUtil.getOrDefault(() -> coilType instanceof IHeatingCoilBlockStats,
                () ->  ((IHeatingCoilBlockStats) coilType).getLevel(),
                BlockWireCoil.CoilType.CUPRONICKEL.getLevel());
        this.casingTier = EPUniverUtil.getOrDefault(() -> casingTier instanceof WrappedIntTired,
                () -> ((WrappedIntTired)casingTier).getIntTier(),
                0);
        this.tubeTier = EPUniverUtil.getOrDefault(() -> tubeTier instanceof WrappedIntTired,
                () -> ((WrappedIntTired)tubeTier).getIntTier(),
                0);
        this.voltageTier = EPUniverUtil.getOrDefault(() -> voltageTier instanceof WrappedIntTired,
                () -> ((WrappedIntTired)voltageTier).getIntTier(),
                0);

        this.tier = Math.min(this.casingTier,this.tubeTier);

        this.writeCustomData(EPValues.EP_CHANNEL_3,buf -> buf.writeInt(this.casingTier));
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.casingTier);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.casingTier = buf.readInt();
    }

    protected class ChemicalPlantLogic extends MultiblockRecipeLogic {

        public ChemicalPlantLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        public void setMaxProgress(int maxProgress) {
            this.maxProgressTime = maxProgress / (2 * coilLevel);
            this.metaTileEntity.markDirty();
        }

        protected long getMaxVoltage() {
            return Math.min(super.getMaxVoltage(), VA[voltageTier]);
        }

        @Override
        public boolean checkRecipe(@Nonnull Recipe recipe) {
            return super.checkRecipe(recipe) && (recipe.getProperty(CasingTierProperty.getInstance(), 0) <= tier);
        }

        @Override
        public int getParallelLimit() {
            return 2 * tubeTier;
        }
    }
}
