package io.walker.planes.myehbadminton.repo;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SetOperationList;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Planeswalker23
 */
@Slf4j
public class QueryInterceptor extends JsqlParserSupport implements InnerInterceptor {

    private final static String DELETED = "deleted";

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        mpBs.sql(parserSingle(mpBs.sql(), null));
    }

    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        if (select instanceof PlainSelect) {
            this.setWhere((PlainSelect) select, (String) obj);
        } else if (select instanceof SetOperationList) {
            SetOperationList setOperationList = (SetOperationList) select;
            List<Select> selectBodyList = setOperationList.getSelects();
            selectBodyList.forEach(s -> this.setWhere((PlainSelect) s, (String) obj));
        }
    }

    /**
     * 设置 where 条件
     *
     * @param plainSelect  查询对象
     * @param whereSegment 查询条件片段
     */
    protected void setWhere(PlainSelect plainSelect, String whereSegment) {
        Expression originalWhere = plainSelect.getWhere();
        if (originalWhere != null && !originalWhere.toString().contains(DELETED)) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new Column(DELETED));
            equalsTo.setRightExpression(new LongValue(0));
            plainSelect.withWhere(new AndExpression(originalWhere, equalsTo));
        }
    }
}
