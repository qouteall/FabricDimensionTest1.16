package net.fabricmc.example.mixin;

import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChunkGenerator.class)
public abstract class MixinChunkGenerator {
    // Make this method to exist both in client and dedicated server
    public abstract ChunkGenerator withSeed(long seed);
}
