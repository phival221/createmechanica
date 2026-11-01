package com.example.createmechanica.registry;

import com.example.createmechanica.CreateMechanica;
import com.example.createmechanica.be.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreateMechanica.MOD_ID);

    public static final Supplier<BlockEntityType<GearRatioBlockEntity>> GEAR_RATIO = BLOCK_ENTITIES.register("gear_ratio",
            () -> BlockEntityType.Builder.of(GearRatioBlockEntity::new,
                    ModBlocks.GEAR_REDUCER.get(), ModBlocks.GEAR_MULTIPLIER.get()).build(null));

    public static final Supplier<BlockEntityType<RotationSensorBlockEntity>> ROTATION_SENSOR = BLOCK_ENTITIES.register("rotation_sensor",
            () -> BlockEntityType.Builder.of(RotationSensorBlockEntity::new, ModBlocks.ROTATION_SENSOR.get()).build(null));

    public static final Supplier<BlockEntityType<AltimeterBlockEntity>> ALTIMETER = BLOCK_ENTITIES.register("altimeter",
            () -> BlockEntityType.Builder.of(AltimeterBlockEntity::new, ModBlocks.ALTIMETER.get()).build(null));

    public static final Supplier<BlockEntityType<ProximitySensorBlockEntity>> PROXIMITY_SENSOR = BLOCK_ENTITIES.register("proximity_sensor",
            () -> BlockEntityType.Builder.of(ProximitySensorBlockEntity::new, ModBlocks.PROXIMITY_SENSOR.get()).build(null));

    private ModBlockEntities() {}
}
