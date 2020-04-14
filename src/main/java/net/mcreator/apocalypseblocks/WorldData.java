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

import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.CompressedStreamTools;

import java.util.List;
import java.util.ArrayList;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;

public class WorldData {
	private File file;
	public WorldData(final String filename) {
		this.file = new File(filename);
	}

	public WorldData() {
		this.file = null;
	}

	public void setFile(final String filename) {
		this.file = new File(filename);
	}

	public boolean checkIfExists() {
		return this.file.exists();
	}

	public void createFile() {
		final NBTTagCompound playerTags = new NBTTagCompound();
		final NBTTagCompound spawnTag = new NBTTagCompound();
		final NBTTagCompound wastelandData = new NBTTagCompound();
		wastelandData.func_74782_a("PlayerTags", (NBTBase) playerTags);
		spawnTag.func_74757_a("isSet", false);
		wastelandData.func_74782_a("spawnTag", (NBTBase) spawnTag);
		try {
			this.file.createNewFile();
			final FileOutputStream e = new FileOutputStream(this.file);
			CompressedStreamTools.func_74799_a(wastelandData, (OutputStream) e);
		} catch (IOException var5) {
			var5.printStackTrace();
		}
	}

	public List getPlayerNames() {
		final ArrayList names = new ArrayList();
		try {
			final FileInputStream e = new FileInputStream(this.file);
			final NBTTagCompound wastelandData = CompressedStreamTools.func_74796_a((InputStream) e);
			names.addAll(wastelandData.func_74775_l("PlayerTags").func_150296_c());
		} catch (Exception var4) {
			var4.printStackTrace();
		}
		return names.isEmpty() ? null : names;
	}

	public void savePlayerNames(final List names) {
		try {
			final FileInputStream e = new FileInputStream(this.file);
			final NBTTagCompound wastelandData = CompressedStreamTools.func_74796_a((InputStream) e);
			final NBTTagCompound playerTags = wastelandData.func_74775_l("PlayerTags");
			for (int fileStreamOut = 0; fileStreamOut < names.size(); ++fileStreamOut) {
				playerTags.func_74778_a((String) names.get(fileStreamOut), "NA");
			}
			final FileOutputStream var7 = new FileOutputStream(this.file);
			CompressedStreamTools.func_74799_a(wastelandData, (OutputStream) var7);
		} catch (Exception var8) {
			var8.printStackTrace();
		}
	}

	public void savePlayerName(final String name) {
		final ArrayList names = new ArrayList();
		names.add(name);
		this.savePlayerNames(names);
	}

	public void saveSpawnLoc(final BlockPos spawn) {
		try {
			final FileInputStream e = new FileInputStream(this.file);
			final NBTTagCompound wastelandData = CompressedStreamTools.func_74796_a((InputStream) e);
			final NBTTagCompound spawnTag = wastelandData.func_74775_l("spawnTag");
			spawnTag.func_74768_a("spawnX", spawn.func_177958_n());
			spawnTag.func_74768_a("spawnY", spawn.func_177956_o());
			spawnTag.func_74768_a("spawnZ", spawn.func_177952_p());
			spawnTag.func_74757_a("isSet", true);
			final FileOutputStream fileStreamOut = new FileOutputStream(this.file);
			CompressedStreamTools.func_74799_a(wastelandData, (OutputStream) fileStreamOut);
		} catch (Exception var6) {
			var6.printStackTrace();
		}
	}

	public BlockPos loadSpawnLoc() {
		BlockPos spawn = null;
		try {
			final FileInputStream e = new FileInputStream(this.file);
			final NBTTagCompound wastelandData = CompressedStreamTools.func_74796_a((InputStream) e);
			final NBTTagCompound spawnTag = wastelandData.func_74775_l("spawnTag");
			spawn = new BlockPos(spawnTag.func_74762_e("spawnX"), spawnTag.func_74762_e("spawnY"), spawnTag.func_74762_e("spawnZ"));
		} catch (Exception var5) {
			var5.printStackTrace();
		}
		return spawn;
	}

	public boolean isSpawnSet() {
		try {
			final FileInputStream e = new FileInputStream(this.file);
			final NBTTagCompound wastelandData = CompressedStreamTools.func_74796_a((InputStream) e);
			final NBTTagCompound spawnTag = wastelandData.func_74775_l("spawnTag");
			return spawnTag.func_74767_n("isSet");
		} catch (Exception var5) {
			var5.printStackTrace();
			return false;
		}
	}
}
