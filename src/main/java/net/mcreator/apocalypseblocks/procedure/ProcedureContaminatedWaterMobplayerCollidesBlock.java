package net.mcreator.apocalypseblocks.procedure;

import net.minecraft.util.DamageSource;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.mcreator.apocalypseblocks.ElementsApocalypseBlocks;

import java.util.Collection;

@ElementsApocalypseBlocks.ModElement.Tag
public class ProcedureContaminatedWaterMobplayerCollidesBlock extends ElementsApocalypseBlocks.ModElement {
	public ProcedureContaminatedWaterMobplayerCollidesBlock(ElementsApocalypseBlocks instance) {
		super(instance, 5);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ContaminatedWaterMobplayerCollidesBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) 1200, (int) 0));
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, (int) 600, (int) 0));
		if ((!(new Object() {
			boolean check() {
				if (entity instanceof EntityLivingBase) {
					Collection<PotionEffect> effects = ((EntityLivingBase) entity).getActivePotionEffects();
					for (PotionEffect effect : effects) {
						if (effect.getPotion() == MobEffects.POISON)
							return true;
					}
				}
				return false;
			}
		}.check()))) {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 1);
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, (int) 40, (int) 0));
		}
	}
}
