package haskelljava;

public class Token {
    public enum Type {
        IDENT, NUMBER, LAMBDA, ARROW, LPAREN, RPAREN, EOF
    }

    public final Type type;
    public final String literal;

    public Token(Type type, String literal) {
        this.type = type;
        this.literal = literal;
    }

    public String toString() {
        return type + "(" + literal + ")";
    }
}
