package net.the1kingStudio.hungryAxolotls.mixin;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Hand;

import net.the1kingStudio.hungryAxolotls.HungryAxolotlsMod;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AxolotlEntity.class)
public abstract class AxolotlEntityMixin {
	@ModifyArg(method = "isBreedingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"), index = 0)
	private TagKey<Item> IsHoldingAxolotlFood(TagKey<Item> tag){
		return HungryAxolotlsMod.AXOLOTL_FOOD;
	}

	@Inject(method = "eat", at = @At("HEAD"))
	private void InjectPoison(PlayerEntity player, Hand hand, ItemStack stack, CallbackInfo ci){
		if(stack.isOf(Items.PUFFERFISH) || stack.isOf((Items.PUFFERFISH_BUCKET))) {
			AxolotlEntity axolotl = (AxolotlEntity) (Object) this;
			axolotl.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200));
		}
	}

	@Redirect(method = "eat", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
	private boolean RedirectFishBucket(ItemStack instance, Item item){
		return instance.isIn(HungryAxolotlsMod.FISH_BUCKETS);
	}
}