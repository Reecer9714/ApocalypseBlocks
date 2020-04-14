
package net.mcreator.apocalypseblocks.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import net.mcreator.apocalypseblocks.creativetab.TabApocalypseBlocksTab;
import net.mcreator.apocalypseblocks.ElementsApocalypseBlocks;

@ElementsApocalypseBlocks.ModElement.Tag
public class BlockBunkerSmoothFloor extends ElementsApocalypseBlocks.ModElement {
	@GameRegistry.ObjectHolder("apocalypseblocks:bunkersmoothfloor")
	public static final Block block = null;
	public BlockBunkerSmoothFloor(ElementsApocalypseBlocks instance) {
		super(instance, 16);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("bunkersmoothfloor"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("apocalypseblocks:bunkersmoothfloor", "inventory"));
	}
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.IRON);
			setUnlocalizedName("bunkersmoothfloor");
			setSoundType(SoundType.METAL);
			setHardness(50F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(TabApocalypseBlocksTab.tab);
		}
	}
}
