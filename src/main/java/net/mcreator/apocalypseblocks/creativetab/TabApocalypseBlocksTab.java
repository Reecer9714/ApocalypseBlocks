
package net.mcreator.apocalypseblocks.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.mcreator.apocalypseblocks.block.BlockBunkerExpansion;
import net.mcreator.apocalypseblocks.ElementsApocalypseBlocks;

@ElementsApocalypseBlocks.ModElement.Tag
public class TabApocalypseBlocksTab extends ElementsApocalypseBlocks.ModElement {
	public TabApocalypseBlocksTab(ElementsApocalypseBlocks instance) {
		super(instance, 11);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabapocalypseblockstab") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(BlockBunkerExpansion.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static CreativeTabs tab;
}
