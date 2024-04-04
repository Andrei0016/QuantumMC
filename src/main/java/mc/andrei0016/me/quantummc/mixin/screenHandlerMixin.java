package mc.andrei0016.me.quantummc.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class screenHandlerMixin {
    @WrapWithCondition(
            method = "internalOnSlotClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;setStack(ILnet/minecraft/item/ItemStack;)V", ordinal = 1)
    )
    private boolean cancelItemShadowingPatch(PlayerInventory inventory, int button, ItemStack stack)
    {
        return false;
    }

    @Inject(method = "internalOnSlotClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;setStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 4, shift = At.Shift.AFTER))
    private void bringBackItemShadowing(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        player.getInventory().setStack(button, ItemStack.EMPTY);
    }
}
