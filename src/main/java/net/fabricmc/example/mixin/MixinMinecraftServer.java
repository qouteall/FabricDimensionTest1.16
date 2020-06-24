package net.fabricmc.example.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.GeneratorOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Redirect(
        method = "createWorlds",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/gen/GeneratorOptions;getDimensionMap()Lnet/minecraft/util/registry/SimpleRegistry;"
        )
    )
    private SimpleRegistry<DimensionOptions> onGetDimensionMap(GeneratorOptions generatorOptions) {
        SimpleRegistry<DimensionOptions> dimensionMap = generatorOptions.getDimensionMap();
        
        dimensionMap.forEach(dimensionOptions -> {
            dimensionOptions.chunkGenerator = dimensionOptions.chunkGenerator.withSeed(generatorOptions.getSeed());
        });
        
        return dimensionMap;
    }
}
