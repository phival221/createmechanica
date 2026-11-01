package com.example.createmechanica.registry;

import com.example.createmechanica.CreateMechanica;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateMechanica.MOD_ID);

    public static final Supplier<CreativeModeTab> MAIN = TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.createmechanica"))
            .icon(() -> new ItemStack(ModBlocks.ROTATION_SENSOR.get()))
            .displayItems((params, out) -> ModBlocks.ITEMS.getEntries().forEach(e -> out.accept(e.get())))
            .build());

    private ModCreativeTab() {}
}
