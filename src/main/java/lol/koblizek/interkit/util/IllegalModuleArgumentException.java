package lol.koblizek.interkit.util;

public class IllegalModuleArgumentException extends RuntimeException {

    private final int requires;
    private int got;

    public IllegalModuleArgumentException(int requires) {
        this.requires = requires;
    }

    public IllegalModuleArgumentException(int requires, int got) {
        this.requires = requires;
        this.got = got;
    }

    public int getRequiredCount() {
        return requires;
    }

    public int getGotCount() {
        return got;
    }
}
