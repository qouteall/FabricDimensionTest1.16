package net.fabricmc.example;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.DebugChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;

// A skyland dimension using vanilla terrain generation
public class ExampleChunkGenerator extends ChunkGenerator {
    
    public static final Codec<ExampleChunkGenerator> codec = MapCodec.of(Encoder.empty(), Decoder.unit(() -> {
        return new ExampleChunkGenerator(0);
    })).stable().codec();
    
    private final SurfaceChunkGenerator proxy;
    
    public ExampleChunkGenerator(
        long seed
    ) {
        super(
            new VanillaLayeredBiomeSource(seed, false, false),
            new StructuresConfig(true)
        );
        
        proxy = new SurfaceChunkGenerator(
            this.getBiomeSource(),
            seed,
            new ChunkGeneratorType.Preset("floating_islands", (preset) -> {
                return ChunkGeneratorType.Preset.createIslandsType(
                    new StructuresConfig(false),
                    Blocks.STONE.getDefaultState(),
                    Blocks.WATER.getDefaultState(),
                    preset,
                    false,
                    false
                );
            }).getChunkGeneratorType()
        );
    }
    
    @Override
    protected Codec<? extends ChunkGenerator> method_28506() {
        return codec;
    }
    
    @Override
    public ChunkGenerator withSeed(long seed) {
        return new ExampleChunkGenerator(seed);
    }
    
    @Override
    public void buildSurface(
        ChunkRegion region, Chunk chunk
    ) {
        proxy.buildSurface(region, chunk);
    }
    
    @Override
    public void populateNoise(
        WorldAccess world, StructureAccessor accessor, Chunk chunk
    ) {
        proxy.populateNoise(world, accessor, chunk);
    }
    
    //make end city and woodland mansion be able to generate
    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType) {
        return 64;
    }
    
    @Override
    public BlockView getColumnSample(int x, int z) {
        return proxy.getColumnSample(x, z);
    }
    
}
