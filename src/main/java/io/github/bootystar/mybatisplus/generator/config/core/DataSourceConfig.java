/*
 * Copyright (c) 2011-2025, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.bootystar.mybatisplus.generator.config.core;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.IDbQuery;
import io.github.bootystar.mybatisplus.generator.config.IKeyWordsHandler;
import io.github.bootystar.mybatisplus.generator.config.ITypeConvert;
import io.github.bootystar.mybatisplus.generator.config.converts.MySqlTypeConvert;
import io.github.bootystar.mybatisplus.generator.config.converts.TypeConverts;
import io.github.bootystar.mybatisplus.generator.config.querys.DbQueryDecorator;
import io.github.bootystar.mybatisplus.generator.config.querys.DbQueryRegistry;
import io.github.bootystar.mybatisplus.generator.query.AbstractDatabaseQuery;
import io.github.bootystar.mybatisplus.generator.query.DefaultQuery;
import io.github.bootystar.mybatisplus.generator.query.IDatabaseQuery;
import io.github.bootystar.mybatisplus.generator.query.SQLQuery;
import io.github.bootystar.mybatisplus.generator.type.ITypeConvertHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * 数据库配置
 *
 * @author YangHu, hcl, hubin
 * @since 2016/8/30
 */
@Slf4j
public class DataSourceConfig {


    protected DataSourceConfig() {
    }

    /**
     * 数据库信息查询
     */
    protected IDbQuery dbQuery;

    /**
     * schemaName
     */
    @Getter
    protected String schemaName;

    /**
     * 类型转换
     */
    protected ITypeConvert typeConvert;

    /**
     * 关键字处理器
     *
     * @since 3.3.2
     */
    @Getter
    protected IKeyWordsHandler keyWordsHandler;

    /**
     * 驱动连接的URL
     */
    @Getter
    protected String url;

    /**
     * 数据库连接用户名
     */
    @Getter
    protected String username;

    /**
     * 数据库连接密码
     */
    @Getter
    protected String password;

    /**
     * 数据源实例
     *
     * @since 3.5.0
     */
    protected DataSource dataSource;

    /**
     * 数据库连接
     *
     * @since 3.5.0
     */
    protected Connection connection;

    /**
     * 数据库连接属性
     *
     * @since 3.5.3
     */
    protected final Map<String, String> connectionProperties = new HashMap<>();

    /**
     * 查询方式
     *
     * @see DefaultQuery 默认查询方式，配合{@link #getTypeConvertHandler()} 使用
     * @see SQLQuery SQL语句查询方式，配合{@link #typeConvert} 使用
     * @since 3.5.3
     */
    protected Class<? extends AbstractDatabaseQuery> databaseQueryClass = DefaultQuery.class;

    /**
     * 类型转换处理
     *
     * @since 3.5.3
     */
    @Getter
    protected ITypeConvertHandler typeConvertHandler;

    /**
     * 驱动全类名
     *
     * @since 3.5.8
     */
    @Getter
    protected String driverClassName;

    /**
     * 获取数据库查询
     */
    public IDbQuery getDbQuery() {
        if (null == dbQuery) {
            DbType dbType = getDbType();
            DbQueryRegistry dbQueryRegistry = new DbQueryRegistry();
            // 默认 MYSQL
            dbQuery = Optional.ofNullable(dbQueryRegistry.getDbQuery(dbType)).orElseGet(() -> dbQueryRegistry.getDbQuery(DbType.MYSQL));
        }
        return dbQuery;
    }

    /**
     * 判断数据库类型
     *
     * @return 类型枚举值
     */
    public DbType getDbType() {
        return this.getDbType(this.url.toLowerCase());
    }

    /**
     * 判断数据库类型
     *
     * @param str url
     * @return 类型枚举值，如果没找到，则返回 null
     */
    protected DbType getDbType(String str) {
        if (str.contains(":mysql:") || str.contains(":cobar:")) {
            return DbType.MYSQL;
        } else if (str.contains(":oracle:")) {
            return DbType.ORACLE;
        } else if (str.contains(":postgresql:")) {
            return DbType.POSTGRE_SQL;
        } else if (str.contains(":sqlserver:")) {
            return DbType.SQL_SERVER;
        } else if (str.contains(":db2:")) {
            return DbType.DB2;
        } else if (str.contains(":mariadb:")) {
            return DbType.MARIADB;
        } else if (str.contains(":sqlite:")) {
            return DbType.SQLITE;
        } else if (str.contains(":h2:")) {
            return DbType.H2;
//        } else if (str.contains(":lealone:")) {
//            return DbType.LEALONE;
        } else if (str.contains(":kingbase:") || str.contains(":kingbase8:")) {
            return DbType.KINGBASE_ES;
        } else if (str.contains(":dm:")) {
            return DbType.DM;
        } else if (str.contains(":zenith:")) {
            return DbType.GAUSS;
        } else if (str.contains(":oscar:")) {
            return DbType.OSCAR;
        } else if (str.contains(":firebird:")) {
            return DbType.FIREBIRD;
        } else if (str.contains(":xugu:")) {
            return DbType.XU_GU;
        } else if (str.contains(":clickhouse:")) {
            return DbType.CLICK_HOUSE;
        } else if (str.contains(":sybase:")) {
            return DbType.SYBASE;
        } else {
            return DbType.OTHER;
        }
    }

    /**
     * 获取数据库字段类型转换
     */
    public ITypeConvert getTypeConvert() {
        if (null == typeConvert) {
            DbType dbType = getDbType();
            // 默认 MYSQL
            typeConvert = TypeConverts.getTypeConvert(dbType);
            if (null == typeConvert) {
                typeConvert = MySqlTypeConvert.INSTANCE;
            }
        }
        return typeConvert;
    }

    /**
     * 创建数据库连接对象
     * 这方法建议只调用一次，毕竟只是代码生成，用一个连接就行。
     *
     * @return Connection
     * @see DbQueryDecorator#getConnection()
     */
    public Connection getConn() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            } else {
                synchronized (this) {
                    if (dataSource != null) {
                        connection = dataSource.getConnection();
                    } else {
                        Properties properties = new Properties();
                        connectionProperties.forEach(properties::setProperty);
                        properties.put("user", username);
                        properties.put("password", password);
                        // 使用元数据查询方式时，有些数据库需要增加属性才能读取注释
                        this.processProperties(properties);
                        this.connection = DriverManager.getConnection(url, properties);
                        if (StringUtils.isBlank(this.schemaName)) {
                            try {
                                this.schemaName = connection.getSchema();
                            } catch (Exception exception) {
                                // ignore 老古董1.7以下的驱动不支持.
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    protected void processProperties(Properties properties) {
        if (this.databaseQueryClass.getName().equals(DefaultQuery.class.getName())) {
            switch (this.getDbType()) {
                case MYSQL:
                    properties.put("remarks", "true");
                    properties.put("useInformationSchema", "true");
                    break;
                case ORACLE:
                    properties.put("remarks", "true");
                    properties.put("remarksReporting", "true");
                    break;
            }
        }
    }

    /**
     * 获取数据库默认schema
     *
     * @return 默认schema
     * @since 3.5.0
     */
    protected String getDefaultSchema() {
        DbType dbType = getDbType();
        String schema = null;
        if (DbType.POSTGRE_SQL == dbType) {
            //pg 默认 schema=public
            schema = "public";
        } else if (DbType.KINGBASE_ES == dbType) {
            //kingbase 默认 schema=PUBLIC
            schema = "PUBLIC";
        } else if (DbType.DB2 == dbType) {
            //db2 默认 schema=current schema
            schema = "current schema";
        } else if (DbType.ORACLE == dbType) {
            //oracle 默认 schema=username
            schema = this.username.toUpperCase();
        }
        return schema;
    }

    public Class<? extends IDatabaseQuery> getDatabaseQueryClass() {
        return databaseQueryClass;
    }

}
