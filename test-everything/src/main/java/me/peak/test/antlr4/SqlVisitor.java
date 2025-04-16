package me.peak.test.antlr4;

import me.peak.test.antlr4.sql.SqlBaseParser;
import me.peak.test.antlr4.sql.SqlBaseParserBaseVisitor;

import java.util.ArrayList;
import java.util.List;

public class SqlVisitor extends SqlBaseParserBaseVisitor {

    private List<String> tableList = new ArrayList<>();

    @Override
    public List<String> visitSingleStatement(SqlBaseParser.SingleStatementContext ctx) {
        super.visitSingleStatement(ctx);
        System.out.println("table list size " + tableList.size());

        return tableList;
    }

    public List<String> visitTableAlias (SqlBaseParser.TableAliasContext ctx) {
        String db = ctx.strictIdentifier() == null ? "" : ctx.strictIdentifier().getText();
        tableList.add(db);
        return null;
    }

    @Override
    public List<String> visitTableIdentifier(SqlBaseParser.TableIdentifierContext ctx) {
        String db = ctx.db == null ? "" : ctx.db.getText();
        String tableName = ctx.table == null ? "" : ctx.table.getText();
        tableList.add("".equals(db) ? tableName : (db + "." + tableName));
        return null;
    }
}
