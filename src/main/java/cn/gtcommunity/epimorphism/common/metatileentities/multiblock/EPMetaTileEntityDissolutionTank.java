package cn.gtcommunity.epimorphism.common.metatileentities.multiblock;

import cn.gtcommunity.epimorphism.api.recipe.EPRecipeMaps;
import cn.gtcommunity.epimorphism.client.textures.EPTextures;
import cn.gtcommunity.epimorphism.common.blocks.EPBlockGlassCasing;
import cn.gtcommunity.epimorphism.common.blocks.EPMetablocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class EPMetaTileEntityDissolutionTank extends RecipeMapMultiblockController {
    public EPMetaTileEntityDissolutionTank(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, EPRecipeMaps.DISSOLUTION_TANK_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new EPMetaTileEntityDissolutionTank(metaTileEntityId);
    }

    @Override
    public boolean checkRecipe(@Nonnull Recipe recipe, boolean consumeIfSuccess) {
        if (super.checkRecipe(recipe, consumeIfSuccess)) {
            List<IItemHandlerModifiable> itemInputInventory = getAbilities(MultiblockAbility.IMPORT_ITEMS);
            List<IFluidTank> fluidInputInventory = getAbilities(MultiblockAbility.IMPORT_FLUIDS);
            double ratio = 1;
            int count = 0;
            for (IFluidTank input : fluidInputInventory) {
                for (GTRecipeInput recipe_input : recipe.getFluidInputs()) {
                    if (recipe_input.acceptsFluid(input.getFluid())) {
                        if (count == 0) {
                            ratio = (double) recipe_input.getAmount() / input.getFluidAmount();
                            count = 1;
                        } else {
                            if (!(ratio == (double) recipe_input.getAmount() / input.getFluidAmount())) {
                                return false;
                            }
                        }
                    }
                }
            }
            for (IItemHandlerModifiable input : itemInputInventory) {
                for (GTRecipeInput recipe_input : recipe.getInputs()) {
                    if (recipe_input.isNonConsumable()) {
                        continue;
                    }
                    for (int index = 0; index < input.getSlots(); index++) {
                        // Skip this slot if it is empty.
                        final ItemStack currentInputItem = input.getStackInSlot(index);
                        if (currentInputItem.isEmpty())
                            continue;
                        if (recipe_input.acceptsStack(input.getStackInSlot(index))) {
                            if (count == 1) {
                                if (!(ratio == (double) recipe_input.getAmount() / input.getStackInSlot(index).getCount())) {
                                    return false;
                                }
                            } else {
                                ratio = (double) recipe_input.getAmount() / input.getStackInSlot(index).getCount();
                                count = 1;
                            }
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("M   M", "MMMMM", "MGGGM", "MGGGM", " MMM ")
                .aisle("     ", "MNNNM", "G###G", "G###G", "MMMMM")
                .aisle("     ", "MNNNM", "G###G", "G###G", "MMMMM")
                .aisle("     ", "MNNNM", "G###G", "G###G", "MMMMM")
                .aisle("M   M", "MMSMM", "MGGGM", "MGGGM", " MMM ")
                .where('S', selfPredicate())
                .where('M', states(getCasingAState()).or(autoAbilities()))
                .where('G', states(getGlassState()))
                .where('N', states(getCasingBState()))
                .where('#', air())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingAState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    private static IBlockState getCasingBState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    private static IBlockState getGlassState() {
        return EPMetablocks.EP_GLASS_CASING.getState(EPBlockGlassCasing.CasingType.OSMIR_BORON_SILICATE_GLASS);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return EPTextures.CVD_UNIT_OVERLAY;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("epimorphism.machine.dissolution_tank.tooltip.1"));
    }
}
