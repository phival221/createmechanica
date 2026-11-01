package com.example.createmechanica.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class MechanicaConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.IntValue ALTIMETER_MIN_Y;
    public static final ModConfigSpec.IntValue ALTIMETER_MAX_Y;
    public static final ModConfigSpec.IntValue PROXIMITY_RANGE;
    public static final ModConfigSpec.IntValue SCAN_INTERVAL;

    static {
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        b.push("altimeter");
        ALTIMETER_MIN_Y = b.comment("Глобальная высота, при которой сигнал = 0")
                .defineInRange("minY", -64, -2048, 4096);
        ALTIMETER_MAX_Y = b.comment("Глобальная высота, при которой сигнал = 15")
                .defineInRange("maxY", 320, -2048, 4096);
        b.pop();
        b.push("proximity_sensor");
        PROXIMITY_RANGE = b.comment("Радиус обнаружения (блоков)")
                .defineInRange("range", 8, 1, 64);
        b.pop();
        SCAN_INTERVAL = b.comment("Период обновления датчиков (тиков)")
                .defineInRange("scanInterval", 5, 1, 100);
        SPEC = b.build();
    }

    private MechanicaConfig() {}
}
