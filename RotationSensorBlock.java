package com.example.createmechanica.block;

import com.example.createmechanica.be.RotationSensorBlockEntity;
import com.example.createmechanica.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/** Датчик вращения: редстоун-сигнал 0..15 ≈ |RPM| / 16. */
public class RotationSensorBlock extends RotatedPillarKineticBlock implements IBE<RotationSensorBlockEntity> {
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    private static final VoxelShape CORE = Block.box(3, 3, 3, 13, 13, 13);
    private static final VoxelShape SHAPE_X = Shapes.or(CORE, Block.box(0, 6, 6, 16, 10, 10));
    private static final VoxelShape SHAPE_Y = Shapes.or(CORE, Block.box(6, 0, 6, 10, 16, 10));
    private static final VoxelShape SHAPE_Z = Shapes.or(CORE, Block.box(6, 6, 0, 10, 10, 16));

    public RotationSensorBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(POWER, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWER);
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == state.getValue(AXIS);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(AXIS);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return switch (state.getValue(AXIS)) {
            case X -> SHAPE_X;
            case Y -> SHAPE_Y;
            case Z -> SHAPE_Z;
        };
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return state.getValue(POWER);
    }

    @Override
    public Class<RotationSensorBlockEntity> getBlockEntityClass() {
        return RotationSensorBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends RotationSensorBlockEntity> getBlockEntityType() {
        return ModBlockEntities.ROTATION_SENSOR.get();
    }
}
