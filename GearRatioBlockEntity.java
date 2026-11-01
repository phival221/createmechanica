package com.example.createmechanica.be;

import com.example.createmechanica.block.GearRatioBlock;
import com.example.createmechanica.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class GearRatioBlockEntity extends SplitShaftBlockEntity {
    public GearRatioBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEAR_RATIO.get(), pos, state);
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        if (hasSource() && face != getSourceFacing() && getBlockState().getBlock() instanceof GearRatioBlock g)
            return g.ratio;
        return 1;
    }

    @Override
    public float calculateStressApplied() {
        return 0;
    }
}
