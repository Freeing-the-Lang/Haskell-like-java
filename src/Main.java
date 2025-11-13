package haskelljava;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        String code = Files.readString(Paths.get("examples/hello.hlj"));

        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        Evaluator eval = new Evaluator();

        ArrayList<Token> tokens = lexer.lex(code);
        System.out.println("[Tokens]");
        System.out.println(tokens);

        AST ast = parser.parse(tokens);
        System.out.println("\n[AST]");
        System.out.println(ast);

        AST result = eval.eval(ast);
        System.out.println("\n[Result]");
        System.out.println(result);
    }
}
