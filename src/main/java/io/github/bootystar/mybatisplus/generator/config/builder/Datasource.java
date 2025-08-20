package io.github.bootystar.mybatisplus.generator.config.builder;

import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.IDbQuery;
import io.github.bootystar.mybatisplus.generator.config.IKeyWordsHandler;
import io.github.bootystar.mybatisplus.generator.config.ITypeConvert;
import io.github.bootystar.mybatisplus.generator.config.core.DataSourceConfig;
import io.github.bootystar.mybatisplus.generator.query.AbstractDatabaseQuery;
import io.github.bootystar.mybatisplus.generator.type.ITypeConvertHandler;

/**
 * @author bootystar
 */
public class Datasource extends DataSourceConfig {
    public static class Builder {
        protected final Datasource config = new Datasource();

        protected Builder(String url, String username, String password) {
            if (StringUtils.isBlank(url)) {
                throw new RuntimeException("无法创建文件，请正确输入 url 配置信息！");
            }
            this.config.url = url;
            this.config.username = username;
            this.config.password = password;
        }
        
        protected DataSourceConfig build() {
            return this.config;
        }

        /**
         * 设置数据库查询实现
         *
         * @param dbQuery 数据库查询实现
         * @return this
         */
        public Builder dbQuery(IDbQuery dbQuery) {
            this.config.dbQuery = dbQuery;
            return this;
        }

        /**
         * 设置数据库schema
         *
         * @param schemaName 数据库schema
         * @return this
         */
        public Builder schema(String schemaName) {
            this.config.schemaName = schemaName;
            return this;
        }

        /**
         * 设置类型转换器
         *
         * @param typeConvert 类型转换器
         * @return this
         */
        public Builder typeConvert(ITypeConvert typeConvert) {
            this.config.typeConvert = typeConvert;
            return this;
        }

        /**
         * 设置数据库关键字处理器
         *
         * @param keyWordsHandler 关键字处理器
         * @return this
         */
        public Builder keyWordsHandler(IKeyWordsHandler keyWordsHandler) {
            this.config.keyWordsHandler = keyWordsHandler;
            return this;
        }

        /**
         * 指定数据库查询方式
         *
         * @param databaseQueryClass 查询类
         * @return this
         * @since 3.5.3
         */
        public Builder databaseQueryClass(Class<? extends AbstractDatabaseQuery> databaseQueryClass) {
            this.config.databaseQueryClass = databaseQueryClass;
            return this;
        }

        /**
         * 指定类型转换器
         *
         * @param typeConvertHandler 类型转换器
         * @return this
         * @since 3.5.3
         */
        public Builder typeConvertHandler(ITypeConvertHandler typeConvertHandler) {
            this.config.typeConvertHandler = typeConvertHandler;
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
        public Builder addConnectionProperty(String key, String value) {
            this.config.connectionProperties.put(key, value);
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
        public Builder driverClassName(String className) {
            ClassUtils.toClassConfident(className);
            this.config.driverClassName = className;
            return this;
        }

    }
}
