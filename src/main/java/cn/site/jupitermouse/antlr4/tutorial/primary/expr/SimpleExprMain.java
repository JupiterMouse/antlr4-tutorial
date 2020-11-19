package cn.site.jupitermouse.antlr4.tutorial.primary.expr;

import cn.site.jupitermouse.antlr4.tutorial.primary.expr.gen.SimpleExprBaseListener;
import cn.site.jupitermouse.antlr4.tutorial.primary.expr.gen.SimpleExprLexer;
import cn.site.jupitermouse.antlr4.tutorial.primary.expr.gen.SimpleExprParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * <p>
 *
 * </p>
 *
 * @author JupiterMouse 2020/11/20
 * @since 1.0
 */
public class SimpleExprMain {

    private static final String testStr = "193\n" +
            "a=5\n" +
            "b=6\n" +
            "a+b*2\n" +
            "(1+2)*3\n";

    public static void main(String[] args) {
        // 词法分析器新建了一个处理字符的输入流
        CodePointCharStream input = CharStreams.fromString(testStr);
        // 用输入input构造词法分析器，词法分析器的作用是产生记号
        SimpleExprLexer lexer = new SimpleExprLexer(input);
        // 用词法分析器构造词法符号流（记号）“管道”
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 使用tokens构造语法分析器对象
        SimpleExprParser parser = new SimpleExprParser(tokens);
        // 从prog规则开始进行语法解析
        ParseTree tree = parser.prog();
        // 以文本打印解析树
        System.out.println(tree.toStringTree(parser));

        // 使用监听器
        // 1. 新建 ParseTreeWalker
        ParseTreeWalker walker = new ParseTreeWalker();
        // 2. 监听器，SimpleExprBaseListener默认什么都不干
        SimpleExprBaseListener listener = new SimpleExprBaseListener();
        // 3. 加入监听器，遍历树
        walker.walk(listener, tree);
    }
}
