package cn.gtcommunity.epimorphism.api.unification.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static cn.gtcommunity.epimorphism.api.unification.material.info.EPMaterialFlags.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static cn.gtcommunity.epimorphism.api.unification.EPMaterials.*;

public class EPModCompatibilityMaterials {
    //Range: 27001 - 27200
    private static int startId = 27001;
    private static final int END_ID = startId + 200;
    public static void init() {
        // 27000 Mana
        Mana = new Material.Builder(getMaterialsId(), gregtechId("mana"))
                .gas(new FluidBuilder().temperature(1))
                .color(0x00BFFF)
                .build();
        // 27001 Primal Mana
        PrimalMana = new Material.Builder(getMaterialsId(), gregtechId("primal_mana"))
                .gas(new FluidBuilder().temperature(1))
                .color(0x0000FF)
                .build();
        // 27002 Dawnstone
        Dawnstone = new Material.Builder(getMaterialsId(), gregtechId("dawnstone"))
                .ingot(3)
                .liquid()
                .blast(2300)
                .components(Gold, 1, Copper, 1)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, GENERATE_ROTOR, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_CURVED_PLATE)
                .build();
    }

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
