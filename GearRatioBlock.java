package com.example.createmechanica.block;

import com.example.createmechanica.be.GearRatioBlockEntity;
import com.example.createmechanica.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.simpleRelays.AbstractEncasedShaftBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.world.level.block.entity.BlockEntityType;

/** Сквозная коробка передач: ratio < 1 — редуктор, ratio > 1 — мультипликатор. */
public class GearRatioBlock extends AbstractEncasedShaftBlock implements IBE<GearRatioBlockEntity> {
    public final float ratio;

    public GearRatioBlock(Properties properties, float ratio) {
        super(properties);
        this.ratio = ratio;
    }

    @Override
    public Class<GearRatioBlockEntity> getBlockEntityClass() {
        return GearRatioBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends GearRatioBlockEntity> getBlockEntityType() {
        return ModBlockEntities.GEAR_RATIO.get();
    }
}
