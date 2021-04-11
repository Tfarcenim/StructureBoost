package tfar.structureboost;

import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(StructureBoost.MODID)
public class StructureBoost
{
    // Directly reference a log4j logger.

    public static final String MODID = "structureboost";

    public StructureBoost() {
        // Register the setup method for modloading
        MinecraftForge.EVENT_BUS.addListener(this::setup);

    }

    private void setup(final WorldEvent.Load event) {
        IWorld world = event.getWorld();
        if (!world.isRemote()) {
            ServerWorld serverWorld = (ServerWorld)world;
            ChunkGenerator chunkGenerator = serverWorld.getChunkProvider().generator;
            DimensionStructuresSettings dimensionStructuresSettings = chunkGenerator.func_235957_b_();
            Map<Structure<?>, StructureSeparationSettings> map = dimensionStructuresSettings.func_236195_a_();
            StructureSeparationSettings settings = map.get(Structure.VILLAGE);

            if (settings != null)//32, 8, 10387312
            map.put(Structure.VILLAGE,new StructureSeparationSettings(100,25,settings.func_236673_c_()));
        }
    }
}
