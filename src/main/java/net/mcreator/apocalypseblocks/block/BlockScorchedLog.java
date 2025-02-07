
package net.mcreator.apocalypseblocks.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import net.mcreator.apocalypseblocks.ElementsApocalypseBlocks;

@ElementsApocalypseBlocks.ModElement.Tag
public class BlockScorchedLog extends ElementsApocalypseBlocks.ModElement {
	@GameRegistry.ObjectHolder("apocalypseblocks:scorchedlog")
	public static final Block block = null;
	public BlockScorchedLog(ElementsApocalypseBlocks instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("scorchedlog"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("apocalypseblocks:scorchedlog", "inventory"));
	}
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.WOOD);
			setUnlocalizedName("scorchedlog");
			setSoundType(SoundType.WOOD);
			setHardness(2F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(new ItemStack(Items.COAL, (int) (1), 1));
		}
	}
}
