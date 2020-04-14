/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class ElementsApocalypseBlocks.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it in
 * "Workspace" -> "Source" menu.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.mcreator.apocalypseblocks;

import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraft.util.math.BlockPos;

public class ApocalypseEventHandler extends ElementsApocalypseBlocks.ModElement
{
    public WorldData worldFileCache;
    public static BlockPos spawnLocation;
    public static boolean spawnSet;
    public static boolean bunkerSpawned;
    
    public ApocalypseEventHandler(final ElementsApocalypseBlocks instance) {
        super(instance, 7);
    }
    
    public void init(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void loadData(final WorldEvent.Load event) {
        this.worldFileCache = new WorldData("saves/" + FMLCommonHandler.instance().getMinecraftServerInstance().func_71270_I() + "/data/apocalypse_cache.dat");
        if (!this.worldFileCache.checkIfExists()) {
            ApocalypseEventHandler.bunkerSpawned = false;
            ApocalypseEventHandler.spawnSet = false;
            this.worldFileCache.createFile();
            System.out.println("File does not exists");
        }
        else if (this.worldFileCache.isSpawnSet()) {
            System.out.println("File has spawn loc");
            ApocalypseEventHandler.spawnLocation = this.worldFileCache.loadSpawnLoc();
            ApocalypseEventHandler.bunkerSpawned = true;
            ApocalypseEventHandler.spawnSet = true;
        }
        System.out.println("ToSpawn:" + ApocalypseEventHandler.spawnSet);
    }
    
    @SubscribeEvent
    public void onRespawnEvent(final LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)event.getEntityLiving();
            if (!ApocalypseEventHandler.spawnSet && ApocalypseEventHandler.spawnLocation != null) {
                final BlockPos upPos = ApocalypseEventHandler.spawnLocation.func_177981_b(1);
                if (player.func_180425_c().func_177956_o() == upPos.func_177956_o()) {
                    ApocalypseEventHandler.spawnSet = true;
                }
                player.func_174828_a(ApocalypseEventHandler.spawnLocation.func_177981_b(1), 0.0f, 0.0f);
            }
        }
    }
    
    @SubscribeEvent
    public void setSpawnpointEvent(final EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)event.getEntity();
            if (this.isNewPlayer(player)) {
                if (ApocalypseEventHandler.spawnLocation == null && player.func_180425_c() != BlockPos.field_177992_a) {
                    System.out.println("Setting spawnLocation to below player");
                    ApocalypseEventHandler.spawnLocation = player.func_180425_c().func_177979_c(11);
                }
                if (!ApocalypseEventHandler.spawnSet && ApocalypseEventHandler.spawnLocation != null) {
                    System.out.println("spawnSet false");
                    if (!ApocalypseEventHandler.bunkerSpawned) {
                        System.out.println("bunkerSpawned false");
                        if (!event.getWorld().field_72995_K) {
                            final World world = event.getWorld();
                            final Template template = ((WorldServer)world).func_184163_y().func_186237_a(world.func_73046_m(), new ResourceLocation("apocalypseblocks", "spawnbunker"));
                            if (template == null) {
                                System.out.println("LOAD TEMPLATE FAILED!!!");
                                return;
                            }
                            System.out.println("LOAD TEMPLATE COMPLETE");
                            final BlockPos spawnTo = ApocalypseEventHandler.spawnLocation.func_177982_a(-3, -1, -12);
                            final IBlockState iblockstate = world.func_180495_p(spawnTo);
                            world.func_184138_a(spawnTo, iblockstate, iblockstate, 3);
                            template.func_186260_a(world, spawnTo, new PlacementSettings().func_186218_a((ChunkPos)null).func_186225_a((Block)null).func_186222_a(false));
                        }
                        this.worldFileCache.saveSpawnLoc(ApocalypseEventHandler.spawnLocation);
                        ApocalypseEventHandler.bunkerSpawned = true;
                    }
                }
                final BlockPos pos = ApocalypseEventHandler.spawnLocation.func_177984_a();
                player.func_180473_a(pos, true);
                player.setSpawnChunk(pos, true, 0);
                this.worldFileCache.savePlayerName(player.getDisplayNameString());
            }
        }
    }
    
    private boolean isNewPlayer(final EntityPlayer player) {
        final List loadedPlayers = this.worldFileCache.getPlayerNames();
        return loadedPlayers == null || !loadedPlayers.contains(player.getDisplayNameString());
    }
}
