package cn.site.jupitermouse.antlr4.tutorial.primary.labeledexpr;

import cn.site.jupitermouse.antlr4.tutorial.primary.labeledexpr.gen.LabeledExprLexer;
import cn.site.jupitermouse.antlr4.tutorial.primary.labeledexpr.gen.LabeledExprParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * <p>
 *
 * </p>
 *
 * @author JupiterMouse 2020/11/20
 * @since 1.0
 */
public class EvalVisitorMain {
    public static String testStr = "193\n" +
            "a = 5\n" +
            "b = 6\n" +
            "a+b*2\n" +
            "(1+2)*3\n";

    public static void main(String[] args) {
        // 词法分析器新建了一个处理字符的输入流
        CodePointCharStream input = CharStreams.fromString(testStr);
        // 用输入input构造词法分析器，词法分析器的作用是产生记号
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        // 用词法分析器构造词法符号流（记号）“管道”
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 使用tokens构造语法分析器对象
        LabeledExprParser parser = new LabeledExprParser(tokens);
        // 从prog规则开始进行语法解析
        ParseTree tree = parser.prog();
        // 以文本打印解析树
        System.out.println(tree.toStringTree());

        // 使用访问器
        EvalVisitor evalVisitor = new EvalVisitor();
        evalVisitor.visit(tree);
    }
}
