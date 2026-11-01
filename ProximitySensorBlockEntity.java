package com.example.createmechanica.be;

import com.example.createmechanica.block.DetectMode;
import com.example.createmechanica.block.ProximitySensorBlock;
import com.example.createmechanica.compat.SubLevelUtil;
import com.example.createmechanica.config.MechanicaConfig;
import com.example.createmechanica.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ProximitySensorBlockEntity extends BlockEntity {
    public ProximitySensorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PROXIMITY_SENSOR.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, ProximitySensorBlockEntity be) {
        if (level.getGameTime() % MechanicaConfig.SCAN_INTERVAL.get() != 0) return;

        double range = MechanicaConfig.PROXIMITY_RANGE.get();
        DetectMode mode = state.getValue(ProximitySensorBlock.MODE);

        Vec3 center = SubLevelUtil.globalCenter(level, pos);
        AABB box = new AABB(center, center).inflate(range);

        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, box,
                e -> e.isAlive() && !e.isSpectator() && matches(mode, e));

        double best = Double.MAX_VALUE;
        for (LivingEntity e : targets) {
            Vec3 ep = SubLevelUtil.toGlobal(level, e.getBoundingBox().getCenter());
            best = Math.min(best, ep.distanceTo(center));
        }

        int power = 0;
        if (best <= range) power = Mth.clamp(15 - (int) (best / range * 14.0), 1, 15);

        if (state.getValue(ProximitySensorBlock.POWER) != power)
            level.setBlock(pos, state.setValue(ProximitySensorBlock.POWER, power), Block.UPDATE_ALL);
    }

    private static boolean matches(DetectMode mode, LivingEntity e) {
        return switch (mode) {
            case ALL -> true;
            case HOSTILE -> e instanceof Enemy;
            case PLAYERS -> e instanceof Player;
        };
    }
}
