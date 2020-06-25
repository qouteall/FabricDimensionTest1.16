package net.fabricmc.example.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.GeneratorOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
//    // Replace the ChunkGenerator with the Current Seed and Skip Vanilla Dimensions
//    @Redirect(
//        method = "createWorlds",
//        at = @At(
//            value = "INVOKE",
//            target = "Lnet/minecraft/world/gen/GeneratorOptions;getDimensionMap()Lnet/minecraft/util/registry/SimpleRegistry;"
//        )
//    )
//    private SimpleRegistry<DimensionOptions> onGetDimensionMap(GeneratorOptions generatorOptions) {
//        SimpleRegistry<DimensionOptions> dimensionMap = generatorOptions.getDimensionMap();
//
//        dimensionMap.getIds().forEach(dimensionId -> {
//            if (!isVanillaDimension(dimensionId)) {
//                DimensionOptions dimensionOptions = dimensionMap.get(dimensionId);
//                dimensionOptions.chunkGenerator =
//                    dimensionOptions.chunkGenerator.withSeed(generatorOptions.getSeed());
//            }
//        });
//
//        return dimensionMap;
//    }
//
//    @Unique
//    private static boolean isVanillaDimension(Identifier identifier) {
//        return identifier.equals(World.OVERWORLD.getValue()) ||
//            identifier.equals(World.NETHER.getValue()) ||
//            identifier.equals(World.END.getValue());
//    }
}
