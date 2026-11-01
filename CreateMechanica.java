package com.example.createmechanica;

import com.example.createmechanica.config.MechanicaConfig;
import com.example.createmechanica.registry.ModBlockEntities;
import com.example.createmechanica.registry.ModBlocks;
import com.example.createmechanica.registry.ModCreativeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(CreateMechanica.MOD_ID)
public class CreateMechanica {
    public static final String MOD_ID = "createmechanica";

    public CreateMechanica(IEventBus modBus, ModContainer container) {
        ModBlocks.BLOCKS.register(modBus);
        ModBlocks.ITEMS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModCreativeTab.TABS.register(modBus);
        container.registerConfig(ModConfig.Type.COMMON, MechanicaConfig.SPEC);
    }
}
