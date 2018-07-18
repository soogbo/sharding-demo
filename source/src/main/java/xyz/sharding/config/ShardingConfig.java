package xyz.sharding.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.keygen.DefaultKeyGenerator;
import tk.mybatis.mapper.autoconfigure.MybatisProperties;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import xyz.sharding.algorithm.UserTableRuleConfig;
import xyz.sharding.annotations.ShardingMapper;

@Configuration
public class ShardingConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShardingConfig.class);
    
    @Bean(name = "shardingDataSource")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DataSource getShardingDataSource(@Qualifier("frameDataSource") DataSource dataSource,
                          @Qualifier("keyGeneratorFactory") KeyGeneratorFactory keyGeneratorFactory/*,
                          @Qualifier("defaultKeyGenerator") DefaultKeyGenerator defaultKeyGenerator*/
                          ) throws SQLException {
        final String logicDataSource = "ds";

        // 配置真实数据源与逻辑数据源对应关系
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        dataSourceMap.put(logicDataSource, dataSource);

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        String defaultKeyGeneratorClass = shardingRuleConfig.getDefaultKeyGeneratorClass();
        
//        shardingRuleConfig.setDefaultKeyGeneratorClass(defaultKeyGeneratorClass);
        //库名和表名支持groovy语法
//        KeyGenerator keyGenerator = keyGeneratorFactory.getKeyGenerator(DefaultKeyGenerator.class);
        
        
        shardingRuleConfig.getTableRuleConfigs().add(new UserTableRuleConfig(logicDataSource, null, "0..2"));
        shardingRuleConfig.setDefaultDataSourceName(logicDataSource);
        // 获取数据源对象
        DataSource shardingDataSource = null;
        try {
            shardingDataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap<>(), new Properties());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return shardingDataSource;
    }

    @Bean(name = "shardingSqlSessionFactory")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("shardingDataSource") DataSource dataSource,
            MybatisProperties mybatisProperties) throws IOException {
        
        // 使用mybatis配置文件中自动注入后，使用MybatisProperties，
        // 需要开启(启动类)@EnableConfigurationProperties，或者使用类上面开启@EnableConfigurationProperties({MybatisProperties.class})
        SqlSessionFactoryBean sql = new SqlSessionFactoryBean();
        sql.setDataSource(dataSource);
        sql.setConfiguration(mybatisProperties.getConfiguration());
        
        // 1.setMapperLocations：设置mapper.xml路径
        // 1.setConfigLocation：导入配置文件资源
        // springboot默认自动打开配置注入，在yml中配置参数即可
        // 可在启动类上关闭，@SpringBootApplication(exclude = {MybatisAutoConfiguration.class})

        /*ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String mapperLocations = "classpath*:com.daikuan.collection.**.mapper.*Mapper.xml";
        String configLocation = "classpath*:mybatis-config.xml";
        Resource[] resources = resolver.getResources(mapperLocations);
        sql.setMapperLocations(resources);
        Resource[] resources2 = resolver.getResources(configLocation);
        if (resources2.length > 0) {
            sql.setConfigLocation(resources2[0]);
        }*/
        return sql;
    }

    @Bean(name = "shardingMapperScannerConfigurer")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        String basePackage = "xyz.sharding.**.mapper";
        msc.setBasePackage(basePackage);
        msc.setSqlSessionFactoryBeanName("shardingSqlSessionFactory");
        msc.setAnnotationClass(ShardingMapper.class);
        return msc;
    }

}
