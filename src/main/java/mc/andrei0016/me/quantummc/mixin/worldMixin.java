package mc.andrei0016.me.quantummc.mixin;

import net.minecraft.world.World;
import net.minecraft.world.block.NeighborUpdater;
import net.minecraft.world.block.SimpleNeighborUpdater;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public class worldMixin {
    @Mutable
    @Shadow
    @Final
    protected NeighborUpdater neighborUpdater;

    @Inject(method = "<init>(Lnet/minecraft/world/MutableWorldProperties;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/DynamicRegistryManager;Lnet/minecraft/registry/entry/RegistryEntry;Ljava/util/function/Supplier;ZZJI)V", at = @At("TAIL"))
    private void bringBackStackOverflowSuppression(CallbackInfo ci) {
        this.neighborUpdater = new SimpleNeighborUpdater((World) (Object) this);
    }
}
