package cn.gtcommunity.epimorphism.api.unification.materials;

import cn.gtcommunity.epimorphism.api.unification.EPElements;
import gregtech.api.fluids.fluidType.FluidTypes;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.ToolProperty;
import net.minecraft.init.Enchantments;

import static cn.gtcommunity.epimorphism.api.unification.material.info.EPMaterialFlags.*;
import static cn.gtcommunity.epimorphism.api.unification.material.info.EPMaterialIconSet.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static cn.gtcommunity.epimorphism.api.unification.EPMaterials.*;

public class EPElementMaterials {
    //  Range 26001-26100
    private static int startId = 26001;
    private static final int END_ID = startId + 100;

    public static void register() {

        //  26001 Draconium
        Draconium = new Material.Builder(getMaterialsId(), gregtechId("draconium"))
                .ingot()
                .fluid()
                .color(0xbe49ed)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, GENERATE_DOUBLE_PLATE, GENERATE_ROTOR, GENERATE_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME, GENERATE_CURVED_PLATE, GENERATE_DENSE)
                .element(EPElements.Draconium)
                .toolStats(ToolProperty.Builder.of(7.0F, 25.0F, 17000, 22)
                                       .magnetic()
                                       .enchantment(Enchantments.EFFICIENCY, 5)
                                       .enchantment(Enchantments.FORTUNE, 5)
                                       .build())
                .blastTemp(10800, BlastProperty.GasTier.HIGHEST, VA[UHV])
                .build();
        //  26002 Awakened Draconium
        AwakenedDraconium = new Material.Builder(getMaterialsId(), gregtechId("awakened_draconium"))
                .ingot()
                .fluid()
                .color(0xf58742)
                .iconSet(BRIGHT)
                .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .element(EPElements.AwakenedDraconium)
                .blastTemp(10800, BlastProperty.GasTier.HIGHEST, VA[UV])
                .cableProperties(V[UHV], 16, 4)
                .build();
        //  26003 Chaotic Draconium
        ChaoticDraconium = new Material.Builder(getMaterialsId(), gregtechId("chaotic_draconium"))
                .ingot()
                .fluid()
                .color(0x2C195A)
                .iconSet(SHINY)
                .flags(NO_SMELTING, GENERATE_PLATE, GENERATE_DOUBLE_PLATE)
                .element(EPElements.ChaoticDraconium)
                .cableProperties(V[UEV], 32, 16)
                .build();
        //  Pay attention to these materials, they are just foil (not foil in gregtech)!
        //  26004 Orichalcum
        Orichalcum = new Material.Builder(getMaterialsId(), gregtechId("orichalcum"))
                .ingot()
                .fluid()
                .color(0x72A0C1)
                .iconSet(METALLIC)
                .element(EPElements.Orichalcum)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME, GENERATE_GEAR)
                .blastTemp(9000, BlastProperty.GasTier.HIGH)
                .build();
        //  26005 Vibranium
        Vibranium = new Material.Builder(getMaterialsId(), gregtechId("vibranium"))
                .ingot()
                .fluid()
                .plasma()
                .color(0xC880FF)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_DOUBLE_PLATE, GENERATE_FINE_WIRE, GENERATE_FRAME, GENERATE_COIL, GENERATE_BOLT_SCREW)
                .element(EPElements.Vibranium)
                .blastTemp(4852, BlastProperty.GasTier.HIGH)
                .build();
        //  26006 Adamantium
        Adamantium = new Material.Builder(getMaterialsId(), gregtechId("adamantium"))
                .ingot()
                .fluid()
                .plasma()
                .color(0xFF0040)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROTOR, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_SPRING, GENERATE_CURVED_PLATE, GENERATE_DOUBLE_PLATE)
                .element(EPElements.Adamantium)
                .blastTemp(5225, BlastProperty.GasTier.HIGH)
                .cableProperties(VA[UHV], 18, 9, false)
                .build();
        //  26007 Taranium
        Taranium = new Material.Builder(getMaterialsId(), gregtechId("taranium"))
                .ingot()
                .fluid()
                .plasma()
                .color(0x4F404F)
                .iconSet(METALLIC)
                .element(EPElements.Taranium)
                .flags(GENERATE_PLATE, GENERATE_DOUBLE_PLATE)
                .build();
        //  26008 Plutonium-244
        Plutonium244 = new Material.Builder(getMaterialsId(), gregtechId("plutonium_244"))
                .ingot()
                .fluid()
                .color(0xF03232)
                .iconSet(SHINY)
                .element(EPElements.Plutonium244)
                .build();
        //  26009 Metastable Oganesson
        MetastableOganesson = new Material.Builder(getMaterialsId(), gregtechId("metastable_oganesson"))
                .ingot()
                .fluid(FluidTypes.GAS)
                .color(0xE61C24)
                .iconSet(SHINY)
                .element(Elements.Og)
                .flags(GENERATE_PLATE)
                .blastTemp(10380)
                .build();
        //  TODO Radium-Radon Mixture + Scandium-Titanium-50 Mixture -> Metastable Hassium
        //  26010 Metastable Hassium
        MetastableHassium = new Material.Builder(getMaterialsId(), gregtechId("metastable_hassium"))
                .ingot()
                .fluid()
                .color(0x2D3A9D)
                .iconSet(BRIGHT)
                .element(Elements.Hs)
                .flags(GENERATE_ROD, GENERATE_PLATE)
                .build();
        //  26011 Metastable Flerovium
        MetastableFlerovium = new Material.Builder(getMaterialsId(), gregtechId("metastable_flerovium"))
                .ingot()
                .fluid()
                .color(0x521973)
                .iconSet(SHINY)
                .element(Elements.Fl)
                .flags(GENERATE_ROD)
                .build();
        //  26012 Cosmic Neutronium
        CosmicNeutronium = new Material.Builder(getMaterialsId(), gregtechId("cosmic_neutronium"))
                .ingot()
                .fluid()
                .color(0x323232)
                .iconSet(BRIGHT)
                .flags(NO_SMELTING, NO_SMASHING, GENERATE_FINE_WIRE)
                .fluidTemp(2000000000)
                .element(EPElements.CosmicNeutronium)
                .cableProperties(V[UIV], 256, 128, false)
                .build();
        //  26013 Degenerate Rhenium
        DegenerateRhenium = new Material.Builder(getMaterialsId(), gregtechId("degenerate_rhenium"))
                .dust()
                .fluid(FluidTypes.PLASMA)
                .fluidTemp((int) V[UV])
                .color(0x6666FF)
                .iconSet(CUSTOM_DEGENERATE_RHENIUM)
                .element(Elements.Rh)
                .flags(GENERATE_PLATE)
                .build()
                .setFormula("§cR§de", false);
        //  26014 Infinity
        Infinity = new Material.Builder(getMaterialsId(), gregtechId("infinity"))
                .ingot()
                .fluid()
                .iconSet(CUSTOM_INFINITY)
                .fluidTemp((int) V[UIV])
                .element(EPElements.Infinity)
                .blastTemp(12600, BlastProperty.GasTier.HIGHEST, VA[UHV], 5901)
                .flags(GENERATE_PLATE, GENERATE_DOUBLE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_RING, GENERATE_ROUND, GENERATE_BOLT_SCREW, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .build();
        //  26015 Rhugnor
        Rhugnor = new Material.Builder(getMaterialsId(), gregtechId("rhugnor"))
                .ingot()
                .fluid()
                .fluidTemp((int) (V[UIV] - V[UV]))
                .color(0xBE00FF)
                .iconSet(BRIGHT)
                .element(EPElements.Rhugnor)
                .blastTemp(12000, BlastProperty.GasTier.HIGHEST, VA[UHV], 3340)
                .flags(GENERATE_PLATE)
                .build();
        //  26016 Hypogen
        Hypogen = new Material.Builder(getMaterialsId(), gregtechId("hypogen"))
                .ingot()
                .fluid()
                .plasma()
                .fluidTemp((int) (V[UXV] - V[LuV]))
                .element(EPElements.Hypogen)
                .color(0xDC784B)
                .iconSet(CUSTOM_HYPOGEN)
                .toolStats(ToolProperty.Builder.of(20.0F, 200.0F, 2000000, 200)
                                       .unbreakable()
                                       .enchantability(33)
                                       .magnetic()
                                       .enchantment(Enchantments.SHARPNESS, 10)
                                       .enchantment(Enchantments.LOOTING, 5)
                                       .enchantment(Enchantments.SWEEPING, 3).build())
                .flags(GENERATE_PLATE, GENERATE_DOUBLE_PLATE)
                .build();
        //  26017 Californium-252
        Californium252 = new Material.Builder(getMaterialsId(), gregtechId("californium_252"))
                .ingot()
                .fluid()
                .iconSet(SHINY)
                .element(EPElements.Californium252)
                .color(Californium.getMaterialRGB())
                .build();
        //  26018 Astral Titanium
        AstralTitanium = new Material.Builder(getMaterialsId(), gregtechId("astral_titanium"))
                .ingot()
                .fluid()
                .plasma()
                .color(0xDCA0F0)
                .iconSet(BRIGHT)
                //  TODO may be re-balance
                .blastTemp(12000, BlastProperty.GasTier.HIGHEST, VA[UHV])
                .element(EPElements.AstralTitanium)
                .flags(GENERATE_PLATE, GENERATE_FOIL)
                .build();
        //  26019 Celestial Tungsten
        CelestialTungsten = new Material.Builder(getMaterialsId(), gregtechId("celestial_tungsten"))
                .ingot()
                .fluid()
                .plasma()
                .color(0x323232)
                .iconSet(BRIGHT)
                //  TODO may be re-balance
                .blastTemp(12000, BlastProperty.GasTier.HIGHEST, VA[UHV])
                .element(EPElements.CelestialTungsten)
                .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_ROD, GENERATE_BOLT_SCREW)
                .build();
        //  26020 Ytterbium-178
        Ytterbium178 = new Material.Builder(getMaterialsId(), gregtechId("ytterbium_178"))
                .dust()
                .fluid()
                .color(Ytterbium.getMaterialRGB())
                .iconSet(SHINY)
                .element(EPElements.Ytterbium178)
                .build();
        //  26021 Ichorium
        Ichorium = new Material.Builder(getMaterialsId(), gregtechId("ichorium"))
                .ingot()
                .fluid()
                .color(0xE5A559)
                .iconSet(BRIGHT)
                .blastTemp(10800, BlastProperty.GasTier.HIGHEST, VA[UV])
                .element(EPElements.Ichorium)
                .flags(GENERATE_PLATE)
                .build();
        //  26022 Ichor Liquid
        IchorLiquid = new Material.Builder(getMaterialsId(), gregtechId("ichor_liquid"))
                .fluid(FluidTypes.PLASMA)
                .fluidTemp(214748)
                .color(0xE5A559)
                .element(EPElements.IchorLiquid)
                .build();
        //  26023 Crystal Matrix
        CrystalMatrix = new Material.Builder(getMaterialsId(), gregtechId("crystal_matrix"))
                .ingot()
                .fluid()
                .fluidTemp(660450)
                .color(0x70ecff)
                .iconSet(BRIGHT)
                .element(EPElements.CrystalMatrix)
                .build();
        //  26024 Void Metal
        VoidMetal = new Material.Builder(getMaterialsId(), gregtechId("void_metal"))
                .ingot()
                .fluid()
                .fluidTemp(0)
                .color(0x20142C)
                .iconSet(DULL)
                .element(EPElements.VoidMetal)
                .build();
        //  26025 Mithril
        Mithril = new Material.Builder(getMaterialsId(), gregtechId("mithril"))
                .ingot()
                .fluid()
                .plasma()
                .fluidTemp(4550)
                .color(0x428fdb)
                .iconSet(DULL)
                .blastTemp(10900, BlastProperty.GasTier.HIGHEST, VA[UHV])
                .element(EPElements.Mithril)
                .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .build();
        //  26026 Bismuth-209
        Bismuth209 = new Material.Builder(getMaterialsId(), gregtechId("bismuth_209"))
                .fluid()
                .color(Bismuth.getMaterialRGB())
                .element(EPElements.Bismuth209)
                .build();
        //  26027 Lead-209
        Lead209 = new Material.Builder(getMaterialsId(), gregtechId("lead_209"))
                .fluid()
                .color(Lead.getMaterialRGB())
                .element(EPElements.Lead209)
                .build();
    }

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
