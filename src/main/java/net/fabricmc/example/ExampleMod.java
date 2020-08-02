package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import oops.TestInterface;

public class ExampleMod implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		
		Registry.register(
			Registry.CHUNK_GENERATOR,
			new Identifier("dimtest:example_generator"),
			ExampleChunkGenerator.codec
		);
		
		TestInterface.oops();
	}
}
