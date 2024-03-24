package cn.gtcommunity.epimorphism.loaders.recipe.multiblocks;

import gregtech.api.recipes.GTRecipeHandler;
import net.minecraftforge.fluids.FluidStack;

import static cn.gtcommunity.epimorphism.api.recipe.EPRecipeMaps.*;
import static cn.gtcommunity.epimorphism.api.unification.EPMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class RocketEngineRecipes {
    public static void init() {

        //  Remove rocket fuel Combustion generator recipe
        GTRecipeHandler.removeRecipesByInputs(COMBUSTION_GENERATOR_FUELS,
                new FluidStack[]{RocketFuel.getFluid(16)});

        //  Rocket Fuel
        ROCKET_ENGINE_RECIPES.recipeBuilder()
                .fluidInputs(RocketFuel.getFluid(16))
                .EUt(-2048)
                .duration(20)
                .buildAndRegister();

        //  RP-1 Rocket Fuel
        DISTILLERY_RECIPES.recipeBuilder()
                .fluidInputs(CoalTar.getFluid(50))
                .circuitMeta(0)
                .fluidOutputs(HighlyPurifiedCoalTar.getFluid(25))
                .EUt(VA[MV])
                .duration(16)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(HighlyPurifiedCoalTar.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(RP1RocketFuel.getFluid(1000))
                .EUt(VA[HV])
                .duration(16)
                .buildAndRegister();

        ROCKET_ENGINE_RECIPES.recipeBuilder()
                .fluidInputs(RP1RocketFuel.getFluid(12))
                .EUt(-2048)
                .duration(60)
                .buildAndRegister();

        //  Dense Hydrazine Mixture Fuel
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylhydrazine.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(DenseHydrazineMixtureFuel.getFluid(1000))
                .EUt(240)
                .duration(120)
                .buildAndRegister();

        ROCKET_ENGINE_RECIPES.recipeBuilder()
                .fluidInputs(DenseHydrazineMixtureFuel.getFluid(9))
                .EUt(-2048)
                .duration(80)
                .buildAndRegister();

        //  Methylhydrazine Nitrate Rocket Fuel
        DISSOLUTION_TANK_RECIPES.recipeBuilder()
                .input(dust, Carbon)
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Methylhydrazine.getFluid(1000))
                .EUt(VA[HV])
                .duration(480)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Methylhydrazine.getFluid(1000))
                .fluidInputs(Tetranitromethane.getFluid(1000))
                .fluidOutputs(MethylhydrazineNitrateRocketFuel.getFluid(1000))
                .EUt(VA[HV])
                .duration(200)
                .buildAndRegister();

        ROCKET_ENGINE_RECIPES.recipeBuilder()
                .fluidInputs(MethylhydrazineNitrateRocketFuel.getFluid(6))
                .EUt(-2048)
                .duration(120)
                .buildAndRegister();

    }
}
