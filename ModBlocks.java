package com.example.createmechanica.registry;

import com.example.createmechanica.CreateMechanica;
import com.example.createmechanica.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateMechanica.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateMechanica.MOD_ID);

    public static final DeferredBlock<GearRatioBlock> GEAR_REDUCER =
            reg("gear_reducer", () -> new GearRatioBlock(props(MapColor.STONE), 0.5f));
    public static final DeferredBlock<GearRatioBlock> GEAR_MULTIPLIER =
            reg("gear_multiplier", () -> new GearRatioBlock(props(MapColor.GOLD), 2.0f));
    public static final DeferredBlock<RotationSensorBlock> ROTATION_SENSOR =
            reg("rotation_sensor", () -> new RotationSensorBlock(props(MapColor.STONE).noOcclusion()));
    public static final DeferredBlock<AltimeterBlock> ALTIMETER =
            reg("altimeter", () -> new AltimeterBlock(props(MapColor.GOLD)));
    public static final DeferredBlock<ProximitySensorBlock> PROXIMITY_SENSOR =
            reg("proximity_sensor", () -> new ProximitySensorBlock(props(MapColor.GOLD)));

    private static BlockBehaviour.Properties props(MapColor color) {
        return BlockBehaviour.Properties.of().mapColor(color).strength(2.0f, 3.0f).sound(SoundType.WOOD);
    }

    private static <T extends Block> DeferredBlock<T> reg(String name, Supplier<T> factory) {
        DeferredBlock<T> block = BLOCKS.register(name, factory);
        ITEMS.registerSimpleBlockItem(name, block);
        return block;
    }

    @SuppressWarnings("unchecked")
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> ticker(
            BlockEntityType<A> actual, BlockEntityType<E> expected, BlockEntityTicker<? super E> ticker) {
        return expected == actual ? (BlockEntityTicker<A>) ticker : null;
    }

    private ModBlocks() {}
}
