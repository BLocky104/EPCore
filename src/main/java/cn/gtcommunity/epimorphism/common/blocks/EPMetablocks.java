package cn.gtcommunity.epimorphism.common.blocks;

import gregtech.api.unification.OreDictUnifier;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EPMetablocks {
    public static EPBlockGlassCasing EP_GLASS_CASING;
    public static EPBlockPMMACasing EP_PMMA_CASING;
    public static EPBlockMultiblockCasing EP_MULTIBLOCK_CASING;
    public static EPBlockMultiblockCasingB EP_MULTIBLOCK_CASING_B;
    public static EPBlockMultiblockCasingC EP_MULTIBLOCK_CASING_C;
    public static EPBlockCrucibleCasing EP_CRUCIBLE_CASING;
    public static EPBlockMillCasing EP_MILL_CASING;
    public static EPBlockActiveMultiblockCasing EP_ACTIVE_MULTIBLOCK_CASING;
    public static EPBlockComponentAssemblyLineCasing EP_COMPONENT_ASSEMBLY_LINE_CASING;
    public static EPBlockPCBFactoryCasing EP_PCB_FACTORY_CASING;
    public static EPBlockQuantumForceTransformerCasing EP_QUANTUM_FORCE_TRANSFORMER_CASING;
    public static EPBlockCleanroomCasing EP_CLEANROOM_CASING;
    public static EPBlockBoilerCasing EP_BOILER_CASING;
    public static EPBlockExplosive EP_EXPLOSIVE_BLOCK;
    public static EPBlockWireCoil EP_WIRE_COIL;
    public static EPBlockTransparentCasing EP_TRANSPARENT_CASING;
    public static EPBlockGlassCasingB EP_GLASS_CASING_B;
    public static EPBlockAdvGlass EP_ADV_GLASS_CASING;
    public static EPBlockFusionCasing EP_BLOCK_FUSION_CASING;
    public static EPBlockPineSapling EP_PINE_SAPLING;
    public static EPBlockPineLog EP_PINE_LOG;
    public static EPBlockPineLeaves EP_PINE_LEAVES;

    private EPMetablocks() {/**/}

    public static void init() {
        EP_GLASS_CASING = new EPBlockGlassCasing();
        EP_GLASS_CASING.setRegistryName("ep_glass_casing");
        EP_PMMA_CASING = new EPBlockPMMACasing();
        EP_PMMA_CASING.setRegistryName("ep_pmma_casing");
        EP_MULTIBLOCK_CASING = new EPBlockMultiblockCasing();
        EP_MULTIBLOCK_CASING.setRegistryName("ep_multiblock_casing");
        EP_MULTIBLOCK_CASING_B = new EPBlockMultiblockCasingB();
        EP_MULTIBLOCK_CASING_B.setRegistryName("ep_multiblock_casing_b");
        EP_MULTIBLOCK_CASING_C = new EPBlockMultiblockCasingC();
        EP_MULTIBLOCK_CASING_C.setRegistryName("ep_multiblock_casing_c");
        EP_CRUCIBLE_CASING = new EPBlockCrucibleCasing();
        EP_CRUCIBLE_CASING.setRegistryName("ep_crucible_casing");
        EP_MILL_CASING = new EPBlockMillCasing();
        EP_MILL_CASING.setRegistryName("ep_mill_casing");
        EP_ACTIVE_MULTIBLOCK_CASING = new EPBlockActiveMultiblockCasing();
        EP_ACTIVE_MULTIBLOCK_CASING.setRegistryName("ep_active_multiblock_casing");
        EP_COMPONENT_ASSEMBLY_LINE_CASING = new EPBlockComponentAssemblyLineCasing();
        EP_COMPONENT_ASSEMBLY_LINE_CASING.setRegistryName("ep_component_assembly_line_casing");
        EP_PCB_FACTORY_CASING = new EPBlockPCBFactoryCasing();
        EP_PCB_FACTORY_CASING.setRegistryName("ep_pcb_factory_casing");
        EP_QUANTUM_FORCE_TRANSFORMER_CASING = new EPBlockQuantumForceTransformerCasing();
        EP_QUANTUM_FORCE_TRANSFORMER_CASING.setRegistryName("ep_quantum_force_transformer_casing");
        EP_CLEANROOM_CASING = new EPBlockCleanroomCasing();
        EP_CLEANROOM_CASING.setRegistryName("ep_cleanroom_casing");
        EP_BOILER_CASING = new EPBlockBoilerCasing();
        EP_BOILER_CASING.setRegistryName("ep_boiler_casing");
        EP_EXPLOSIVE_BLOCK = new EPBlockExplosive();
        EP_EXPLOSIVE_BLOCK.setRegistryName("ep_explosive_block");
        EP_WIRE_COIL = new EPBlockWireCoil();
        EP_WIRE_COIL.setRegistryName("ep_wire_coil");
        EP_TRANSPARENT_CASING = new EPBlockTransparentCasing();
        EP_TRANSPARENT_CASING.setRegistryName("ep_transparent_casing");
        EP_GLASS_CASING_B = new EPBlockGlassCasingB();
        EP_GLASS_CASING_B.setRegistryName("ep_glass_casing_b");
        EP_ADV_GLASS_CASING = new EPBlockAdvGlass();
        EP_ADV_GLASS_CASING.setRegistryName("ep_adv_glass_casing");
        EP_BLOCK_FUSION_CASING = new EPBlockFusionCasing();
        EP_BLOCK_FUSION_CASING.setRegistryName("ep_fusion_casing");
        EP_PINE_SAPLING = new EPBlockPineSapling();
        EP_PINE_SAPLING.setRegistryName("ep_pine_sapling");
        EP_PINE_LOG = new EPBlockPineLog();
        EP_PINE_LOG.setRegistryName("ep_pine_log");
        EP_PINE_LEAVES = new EPBlockPineLeaves();
        EP_PINE_LEAVES.setRegistryName("ep_pine_leaves");

        Blocks.FIRE.setFireInfo(EP_PINE_LOG, 5, 5);
        Blocks.FIRE.setFireInfo(EP_PINE_LEAVES, 30, 60);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(EP_MULTIBLOCK_CASING);
        registerItemModel(EP_CRUCIBLE_CASING);
        registerItemModel(EP_MILL_CASING);
        registerItemModel(EP_MULTIBLOCK_CASING_B);
        registerItemModel(EP_MULTIBLOCK_CASING_C);
        registerItemModel(EP_COMPONENT_ASSEMBLY_LINE_CASING);
        registerItemModel(EP_PCB_FACTORY_CASING);
        registerItemModel(EP_QUANTUM_FORCE_TRANSFORMER_CASING);
        registerItemModel(EP_CLEANROOM_CASING);
        registerItemModel(EP_BOILER_CASING);
        registerItemModel(EP_EXPLOSIVE_BLOCK);
        registerItemModel(EP_ADV_GLASS_CASING);
        registerItemModel(EP_BLOCK_FUSION_CASING);

        registerItemModel(EP_PINE_LOG);
        registerItemModel(EP_PINE_LEAVES);
        registerItemModel(EP_PINE_SAPLING);

        //  VariantActiveBlock Registry
        EP_GLASS_CASING.onModelRegister();
        EP_PMMA_CASING.onModelRegister();
        EP_ACTIVE_MULTIBLOCK_CASING.onModelRegister();
        EP_WIRE_COIL.onModelRegister();
        EP_TRANSPARENT_CASING.onModelRegister();
        EP_GLASS_CASING_B.onModelRegister();
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors() {
        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();

        final int rubberLeavesColor = 0x98de4b;

        blockColors.registerBlockColorHandler((s, w, p, i) -> rubberLeavesColor, EP_PINE_LEAVES);
        itemColors.registerItemColorHandler((s, i) -> rubberLeavesColor, EP_PINE_LEAVES);

    }

    public static void registerOreDict() {
        OreDictUnifier.registerOre(new ItemStack(EP_PINE_LOG), "treeLeaves");
        OreDictUnifier.registerOre(new ItemStack(EP_PINE_LEAVES), "treeSapling");
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            MetaBlocks.statePropertiesToString(state.getProperties())));
        }
    }
}
