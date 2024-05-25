package lol.koblizek.interkit;

import lol.koblizek.interkit.commands.InterKitCommand;
import lol.koblizek.interkit.modules.Module;
import lol.koblizek.interkit.modules.PackageModule;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class InterKit extends JavaPlugin {

    @Override
    public void onEnable() {
        getSLF4JLogger().warn("================================================================");
        getSLF4JLogger().warn("InterKit can expose your server to severe security risks. TRUST NO ONE.");
        getSLF4JLogger().warn("================================================================");

        // Register modules here
        Module.register(
                new PackageModule(this)
        );

        getCommand("interkit").setExecutor(new InterKitCommand());
    }

    public void sendStackTrace(Audience audience, Throwable throwable) {
        audience.sendMessage(Component.text(throwable.getClass().getSimpleName() + ": " + throwable.getMessage())
                .color(NamedTextColor.RED));
        for (StackTraceElement element : throwable.getStackTrace()) {
            audience.sendMessage(Component.text("\t" + element.toString()).color(NamedTextColor.RED));
        }
    }
}
