package io.github.bootystar.mybatisplus.generator.config.builder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.INameConvert;
import io.github.bootystar.mybatisplus.generator.config.core.EntityConfig;
import io.github.bootystar.mybatisplus.generator.config.rules.NamingStrategy;
import io.github.bootystar.mybatisplus.generator.fill.IFill;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author bootystar
 */
@Slf4j
public class Entity extends EntityConfig {
    public static class Builder {
        protected final Entity config = new Entity();

        protected Builder() {
        }

        protected EntityConfig build() {
            return this.config;
        }

        /**
         * 名称转换实现
         *
         * @param nameConvert 名称转换实现
         * @return this
         */
        public Builder nameConvert(INameConvert nameConvert) {
            this.config.nameConvert = nameConvert;
            return this;
        }

        /**
         * 自定义继承的Entity类全称
         *
         * @param clazz 类
         * @return this
         */
        public Builder superClass(Class<?> clazz) {
            return superClass(clazz.getName());
        }

        /**
         * 自定义继承的Entity类全称，带包名
         *
         * @param superEntityClass 类全称
         * @return this
         */
        public Builder superClass(String superEntityClass) {
            this.config.superClass = superEntityClass;
            if (StringUtils.isNotBlank(superEntityClass)) {
                try {
                    Optional.of(ClassUtils.toClassConfident(superEntityClass))
                            .ifPresent(this.config::convertSuperEntityColumns);
                } catch (Exception e) {
                    //当父类实体存在类加载器的时候,识别父类实体字段，不存在的情况就只有通过指定superEntityColumns属性了。
                }
            } else {
                if (!this.config.superEntityColumns.isEmpty()) {
                    log.warn("Forgot to set entity supper class ?");
                }
            }
            return this;
        }

        /**
         * 设置乐观锁数据库表字段名称
         *
         * @param versionColumnName 乐观锁数据库字段名称
         * @return this
         */
        public Builder versionColumnName(String versionColumnName) {
            this.config.versionColumnName = versionColumnName;
            return this;
        }

        /**
         * 设置乐观锁实体属性字段名称
         *
         * @param versionPropertyName 乐观锁实体属性字段名称
         * @return this
         */
        public Builder versionPropertyName(String versionPropertyName) {
            this.config.versionPropertyName = versionPropertyName;
            return this;
        }

        /**
         * 逻辑删除数据库字段名称
         *
         * @param logicDeleteColumnName 逻辑删除字段名称
         * @return this
         */
        public Builder logicDeleteColumnName(String logicDeleteColumnName) {
            this.config.logicDeleteColumnName = logicDeleteColumnName;
            return this;
        }

        /**
         * 逻辑删除实体属性名称
         *
         * @param logicDeletePropertyName 逻辑删除实体属性名称
         * @return this
         */
        public Builder logicDeletePropertyName(String logicDeletePropertyName) {
            this.config.logicDeletePropertyName = logicDeletePropertyName;
            return this;
        }

        /**
         * 数据库表映射到实体的命名策略
         *
         * @param namingStrategy 数据库表映射到实体的命名策略
         * @return this
         */
        public Builder naming(NamingStrategy namingStrategy) {
            this.config.naming = namingStrategy;
            return this;
        }

        /**
         * 数据库表字段映射到实体的命名策略
         *
         * @param namingStrategy 数据库表字段映射到实体的命名策略
         * @return this
         */
        public Builder columnNaming(NamingStrategy namingStrategy) {
            this.config.columnNaming = namingStrategy;
            return this;
        }

        /**
         * 添加父类公共字段
         *
         * @param superEntityColumns 父类字段(数据库字段列名)
         * @return this
         * @since 3.5.0
         */
        public Builder addSuperEntityColumns(String... superEntityColumns) {
            return addSuperEntityColumns(Arrays.asList(superEntityColumns));
        }

        public Builder addSuperEntityColumns(List<String> superEntityColumnList) {
            this.config.superEntityColumns.addAll(superEntityColumnList);
            return this;
        }

        /**
         * 添加忽略字段
         *
         * @param ignoreColumns 需要忽略的字段(数据库字段列名)
         * @return this
         * @since 3.5.0
         */
        public Builder addIgnoreColumns(String... ignoreColumns) {
            return addIgnoreColumns(Arrays.asList(ignoreColumns));
        }

        public Builder addIgnoreColumns(List<String> ignoreColumnList) {
            this.config.ignoreColumns.addAll(ignoreColumnList);
            return this;
        }

        /**
         * 添加表字段填充
         *
         * @param tableFills 填充字段
         * @return this
         * @since 3.5.0
         */
        public Builder addTableFills(IFill... tableFills) {
            return addTableFills(Arrays.asList(tableFills));
        }

        /**
         * 添加表字段填充
         *
         * @param tableFillList 填充字段集合
         * @return this
         * @since 3.5.0
         */
        public Builder addTableFills(List<IFill> tableFillList) {
            this.config.tableFillList.addAll(tableFillList);
            return this;
        }

        /**
         * 指定生成的主键的ID类型
         *
         * @param idType ID类型
         * @return this
         * @since 3.5.0
         */
        public Builder idType(IdType idType) {
            this.config.idType = idType;
            return this;
        }

        /**
         * 格式化文件名称
         *
         * @param format 　格式
         * @return this
         * @since 3.5.0
         */
        public Builder formatFileName(String format) {
            this.config.converterFileName = entityName -> String.format(format, entityName);
            return this;
        }

        /**
         * 覆盖已有文件
         *
         * @since 3.5.3
         */
        public Builder enableFileOverride() {
            this.config.fileOverride = true;
            return this;
        }

        /**
         * 启用生成 {@link java.io.Serial} (需JAVA 14)
         * <p>当开启了 {@link #serialVersionUID} 时,会增加 {@link java.io.Serial} 注解在此字段上</p>
         *
         * @return this
         * @since 3.5.11
         */
        public Builder enableSerialAnnotation() {
            this.config.serialAnnotation = true;
            return this;
        }

        /**
         * 开启生成字段常量
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableColumnConstant() {
            this.config.columnConstant = true;
            return this;
        }

        /**
         * 开启Boolean类型字段移除is前缀
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableRemoveIsPrefix() {
            this.config.booleanColumnRemoveIsPrefix = true;
            return this;
        }

        /**
         * 开启生成实体时生成字段注解
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableTableFieldAnnotation() {
            this.config.tableFieldAnnotationEnable = true;
            return this;
        }

        /**
         * 开启 ActiveRecord 模式
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableActiveRecord() {
            this.config.activeRecord = true;
            return this;
        }

        /**
         * 禁用生成serialVersionUID
         *
         * @return this
         * @since 3.5.0
         */
        public Builder disableSerialVersionUID() {
            this.config.serialVersionUID = false;
            return this;
        }

        /**
         * 禁用实体生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disable() {
            this.config.generate = false;
            return this;
        }

    }
}
