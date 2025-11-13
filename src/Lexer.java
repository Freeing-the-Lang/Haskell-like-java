package haskelljava;

import java.util.ArrayList;

public class Lexer {
    public ArrayList<Token> lex(String src) {
        ArrayList<Token> list = new ArrayList<>();
        int i = 0;

        while (i < src.length()) {
            char c = src.charAt(i);

            if (Character.isWhitespace(c)) {
                i++; continue;
            }

            if (c == '\\') {
                list.add(new Token(Token.Type.LAMBDA, "\\"));
                i++; continue;
            }

            if (c == '(') { list.add(new Token(Token.Type.LPAREN, "(")); i++; continue; }
            if (c == ')') { list.add(new Token(Token.Type.RPAREN, ")")); i++; continue; }

            if (c == '-' && i + 1 < src.length() && src.charAt(i+1) == '>') {
                list.add(new Token(Token.Type.ARROW, "->"));
                i += 2; continue;
            }

            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < src.length() && Character.isDigit(src.charAt(i))) {
                    sb.append(src.charAt(i));
                    i++;
                }
                list.add(new Token(Token.Type.NUMBER, sb.toString()));
                continue;
            }

            if (Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < src.length() && Character.isLetter(src.charAt(i))) {
                    sb.append(src.charAt(i));
                    i++;
                }
                list.add(new Token(Token.Type.IDENT, sb.toString()));
                continue;
            }

            i++;
        }

        list.add(new Token(Token.Type.EOF, ""));
        return list;
    }
}
