package cn.gtcommunity.epimorphism.api.unification.materials;

import gregtech.api.fluids.fluidType.FluidTypes;
import gregtech.api.unification.material.Material;

import static gregtech.api.util.GTUtility.gregtechId;
import static cn.gtcommunity.epimorphism.api.unification.EPMaterials.*;

public class EPUnknownCompositionMaterials {
    //  Range 25201-25400
    public static void register() {
        //  25201 BZMedium
        BZMedium = new Material.Builder(25201, gregtechId("bz_medium"))
                .fluid()
                .color(10681653)
                .build();
        //  25202 EDP
        EDP = new Material.Builder(25202, gregtechId("edp"))
                .fluid()
                .color(16514839)
                .build();
        //  25203 Rich Nitrogen Mixture
        RichNitrogenMixture = new Material.Builder(25203, gregtechId("rich_nitrogen_mixture"))
                .fluid(FluidTypes.GAS)
                .color(0x6891D8)
                .build();
        //  25204 Rich Ammonia Mixture
        RichAmmoniaMixture = new Material.Builder(25204, gregtechId("rich_ammonia_mixture"))
                .fluid()
                .color(0x708ACD)
                .build();
    }
}
