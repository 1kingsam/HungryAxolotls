package net.the1kingStudio.hungryAxolotls;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HungryAxolotlsMod implements ModInitializer {
	public static final TagKey<Item> AXOLOTL_FOOD = TagKey.of(RegistryKeys.ITEM, new Identifier("hungry_axolotls", "axolotl_food"));
	public static final TagKey<Item> FISH_BUCKETS = TagKey.of(RegistryKeys.ITEM, new Identifier("hungry_axolotls", "fish_buckets"));
	public static final Logger LOGGER = LoggerFactory.getLogger("hungry_axolotls");

	@Override
	public void onInitialize() {

	}
}
