package mc.andrei0016.me.quantummc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShulkerBoxBlock.class)
public class shulkerBoxBlockMixin  {
    @WrapOperation(
            method = "getComparatorOutput",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;calculateComparatorOutput(Lnet/minecraft/block/entity/BlockEntity;)I")
    )
    private int bringBackCCESuppression(BlockEntity blockEntity, Operation<Integer> original)  {
        return ScreenHandler.calculateComparatorOutput((Inventory) blockEntity);
    }
}
