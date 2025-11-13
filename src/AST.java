package haskelljava;

public abstract class AST {
    public static class Var extends AST {
        public final String name;
        public Var(String name) { this.name = name; }
        public String toString() { return "Var(" + name + ")"; }
    }

    public static class Num extends AST {
        public final int value;
        public Num(int v) { this.value = v; }
        public String toString() { return "Num(" + value + ")"; }
    }

    public static class Lambda extends AST {
        public final String arg;
        public final AST body;
        public Lambda(String arg, AST body) { this.arg = arg; this.body = body; }
        public String toString() { return "Lambda(" + arg + " -> " + body + ")"; }
    }

    public static class Call extends AST {
        public final AST func;
        public final AST arg;
        public Call(AST f, AST a) { this.func = f; this.arg = a; }
        public String toString() { return "Call(" + func + ", " + arg + ")"; }
    }
}
