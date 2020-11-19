package cn.site.jupitermouse.antlr4.tutorial.primary.labeledexpr;

import java.util.HashMap;
import java.util.Map;

import cn.site.jupitermouse.antlr4.tutorial.primary.labeledexpr.gen.LabeledExprBaseVisitor;
import cn.site.jupitermouse.antlr4.tutorial.primary.labeledexpr.gen.LabeledExprParser;


public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
    // 计算器的内存，存放 变量-值的关系
    Map<String, Integer> memory = new HashMap<>();

    // expr NEWLINE
    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        // 字段子节点的值
        Integer value = visit(ctx.expr());
        //  打印结果
        System.out.println(value);
        return 0;
    }

    // ID '=' expr NEWLINE
    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
        // ID 在=的左侧
        String id = ctx.ID().getText();
        // 计算右侧表达式的值
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    // INT
    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    // ID
    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0;
    }

    // expr op=('*'|'/') expr
    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        // get value of left subexpression
        int left = visit(ctx.expr(0));
        // get value of right subexpression
        int right = visit(ctx.expr(1));

        if (ctx.op.getType() == LabeledExprParser.MUL) {
            return left * right;
        } else {
            // must be DIV
            return left / right;
        }
    }

    // expr op=('+'|'-') expr
    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        // get value of left subexpression
        int left = visit(ctx.expr(0));
        // get value of right subexpression
        int right = visit(ctx.expr(1));

        if (ctx.op.getType() == LabeledExprParser.ADD) {
            return left + right;
        } else {
            // must be SUB
            return left - right;
        }
    }

    //  '(' expr ')'
    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        // 返回子表达式的值
        return visit(ctx.expr());
    }

    // 'clear'
    @Override
    public Integer visitClear(LabeledExprParser.ClearContext ctx) {
        memory.clear();
        return 0;
    }

}
