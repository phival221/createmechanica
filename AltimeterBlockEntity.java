package com.example.createmechanica.be;

import com.example.createmechanica.block.AltimeterBlock;
import com.example.createmechanica.compat.SubLevelUtil;
import com.example.createmechanica.config.MechanicaConfig;
import com.example.createmechanica.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AltimeterBlockEntity extends BlockEntity {
    public AltimeterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALTIMETER.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, AltimeterBlockEntity be) {
        if (level.getGameTime() % MechanicaConfig.SCAN_INTERVAL.get() != 0) return;

        Vec3 global = SubLevelUtil.globalCenter(level, pos);

        double min = MechanicaConfig.ALTIMETER_MIN_Y.get();
        double max = Math.max(min + 1, MechanicaConfig.ALTIMETER_MAX_Y.get());
        int power = Mth.clamp((int) Math.round((global.y - min) / (max - min) * 15.0), 0, 15);

        if (state.getValue(AltimeterBlock.POWER) != power)
            level.setBlock(pos, state.setValue(AltimeterBlock.POWER, power), Block.UPDATE_ALL);
    }
}
