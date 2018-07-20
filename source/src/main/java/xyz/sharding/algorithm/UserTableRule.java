package xyz.sharding.algorithm;

import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.keygen.DefaultKeyGenerator;
import io.shardingjdbc.core.keygen.KeyGenerator;
import io.shardingjdbc.core.routing.strategy.ShardingStrategy;
import io.shardingjdbc.core.rule.TableRule;
import io.shardingjdbc.core.util.InlineExpressionParser;
import xyz.sharding.pojo.po.UserSharding;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 用户表分表规则
 */
public class UserTableRule extends TableRuleConfiguration {
    /**
     * 主键生成成器
     */
//    private KeyGenerator keyGenerator;

    public UserTableRule(String logicDataSource, KeyGenerator keyGenerator, String inline) {
        this.setLogicTable(UserSharding.TABLE_NAME);
        this.setActualDataNodes(String.format("%s.%s_${%s}", logicDataSource, UserSharding.TABLE_NAME, inline));
        this.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(UserSharding.SHARDING_COLUMN, UserPreciseShardingAlgorithm.class.getName()));
        this.setKeyGeneratorColumnName(UserSharding.GENERATE_COLUMN);
//        this.keyGenerator = keyGenerator;
    }
    
    /*@Override
    public TableRule build(Map<String, DataSource> dataSourceMap) {
        List<String> actualDataNodes = new InlineExpressionParser(this.getActualDataNodes()).evaluate();
        ShardingStrategy tableShardingStrategy = this.getTableShardingStrategyConfig().build();
        return new TableRule(this.getLogicTable(), actualDataNodes, dataSourceMap, null, tableShardingStrategy, this.getKeyGeneratorColumnName(), keyGenerator, this.getLogicIndex());
    }*/
}
