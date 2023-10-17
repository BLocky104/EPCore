package cn.gtcommunity.epimorphism.common.metatileentities.multiblock;

import cn.gtcommunity.epimorphism.api.capability.EPCapabilities;
import cn.gtcommunity.epimorphism.api.capability.IPHValue;
import cn.gtcommunity.epimorphism.api.recipe.EPRecipeMaps;
import cn.gtcommunity.epimorphism.api.recipe.properties.PHChangeProperty;
import cn.gtcommunity.epimorphism.api.recipe.properties.PHErrorRangeProperty;
import cn.gtcommunity.epimorphism.api.recipe.properties.PHProperty;
import cn.gtcommunity.epimorphism.client.textures.EPTextures;
import cn.gtcommunity.epimorphism.common.blocks.EPBlockGlassCasing;
import cn.gtcommunity.epimorphism.common.blocks.EPMetablocks;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.util.LocalizationUtils;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static gregicality.multiblocks.api.unification.GCYMMaterials.WatertightSteel;
import static gregtech.api.unification.material.Materials.Steel;

public class EPMetaTileEntityFermentationTank extends RecipeMapMultiblockController implements IPHValue {
    private double pH = 7;

    public EPMetaTileEntityFermentationTank(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, EPRecipeMaps.FERMENTATION_TANK_RECIPES);
        this.recipeMapWorkable = new PHRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new EPMetaTileEntityFermentationTank(metaTileEntityId);
    }

    @Override
    public boolean checkRecipe(@Nonnull Recipe recipe, boolean consumeIfSuccess) {
        double min = recipe.getProperty(PHProperty.getInstance(), 7D) - recipe.getProperty(PHErrorRangeProperty.getInstance(), 0D);
        double max = recipe.getProperty(PHProperty.getInstance(), 7D) + recipe.getProperty(PHErrorRangeProperty.getInstance(), 0D);
        if (min < 0) {min = 0D;}
        if (max > 14) {max = 14D;}
        return (this.pH >= min && this.pH <= max) && super.checkRecipe(recipe, consumeIfSuccess);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.pH = 7D;
        this.markDirty();
    }

    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("FAAAF", "FXXXF", "FXGXF", "FXGXF", "FXGXF", "FXXXF", "AAAAA")
                .aisle("AXXXA", "XEEEX", "XEEEX", "XEEEX", "XEEEX", "XEEEX", "AXXXA")
                .aisle("AXXXA", "XEPEX", "GEPEG", "GEPEG", "GEPEG", "XEPEX", "AXXXA")
                .aisle("AXXXA", "XEEEX", "XEEEX", "XEEEX", "XEEEX", "XEEEX", "AXXXA")
                .aisle("FAAAF", "FXSXF", "FXGXF", "FXGXF", "FXGXF", "FXXXF", "AAAAA")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(42).or(autoAbilities()).or(abilities(EPCapabilities.BUFFER_MULTIBLOCK_ABILITY).setExactLimit(1)))
                .where('G', states(getGlassState()))
                .where('F', states(getFrameState()))
                .where('P', states(getBoilerState()))
                .where('A', any())
                .where('E', air())
                .build();
    }

    private static IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    private static IBlockState getBoilerState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE);
    }

    @Nonnull
    private static IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(WatertightSteel).getBlock(WatertightSteel);
    }

    private static IBlockState getGlassState() {
        return EPMetablocks.EP_GLASS_CASING.getState(EPBlockGlassCasing.CasingType.OSMIR_BORON_SILICATE_GLASS);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("epimorphism.machine.fermentation_tank.ph", String.format("%, .2f", this.pH)));
    }

    @Override
    public String[] getDescription() {
        List<String> list = new ArrayList<>();
        list.add(I18n.format("epimorphism.multiblock.fermentation_tank.description"));
        Collections.addAll(list, LocalizationUtils.formatLines("epimorphism.multiblock.fermentation_tank.extra1"));
        Collections.addAll(list, LocalizationUtils.formatLines("epimorphism.multiblock.fermentation_tank.extra2"));
        return list.toArray(new String[0]);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return EPTextures.CVD_UNIT_OVERLAY;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    @Override
    public double getCurrentPHValue() {
        return this.pH;
    }

    @Override
    public void changeCurrentPHValue(double ph_change) {
        double ph = this.pH + ph_change;
        if (ph < 0){
            this.pH = 0D;
        } else if (ph > 14){
            this.pH = 14D;
        } else {this.pH = ph;}
        this.markDirty();
    }

    @Override
    public void changeCurrentPHValue(double ph_change, double ph_change_limit) {
        if (ph_change > 0) {
            double ph = this.pH + ph_change;
            this.pH = Math.min(ph, ph_change_limit);
        } else {
            double ph = this.pH + ph_change;
            this.pH = Math.max(ph, ph_change_limit);
        }
        this.markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setDouble("ph", this.pH);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.pH = data.getDouble("ph");
    }

    protected class PHRecipeLogic extends MultiblockRecipeLogic {
        private double current_ph_change;
        public PHRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
            if (!(metaTileEntity instanceof IPHValue)) {
                throw new IllegalArgumentException("MetaTileEntity must be instanceof IPHValue");
            }
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            super.setupRecipe(recipe);
            this.current_ph_change = recipe.getProperty(PHChangeProperty.getInstance(), 0D);
        }

        @Nonnull
        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound tag = super.serializeNBT();
            tag.setDouble("current_ph_change", this.current_ph_change);
            return tag;
        }

        @Override
        public void deserializeNBT(@Nonnull NBTTagCompound compound) {
            super.deserializeNBT(compound);
            this.current_ph_change = compound.getDouble("current_ph_change");
        }

        @Override
        protected void completeRecipe() {
            super.completeRecipe();
            ((IPHValue)this.metaTileEntity).changeCurrentPHValue(this.current_ph_change);
        }
    }
}
