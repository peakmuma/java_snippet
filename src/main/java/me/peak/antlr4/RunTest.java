package me.peak.antlr4;

import me.peak.antlr4.parser.ExpressionLexer;
import me.peak.antlr4.parser.ExpressionParser;
import me.peak.antlr4.parser.HelloLexer;
import me.peak.antlr4.parser.HelloParser;
import me.peak.antlr4.sql.SqlBaseLexer;
import me.peak.antlr4.sql.SqlBaseParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.math.BigDecimal;
import java.util.List;

public class RunTest {
    public static void main(String[] args) {
//        parseTest("hello world");
//        System.out.println(calc("1+2+4*5"));
        String sql = "SELECT T1.COL1,T2.COL2 FROM TAB1 T1 LEFT JOIN TAB2 T2 ON T1.ID=T2.IxD";
        System.out.println(parseSql(sql));
    }


    static void parseTest(String param) {
        HelloLexer lexer = new HelloLexer(CharStreams.fromString(param));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokens);
        ParseTree tree = parser.r();
        System.out.println(tree.toStringTree(parser));
    }

    static BigDecimal calc(String expression) {
        CharStream cs = CharStreams.fromString(expression);
        ExpressionLexer lexer = new ExpressionLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        ExpressionParser.CalcContext context = parser.calc();
        BigDecimalCalculationVisitor visitor = new BigDecimalCalculationVisitor();
        return visitor.visit(context);
    }

    static List<String> parseSql(String sql) {
        CharStream charStream = CharStreams.fromString(sql);
        //新建一个词法分析器，解析创建的流
        SqlBaseLexer lexer = new SqlBaseLexer(charStream);
        //新建一个词法符号的缓冲器，存储词法分析器生成的词法符号
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        //新建语法解析器，处理词法符号缓冲器中的内容
        SqlBaseParser parser = new SqlBaseParser(tokenStream);
//        ParseTree tree = parser.singleStatement();
//        System.out.println(tree.toStringTree(parser));
        //指定预测模式(1)
//        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        //创建解析器访问对应的方法
        SqlVisitor visitor = new SqlVisitor();
        return (List<String>) visitor.visit(parser.singleStatement());
    }
}
