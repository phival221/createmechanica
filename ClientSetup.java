package com.example.createmechanica.client;

import com.example.createmechanica.CreateMechanica;
import com.example.createmechanica.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftRenderer;
import com.simibubi.create.content.kinetics.transmission.SplitShaftRenderer;
import com.simibubi.create.content.kinetics.transmission.SplitShaftVisual;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/** Переиспользуем анимированные рендеры и визуалы Create: вращающиеся валы без собственных моделей. */
@EventBusSubscriber(modid = CreateMechanica.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientSetup {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GEAR_RATIO.get(), SplitShaftRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ROTATION_SENSOR.get(), ShaftRenderer::new);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            SimpleBlockEntityVisualizer.builder(ModBlockEntities.GEAR_RATIO.get())
                    .factory(SplitShaftVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntities.ROTATION_SENSOR.get())
                    .factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
        });
    }

    private ClientSetup() {}
}
