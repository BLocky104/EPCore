package cn.gtcommunity.epimorphism.loaders.recipe.chains;

import static cn.gtcommunity.epimorphism.api.recipe.EPRecipeMaps.*;
import static cn.gtcommunity.epimorphism.api.unification.EPMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class SodiumChain {
    public static void init() {

        //  Sodium Tungstate
        ROASTER_RECIPES.recipeBuilder()
                .input(dust, Scheelite, 7)
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .output(dust, CalciumHydroxide, 10)
                .fluidOutputs(SodiumTungstate.getFluid(1000))
                .EUt(VA[HV])
                .duration(110)
                .temperature(1134)
                .buildAndRegister();

        //  Sodium Molybdate
        ROASTER_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 6)
                .input(dust, MolybdenumTrioxide, 4)
                .output(dust, SodiumMolybdate, 7)
                .fluidOutputs(Steam.getFluid(1000))
                .EUt(VA[HV])
                .duration(110)
                .temperature(1128)
                .buildAndRegister();

        //  Sodium Tungstate + Phosphoric Acid -> Sodium Phosphotungstate + Sodium Hydroxide + Sodium Oxide
        BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(SodiumTungstate.getFluid(12000))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .output(dust, SodiumPhosphotungstate, 56)
                .output(dust, SodiumHydroxide, 3)
                .output(dust, SodiumOxide, 30)
                .EUt(VA[EV])
                .duration(480)
                .temperature(1442)
                .buildAndRegister();

        //  Sodium Molybdate + Phosphoric Acid -> Sodium Phosphomolybdate + Sodium Hydroxide + Sodium Oxide
        BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, SodiumMolybdate, 64)
                .input(dust, SodiumMolybdate, 20)
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .output(dust, SodiumPhosphomolybdate, 56)
                .output(dust, SodiumHydroxide, 3)
                .output(dust, SodiumOxide, 30)
                .EUt(VA[EV])
                .duration(480)
                .temperature(1394)
                .buildAndRegister();
    }
}
