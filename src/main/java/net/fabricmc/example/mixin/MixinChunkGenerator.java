package net.fabricmc.example.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChunkGenerator.class)
public abstract class MixinChunkGenerator {
//    // Make this method to exist in dedicated server
//    public abstract ChunkGenerator withSeed(long seed);
//
//    public abstract ChunkGenerator method_27997(long seed);
}
