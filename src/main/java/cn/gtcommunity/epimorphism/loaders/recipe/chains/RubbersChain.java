package cn.gtcommunity.epimorphism.loaders.recipe.chains;

import static cn.gtcommunity.epimorphism.api.recipe.EPRecipeMaps.*;
import static cn.gtcommunity.epimorphism.api.unification.EPMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;

public class RubbersChain {
    public static void init() {

        //  Acrylonitrile (EV): Acetylene + Hydrogen Cyanide -> Acrylonitrile
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .notConsumable(dust, Salt)
                .notConsumable(dust, CopperChloride)
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidOutputs(Acrylonitrile.getFluid(1000))
                .EUt(VA[EV])
                .duration(120)
                .CasingTier(2)
                .buildAndRegister();

        //  Acrylonitrile (LuV): Propene + Ammonia + Air + Water -> Acrylonitrile + Hydrogen
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .notConsumable(dust, SodiumPhosphomolybdate)
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Air.getFluid(4000))
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(Acrylonitrile.getFluid(6000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[LuV])
                .duration(120)
                .CasingTier(3)
                .buildAndRegister();

        //  Ferric Catalyst
        MIXER_RECIPES.recipeBuilder()
                .input(dust, FerricOxide, 1)
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .output(dust, FerricCatalyst, 2)
                .EUt(VA[MV])
                .duration(200)
                .buildAndRegister();

        //  Butadiene + Acrylonitrile -> Nitrile Butadiene Rubber
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .notConsumable(dust, FerricCatalyst)
                .fluidInputs(Butadiene.getFluid(1000))
                .fluidInputs(Acrylonitrile.getFluid(1000))
                .fluidOutputs(NitrileButadieneRubber.getFluid(1000))
                .EUt(VA[LuV])
                .duration(200)
                .temperature(286)
                .buildAndRegister();

        //  Carbon + Phosphoryl Chloride + Ammonium Chloride -> Phosphonitrilic Chloride Trimer + Steam + Diluted Hydrochloric Acid
        BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Carbon, 6)
                .fluidInputs(PhosphorylChloride.getFluid(3000))
                .fluidInputs(AmmoniumChloride.getFluid(3000))
                .fluidOutputs(PhosphonitrilicChlorideTrimer.getFluid(1000))
                .fluidOutputs(Steam.getFluid(3000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(6000))
                .EUt(VA[HV])
                .duration(140)
                .temperature(796)
                .buildAndRegister();

        //  Sodium Fluoride + Ethanol + Fluorine -> Sodium Trifluoroethanolate + Hydrogen
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .input(dust, SodiumFluoride, 6)
                .fluidInputs(Ethanol.getFluid(3000))
                .fluidInputs(Fluorine.getFluid(3000))
                .output(dust, SodiumTrifluoroethanolate, 3)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .temperature(344)
                .EUt(VA[EV])
                .duration(140)
                .buildAndRegister();

        //  Fluorobenzene + Hydrofluoric Acid + Oxygen -> Octafluoro Pentanol + Propene (Cycle)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(BLACKLIGHT)
                .fluidInputs(Fluorobenzene.getFluid(2000))
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(OctafluoroPentanol.getFluid(1000))
                .fluidOutputs(Propene.getFluid(2000))
                .EUt(VA[ZPM])
                .duration(340)
                .buildAndRegister();

        //  Sodium Trifluoroethanolate + Phosphonitrilic Chloride Trimer + Octafluoro Pentanol -> Poly Phosphonitrile Fluoro Rubber + Sodium Fluoride + Phosphoryl Chloride (Cycle)
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .input(dust, SodiumTrifluoroethanolate, 9)
                .fluidInputs(PhosphonitrilicChlorideTrimer.getFluid(1000))
                .fluidInputs(OctafluoroPentanol.getFluid(4000))
                .output(dust, SodiumFluoride, 3)
                .fluidOutputs(PolyPhosphonitrileFluoroRubber.getFluid(1000))
                .fluidOutputs(PhosphorylChloride.getFluid(3000))
                .EUt(VA[UV])
                .duration(400)
                .CasingTier(4)
                .buildAndRegister();
    }
}
