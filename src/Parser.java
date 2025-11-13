package haskelljava;

import java.util.ArrayList;

public class Parser {
    ArrayList<Token> tokens;
    int pos = 0;

    Token peek() { return tokens.get(pos); }
    Token next() { return tokens.get(pos++); }

    public AST parse(ArrayList<Token> tks) {
        this.tokens = tks;
        this.pos = 0;
        return parseExpr();
    }

    AST parseExpr() {
        Token t = peek();

        // λx -> expr
        if (t.type == Token.Type.LAMBDA) {
            next(); // '\'

            Token var = next(); // x
            next(); // '->'

            AST body = parseExpr();
            return new AST.Lambda(var.literal, body);
        }

        // 숫자
        if (t.type == Token.Type.NUMBER) {
            next();
            return new AST.Num(Integer.parseInt(t.literal));
        }

        // 변수
        if (t.type == Token.Type.IDENT) {
            next();
            return new AST.Var(t.literal);
        }

        // (expr)
        if (t.type == Token.Type.LPAREN) {
            next();
            AST inside = parseExpr();
            next(); // RPAREN
            return inside;
        }

        return null;
    }
}
