package cn.gtcommunity.epimorphism.common.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

public class EPBlockFusionCasing extends VariantBlock<EPBlockFusionCasing.CasingType> {


    public EPBlockFusionCasing() {
        super(Material.IRON);
        this.setTranslationKey("fusion_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
    }

    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public static enum CasingType implements IStringSerializable {

        COMPRESSED_FUSION_REACTOR_MKI_CASING("compressed_fusion_reactor_mki_casing"),
        COMPRESSED_FUSION_REACTOR_MKII_CASING("compressed_fusion_reactor_mkii_casing"),
        COMPRESSED_FUSION_REACTOR_MKIII_CASING("compressed_fusion_reactor_mkiii_casing"),
        BEAM_CORE_0("beam_core_0"),
        BEAM_CORE_1("beam_core_1"),
        BEAM_CORE_2("beam_core_2"),
        BEAM_CORE_3("beam_core_3"),
        BEAM_CORE_4("beam_core_4"),
        CASING_FUSION_MKIV("machine_casing_fusion_4"),
        CASING_FUSION_MKV("machine_casing_fusion_5"),
        CASING_FUSION_MKVI("machine_casing_fusion_6");

        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

    }
}