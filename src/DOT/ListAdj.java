import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class ListAdj {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0)
            inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null)
            is = new FileInputStream(inputFile);
        CharStream input = CharStreams.fromStream(is);
        DotExprLexer lexer = new DotExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DotExprParser parser = new DotExprParser(tokens);
        ParseTree tree = parser.graph();
        System.out.println(tree.toStringTree(parser));

        Visitor eval = new Visitor();
        eval.visit(tree);

        Map<String, String> symbolTable = eval.getSymbolTable();
        // Ahora puedes trabajar con la tabla de símbolos
        for (Map.Entry<String, String> entry : symbolTable.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
