package io.github.bootystar.mybatisplus.generator.config.core;

import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.IDbQuery;
import io.github.bootystar.mybatisplus.generator.config.IKeyWordsHandler;
import io.github.bootystar.mybatisplus.generator.config.ITypeConvert;
import io.github.bootystar.mybatisplus.generator.query.AbstractDatabaseQuery;
import io.github.bootystar.mybatisplus.generator.type.ITypeConvertHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author bootystar
 */
public class DataSourceConfigBuilder implements IConfigBuilder<DataSourceConfig> {

    protected final DataSourceConfig dataSourceConfig = new DataSourceConfig();

    /**
     * 构造初始化方法
     *
     * @param url      数据库连接地址
     * @param username 数据库账号
     * @param password 数据库密码
     */
    public DataSourceConfigBuilder(String url, String username, String password) {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("无法创建文件，请正确输入 url 配置信息！");
        }
        this.dataSourceConfig.url = url;
        this.dataSourceConfig.username = username;
        this.dataSourceConfig.password = password;
    }

    /**
     * 构造初始化方法
     *
     * @param dataSource 外部数据源实例
     */
    public DataSourceConfigBuilder(DataSource dataSource) {
        this.dataSourceConfig.dataSource = dataSource;
        try {
            Connection conn = dataSource.getConnection();
            this.dataSourceConfig.url = conn.getMetaData().getURL();
            try {
                this.dataSourceConfig.schemaName = conn.getSchema();
            } catch (Throwable exception) {
                //ignore  如果使用低版本的驱动，这里由于是1.7新增的方法，所以会报错，如果驱动太低，需要自行指定了。
            }
            this.dataSourceConfig.connection = conn;
            this.dataSourceConfig.username = conn.getMetaData().getUserName();
        } catch (SQLException ex) {
            throw new RuntimeException("构建数据库配置对象失败!", ex);
        }
    }

    /**
     * 构建数据库配置
     *
     * @return 数据库配置
     */
    @Override
    public DataSourceConfig build() {
        return this.dataSourceConfig;
    }
    
    /**
     * 设置数据库查询实现
     *
     * @param dbQuery 数据库查询实现
     * @return this
     */
    public DataSourceConfigBuilder dbQuery(IDbQuery dbQuery) {
        this.dataSourceConfig.dbQuery = dbQuery;
        return this;
    }

    /**
     * 设置数据库schema
     *
     * @param schemaName 数据库schema
     * @return this
     */
    public DataSourceConfigBuilder schema(String schemaName) {
        this.dataSourceConfig.schemaName = schemaName;
        return this;
    }

    /**
     * 设置类型转换器
     *
     * @param typeConvert 类型转换器
     * @return this
     */
    public DataSourceConfigBuilder typeConvert(ITypeConvert typeConvert) {
        this.dataSourceConfig.typeConvert = typeConvert;
        return this;
    }

    /**
     * 设置数据库关键字处理器
     *
     * @param keyWordsHandler 关键字处理器
     * @return this
     */
    public DataSourceConfigBuilder keyWordsHandler(IKeyWordsHandler keyWordsHandler) {
        this.dataSourceConfig.keyWordsHandler = keyWordsHandler;
        return this;
    }

    /**
     * 指定数据库查询方式
     *
     * @param databaseQueryClass 查询类
     * @return this
     * @since 3.5.3
     */
    public DataSourceConfigBuilder databaseQueryClass(Class<? extends AbstractDatabaseQuery> databaseQueryClass) {
        this.dataSourceConfig.databaseQueryClass = databaseQueryClass;
        return this;
    }

    /**
     * 指定类型转换器
     *
     * @param typeConvertHandler 类型转换器
     * @return this
     * @since 3.5.3
     */
    public DataSourceConfigBuilder typeConvertHandler(ITypeConvertHandler typeConvertHandler) {
        this.dataSourceConfig.typeConvertHandler = typeConvertHandler;
        return this;
    }

    /**
     * 增加数据库连接属性
     *
     * @param key   属性名
     * @param value 属性值
     * @return this
     * @since 3.5.3
     */
    public DataSourceConfigBuilder addConnectionProperty(String key, String value) {
        this.dataSourceConfig.connectionProperties.put(key, value);
        return this;
    }

    /**
     * 指定连接驱动
     * <li>对于一些老驱动(低于4.0规范)没有实现SPI不能自动加载的,手动指定加载让其初始化注册到驱动列表去.</li>
     *
     * @param className 驱动全类名
     * @return this
     * @since 3.5.8
     */
    public DataSourceConfigBuilder driverClassName(String className) {
        ClassUtils.toClassConfident(className);
        this.dataSourceConfig.driverClassName = className;
        return this;
    }

}
