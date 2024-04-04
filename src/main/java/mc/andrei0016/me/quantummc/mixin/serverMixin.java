package mc.andrei0016.me.quantummc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.crash.CrashException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class serverMixin {
    @WrapOperation(
            method = "tickWorlds",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;tick(Ljava/util/function/BooleanSupplier;)V")
    )
    private void updateSuppressionCrashFix(ServerWorld world, BooleanSupplier shouldKeepTicking, Operation<Void> original) {
        try{
            original.call(world, shouldKeepTicking);
        }catch (ClassCastException | StackOverflowError | CrashException error) {
            error.printStackTrace();
            alertDimensionAboutCrash(world);
        }
    }
    @Unique
    private void alertDimensionAboutCrash(ServerWorld world) {
        MinecraftServer server = (MinecraftServer) (Object) this;
        server.getPlayerManager().sendToDimension(
                new GameMessageS2CPacket(Text.literal("Update Suppression crash just occurred!").formatted(Formatting.GRAY), false), world.getRegistryKey());
    }
}
