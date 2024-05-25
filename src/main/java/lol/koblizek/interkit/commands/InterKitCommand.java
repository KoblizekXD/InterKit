package lol.koblizek.interkit.commands;

import lol.koblizek.interkit.modules.Module;
import lol.koblizek.interkit.util.IllegalModuleArgumentException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class InterKitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            args = new String[]{"help"};
        }
        String module = args[0];
        String[] moduleArgs = new String[args.length - 1];
        System.arraycopy(args, 1, moduleArgs, 0, args.length - 1);

        var optionalModule = Module.getModules().stream().filter(m -> m.getName().equals(module)).findFirst();

        if (optionalModule.isEmpty()) {
            sender.sendMessage(Component.text("Module " + module + " not found.").color(NamedTextColor.RED));
            return false;
        }

        Module m = optionalModule.get();

        try {
            m.execute(sender, moduleArgs);
        } catch (IllegalModuleArgumentException e) {
            sender.sendMessage(Component.text("Modules requires at least " + e.getRequiredCount() + " arguments.").color(NamedTextColor.RED));
            return false;
        }

        return true;
    }
}
