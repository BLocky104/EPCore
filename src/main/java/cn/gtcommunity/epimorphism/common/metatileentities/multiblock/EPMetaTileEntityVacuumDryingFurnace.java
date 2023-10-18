package cn.gtcommunity.epimorphism.common.metatileentities.multiblock;

import cn.gtcommunity.epimorphism.api.recipe.EPRecipeMaps;
import cn.gtcommunity.epimorphism.client.textures.EPTextures;
import cn.gtcommunity.epimorphism.common.blocks.EPBlockMultiblockCasing;
import cn.gtcommunity.epimorphism.common.blocks.EPMetablocks;
import cn.gtcommunity.epimorphism.common.metatileentities.EPMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.impl.HeatingCoilRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EPMetaTileEntityVacuumDryingFurnace extends MultiMapMultiblockController implements IHeatingCoil {

    private int temperature;

    public EPMetaTileEntityVacuumDryingFurnace(ResourceLocation metaTileEntityId) {
            super(metaTileEntityId, new RecipeMap[]{
                EPRecipeMaps.DRYER_RECIPES,
                EPRecipeMaps.VACUUM_DRYING_FURNACE_RECIPES
        });
        this.recipeMapWorkable = new HeatingCoilRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity holder) {
        return new EPMetaTileEntityVacuumDryingFurnace(metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature",
                    TextFormatting.RED + GTUtility.formatNumbers(temperature) + "K"));
        }
        super.addDisplayText(textList);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("CoilType");
        if (type instanceof BlockWireCoil.CoilType)
            this.temperature = ((BlockWireCoil.CoilType) type).getCoilTemperature();
        else
            this.temperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();

        this.temperature += 100 * Math.max(0, GTUtility.getTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.temperature = 0;
    }

    @Override
    public boolean checkRecipe(@Nonnull Recipe recipe, boolean consumeIfSuccess) {
        return this.temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0);
    }

    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "CCC", "XXX")
                .aisle("XXX", "C#C", "C#C", "XMX")
                .aisle("XSX", "CCC", "CCC", "XXX")
                .where('S', this.selfPredicate())
                .where('X', states(new IBlockState[]{this.getCasingState()})
                        .setMinGlobalLimited(9)
                        .or(this.autoAbilities(true, true, true, true, true, true, false)))
                .where('M', abilities(new MultiblockAbility[]{MultiblockAbility.MUFFLER_HATCH}))
                .where('C', heatingCoils())
                .where('#', air())
                .build();
    }

    @Nonnull
    private static IBlockState getCasingState() {
        return EPMetablocks.EP_MULTIBLOCK_CASING.getState(EPBlockMultiblockCasing.CasingType.VACUUM_CASING);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return EPTextures.VACUUM_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return EPTextures.VACUUM_DRYING_FURNACE_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public int getCurrentTemperature() {
        return this.temperature;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = null;
        if (Blocks.AIR != null) {
            builder = MultiblockShapeInfo.builder()
                    .aisle("EEM", "CCC", "CCC", "XXX")
                    .aisle("FXD", "C#C", "C#C", "XHX")
                    .aisle("ISO", "CCC", "CCC", "XXX")
                    .where('S', EPMetaTileEntities.VACUUM_DRYING_FURNACE, EnumFacing.SOUTH)
                    .where('X', getCasingState())
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[6], EnumFacing.NORTH)
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[6], EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[6], EnumFacing.SOUTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[6], EnumFacing.WEST)
                    .where('D', MetaTileEntities.FLUID_EXPORT_HATCH[6], EnumFacing.EAST)
                    .where('H', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('M', () -> {
                        return ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : EPMetablocks.EP_MULTIBLOCK_CASING.getState(EPBlockMultiblockCasing.CasingType.ADVANCED_INVAR_CASING);
                    }, EnumFacing.NORTH);
        }
        MultiblockShapeInfo.Builder finalBuilder = builder;
        GregTechAPI.HEATING_COILS.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getTier()))
                .forEach(entry -> {
                    if (finalBuilder != null) {
                        shapeInfo.add(finalBuilder.where('C', entry.getKey()).build());
                    }
                });
        return shapeInfo;
    }
}