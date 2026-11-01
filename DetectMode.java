package com.example.createmechanica.block;

import net.minecraft.util.StringRepresentable;

public enum DetectMode implements StringRepresentable {
    ALL, HOSTILE, PLAYERS;

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }

    public DetectMode next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
