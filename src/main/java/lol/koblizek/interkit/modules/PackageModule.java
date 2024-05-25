package lol.koblizek.interkit.modules;

import com.destroystokyo.paper.console.TerminalConsoleCommandSender;
import lol.koblizek.interkit.InterKit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class PackageModule extends Module {

    public PackageModule(InterKit interKit) {
        super(interKit);
    }

    @Override
    public String getName() {
        return "package";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        requires(args, 1);

        for (String arg : args) {
            try (InputStream stream = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(arg.replaceAll("[.]", "/")));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                var data = Component.text("Displaying information about package ").append(Component.text(arg).decorate(TextDecoration.BOLD)).append(Component.text(":")).color(NamedTextColor.GREEN)
                        .appendNewline();
                for (String s : reader.lines().toList()) {
                    System.out.println(s);
                    data = data.append(Component.text("\t" + s).color(NamedTextColor.AQUA))
                            .appendNewline();
                }
                sender.sendMessage(data);
            } catch (NullPointerException e) {
                sender.sendMessage(Component.text("Package was not found.").color(NamedTextColor.RED));
            } catch (IOException e) {
                plugin.sendStackTrace(sender, e);
            }
        }
    }
}
