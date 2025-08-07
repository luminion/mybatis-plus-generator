package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.baomidou.mybatisplus.generator.util.ClassUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * 实体配置构建器
 * 
 * @see com.baomidou.mybatisplus.generator.config.builder.Entity.Builder
 * @author bootystar
 */
@Slf4j
public class EntityBuilder implements IConfigBuilder<Entity> {
    
    private final Entity entity = new Entity();
    
    @Override
    public Entity build() {
        return entity;
    }

    /**
     * 名称转换实现
     *
     * @param nameConvert 名称转换实现
     * @return this
     */
    public EntityBuilder nameConvert(INameConvert nameConvert) {
        this.entity.nameConvert = nameConvert;
        return this;
    }

    /**
     * 自定义继承的Entity类全称
     *
     * @param clazz 类
     * @return this
     */
    public EntityBuilder superClass(Class<?> clazz) {
        return superClass(clazz.getName());
    }

    /**
     * 自定义继承的Entity类全称，带包名
     *
     * @param superEntityClass 类全称
     * @return this
     */
    public EntityBuilder superClass(String superEntityClass) {
        this.entity.superClass = superEntityClass;
        return this;
    }

    /**
     * 禁用生成serialVersionUID
     *
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder disableSerialVersionUID() {
        this.entity.serialVersionUID = false;
        return this;
    }

    /**
     * 启用生成 {@link java.io.Serial} (需JAVA 14)
     * <p>当开启了 {@link Entity#serialVersionUID} 时,会增加 {@link java.io.Serial} 注解在此字段上</p>
     *
     * @return this
     * @since 3.5.11
     */
    public EntityBuilder enableSerialAnnotation() {
        this.entity.serialAnnotation = true;
        return this;
    }

    /**
     * 开启生成字段常量
     *
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder enableColumnConstant() {
        this.entity.columnConstant = true;
        return this;
    }

    /**
     * 开启链式模型
     *
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder enableChainModel() {
        this.entity.chain = true;
        return this;
    }

    /**
     * 关闭lombok模型 (默认添加Getter和Setter)
     * 
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder disableLombok() {
        this.entity.lombok = true;
        return this;
    }

    /**
     * 开启Boolean类型字段移除is前缀
     *
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder enableRemoveIsPrefix() {
        this.entity.booleanColumnRemoveIsPrefix = true;
        return this;
    }

    /**
     * 开启生成实体时生成字段注解
     *
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder enableTableFieldAnnotation() {
        this.entity.tableFieldAnnotationEnable = true;
        return this;
    }

    /**
     * 开启 ActiveRecord 模式
     *
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder enableActiveRecord() {
        this.entity.activeRecord = true;
        return this;
    }

    /**
     * 设置乐观锁数据库表字段名称
     *
     * @param versionColumnName 乐观锁数据库字段名称
     * @return this
     */
    public EntityBuilder versionColumnName(String versionColumnName) {
        this.entity.versionColumnName = versionColumnName;
        return this;
    }

    /**
     * 设置乐观锁实体属性字段名称
     *
     * @param versionPropertyName 乐观锁实体属性字段名称
     * @return this
     */
    public EntityBuilder versionPropertyName(String versionPropertyName) {
        this.entity.versionPropertyName = versionPropertyName;
        return this;
    }

    /**
     * 逻辑删除数据库字段名称
     *
     * @param logicDeleteColumnName 逻辑删除字段名称
     * @return this
     */
    public EntityBuilder logicDeleteColumnName(String logicDeleteColumnName) {
        this.entity.logicDeleteColumnName = logicDeleteColumnName;
        return this;
    }

    /**
     * 逻辑删除实体属性名称
     *
     * @param logicDeletePropertyName 逻辑删除实体属性名称
     * @return this
     */
    public EntityBuilder logicDeletePropertyName(String logicDeletePropertyName) {
        this.entity.logicDeletePropertyName = logicDeletePropertyName;
        return this;
    }

    /**
     * 数据库表映射到实体的命名策略
     *
     * @param namingStrategy 数据库表映射到实体的命名策略
     * @return this
     */
    public EntityBuilder naming(NamingStrategy namingStrategy) {
        this.entity.naming = namingStrategy;
        return this;
    }

    /**
     * 数据库表字段映射到实体的命名策略
     *
     * @param namingStrategy 数据库表字段映射到实体的命名策略
     * @return this
     */
    public EntityBuilder columnNaming(NamingStrategy namingStrategy) {
        this.entity.columnNaming = namingStrategy;
        return this;
    }

    /**
     * 添加父类公共字段
     *
     * @param superEntityColumns 父类字段(数据库字段列名)
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder addSuperEntityColumns(String... superEntityColumns) {
        return addSuperEntityColumns(Arrays.asList(superEntityColumns));
    }

    public EntityBuilder addSuperEntityColumns(List<String> superEntityColumnList) {
        this.entity.superEntityColumns.addAll(superEntityColumnList);
        return this;
    }

    /**
     * 添加忽略字段
     *
     * @param ignoreColumns 需要忽略的字段(数据库字段列名)
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder addIgnoreColumns(String... ignoreColumns) {
        return addIgnoreColumns(Arrays.asList(ignoreColumns));
    }

    public EntityBuilder addIgnoreColumns(List<String> ignoreColumnList) {
        this.entity.ignoreColumns.addAll(ignoreColumnList);
        return this;
    }

    /**
     * 添加表字段填充
     *
     * @param tableFills 填充字段
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder addTableFills(IFill... tableFills) {
        return addTableFills(Arrays.asList(tableFills));
    }

    /**
     * 添加表字段填充
     *
     * @param tableFillList 填充字段集合
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder addTableFills(List<IFill> tableFillList) {
        this.entity.tableFillList.addAll(tableFillList);
        return this;
    }

    /**
     * 指定生成的主键的ID类型
     *
     * @param idType ID类型
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder idType(IdType idType) {
        this.entity.idType = idType;
        return this;
    }

    /**
     * 转换输出文件名称
     *
     * @param converter 　转换处理
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder convertFileName(ConverterFileName converter) {
        this.entity.converterFileName = converter;
        return this;
    }

    /**
     * 格式化文件名称
     *
     * @param format 　格式
     * @return this
     * @since 3.5.0
     */
    public EntityBuilder formatFileName(String format) {
        return convertFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 覆盖已有文件
     *
     * @since 3.5.3
     */
    public EntityBuilder enableFileOverride() {
        this.entity.fileOverride = true;
        return this;
    }

    /**
     * 指定模板路径
     *
     * @param template 模板路径
     * @return this
     * @since 3.5.6
     */
    public EntityBuilder javaTemplate(String template) {
        this.entity.javaTemplate = template;
        return this;
    }

    /**
     * 指定模板路径
     *
     * @param template 模板路径
     * @return this
     * @since 3.5.6
     */
    public EntityBuilder kotlinTemplatePath(String template) {
        this.entity.kotlinTemplate = template;
        return this;
    }

    /**
     * 禁用实体生成
     *
     * @return this
     * @since 3.5.6
     */
    public EntityBuilder disable() {
        this.entity.generate = false;
        return this;
    }

    /**
     * 设置字段是否生成文档注释
     *
     * @param fieldUseJavaDoc 是否生成文档注释
     * @return this
     * @since 3.5.10
     */
    public EntityBuilder fieldUseJavaDoc(boolean fieldUseJavaDoc) {
        this.entity.fieldUseJavaDoc = fieldUseJavaDoc;
        return this;
    }

    /**
     * 导包处理方法
     *
     * @param importPackageFunction 导包处理
     * @return this
     * @since 3.5.11
     */
    public EntityBuilder importPackageFunction(Function<Set<String>, List<String>> importPackageFunction) {
        this.entity.importPackageFunction = importPackageFunction;
        return this;
    }

    private Optional<Class<?>> tryLoadClass(String className) {
        try {
            return Optional.of(ClassUtils.toClassConfident(className));
        } catch (Exception e) {
            //当父类实体存在类加载器的时候,识别父类实体字段，不存在的情况就只有通过指定superEntityColumns属性了。
        }
        return Optional.empty();
    }
    
    
}
