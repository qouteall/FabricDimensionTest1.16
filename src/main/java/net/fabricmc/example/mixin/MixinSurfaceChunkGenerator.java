package net.fabricmc.example.mixin;

import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

// This is only used in this test mod
@Mixin(SurfaceChunkGenerator.class)
public abstract class MixinSurfaceChunkGenerator extends ChunkGenerator {
    
    @Shadow
    @Final
    protected ChunkGeneratorType field_24774;
    
    public MixinSurfaceChunkGenerator(
        BiomeSource biomeSource,
        BiomeSource biomeSource2,
        StructuresConfig structuresConfig,
        long l
    ) {
        super(biomeSource, biomeSource2, structuresConfig, l);
    }
    
    // Make this method to exist in dedicated server
    public ChunkGenerator withSeed(long seed) {
        return new SurfaceChunkGenerator(this.biomeSource.withSeed(seed), seed, this.field_24774);
    }
}
