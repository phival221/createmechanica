package com.example.createmechanica.be;

import com.example.createmechanica.block.RotationSensorBlock;
import com.example.createmechanica.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RotationSensorBlockEntity extends KineticBlockEntity {
    public RotationSensorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ROTATION_SENSOR.get(), pos, state);
    }

    @Override
    public void onSpeedChanged(float previousSpeed) {
        super.onSpeedChanged(previousSpeed);
        updatePower();
    }

    public void updatePower() {
        if (level == null || level.isClientSide) return;
        float speed = Math.abs(getSpeed());
        int power = speed == 0 ? 0 : Mth.clamp((int) Math.ceil(speed / 16f), 1, 15);
        BlockState state = getBlockState();
        if (state.hasProperty(RotationSensorBlock.POWER) && state.getValue(RotationSensorBlock.POWER) != power)
            level.setBlock(worldPosition, state.setValue(RotationSensorBlock.POWER, power), Block.UPDATE_ALL);
    }

    @Override
    public float calculateStressApplied() {
        return 0;
    }
}
