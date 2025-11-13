package haskelljava;

public class Evaluator {

    public AST eval(AST expr) {
        if (expr instanceof AST.Num n) return n;
        if (expr instanceof AST.Var v) return v;

        if (expr instanceof AST.Lambda lam)
            return lam;

        if (expr instanceof AST.Call c) {
            AST func = eval(c.func);
            AST arg  = eval(c.arg);

            if (func instanceof AST.Lambda lam) {
                return eval(substitute(lam.body, lam.arg, arg));
            }
            return new AST.Call(func, arg);
        }

        return expr;
    }

    AST substitute(AST expr, String name, AST value) {

        if (expr instanceof AST.Var v) {
            if (v.name.equals(name)) return value;
            return v;
        }

        if (expr instanceof AST.Num n) return n;

        if (expr instanceof AST.Lambda lam) {
            if (lam.arg.equals(name))
                return lam;
            return new AST.Lambda(lam.arg, substitute(lam.body, name, value));
        }

        if (expr instanceof AST.Call c) {
            return new AST.Call(
                substitute(c.func, name, value),
                substitute(c.arg, name, value)
            );
        }

        return expr;
    }
}
