package com.example.createmechanica.compat;

import dev.ryanhcode.sable.companion.SableCompanion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Единая точка совместимости с Sable / Create Aeronautics.
 * Блоки на корабле лежат в "plot grid" с огромными координатами; любая логика,
 * зависящая от реального положения (высота, расстояния), переводит позицию в глобальное
 * пространство. Без Sable Companion возвращает позицию как есть.
 */
public final class SubLevelUtil {
    private SubLevelUtil() {}

    public static Vec3 globalCenter(Level level, BlockPos pos) {
        return toGlobal(level, Vec3.atCenterOf(pos));
    }

    public static Vec3 toGlobal(Level level, Vec3 position) {
        return SableCompanion.INSTANCE.projectOutOfSubLevel(level, position);
    }
}
