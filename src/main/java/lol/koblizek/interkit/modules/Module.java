package lol.koblizek.interkit.modules;

import lol.koblizek.interkit.InterKit;
import lol.koblizek.interkit.util.IllegalModuleArgumentException;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Module {

    private static final List<Module> modules = new ArrayList<>();

    protected final InterKit plugin;

    public Module(InterKit interKit) {
        this.plugin = interKit;
    }

    /**
     * @return The name of the module
     */
    public abstract String getName();

    /**
     * @param sender The module invoker
     * @param args  The arguments passed to the module
     */
    public abstract void execute(CommandSender sender, String[] args);

    /**
     * Registers passed modules
     * @param modules The modules to register
     */
    public static void register(Module... modules) {
        Collections.addAll(Module.modules, modules);
    }

    public static List<Module> getModules() {
        return Collections.unmodifiableList(modules);
    }

    protected final void requires(String[] args, int req) throws IllegalModuleArgumentException {
        if (args.length < req) {
            throw new IllegalModuleArgumentException(req);
        }
    }
}
