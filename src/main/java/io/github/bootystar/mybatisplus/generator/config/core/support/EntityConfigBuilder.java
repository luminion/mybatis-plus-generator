package io.github.bootystar.mybatisplus.generator.config.core.support;

import com.baomidou.mybatisplus.annotation.IdType;
import io.github.bootystar.mybatisplus.generator.IFill;
import io.github.bootystar.mybatisplus.generator.ITableAnnotationHandler;
import io.github.bootystar.mybatisplus.generator.ITableFieldAnnotationHandler;
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.INameConvert;
import io.github.bootystar.mybatisplus.generator.config.core.GlobalConfig;
import io.github.bootystar.mybatisplus.generator.config.rules.NamingStrategy;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import io.github.bootystar.mybatisplus.generator.model.AnnotationAttributes;
import io.github.bootystar.mybatisplus.generator.model.ClassAnnotationAttributes;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * @author bootystar
 */
public class EntityConfigBuilder implements IConfigBuilder<EntityConfig> {
    protected final EntityConfig entity = new EntityConfig();


    @Override
    public EntityConfig build() {
        return entity;
    }

    /**
     * 名称转换实现
     *
     * @param nameConvert 名称转换实现
     * @return this
     */
    public EntityConfigBuilder nameConvert(INameConvert nameConvert) {
        this.entity.nameConvert = nameConvert;
        return this;
    }

    /**
     * 自定义继承的Entity类全称
     *
     * @param clazz 类
     * @return this
     */
    public EntityConfigBuilder superClass(Class<?> clazz) {
        return superClass(clazz.getName());
    }

    /**
     * 自定义继承的Entity类全称，带包名
     *
     * @param superEntityClass 类全称
     * @return this
     */
    public EntityConfigBuilder superClass(String superEntityClass) {
        this.entity.superClass = superEntityClass;
        return this;
    }

    /**
     * 禁用生成serialVersionUID
     *
     * @return this
     * @since 3.5.0
     */
    public EntityConfigBuilder disableSerialVersionUID() {
        this.entity.serialVersionUID = false;
        return this;
    }

    /**
     * 启用生成 {@link java.io.Serial} (需JAVA 14)
     * <p>当开启了 {@link #serialVersionUID} 时,会增加 {@link java.io.Serial} 注解在此字段上</p>
     *
     * @return this
     * @since 3.5.11
     */
    public EntityConfigBuilder enableSerialAnnotation() {
        this.entity.serialAnnotation = true;
        return this;
    }

    /**
     * 开启生成字段常量
     *
     * @return this
     * @since 3.5.0
     */
    public EntityConfigBuilder enableColumnConstant() {
        this.entity.columnConstant = true;
        return this;
    }

    /**
     * 开启链式模型
     *
     * @return this
     * @since 3.5.0
     */
    public EntityConfigBuilder enableChainModel() {
        this.entity.chain = true;
        return this;
    }

    /**
     * 开启lombok模型 (默认添加Getter和Setter)
     * <p>自3.5.10开始,默认添加ToString搭配,如果想关闭可通过{@link #toString(boolean)}关闭</p>
     *
     * @return this
     * @since 3.5.0
     */
    public EntityConfigBuilder enableLombok() {
        this.entity.lombok = true;
        return this;
    }

    /**
     * 开启lombok模型 (会把注解属性都加入进去,无论是否启用{@link GlobalConfig#isKotlin()})
     * <p>注意如果通过此方法开启lombok模型,默认的lombok注解(get,set,toString)都将不会生成,请自行控制添加</p>
     * <p>由{@link #toString(boolean)}控制的也会失效</p>
     * 使用@Data示例: enableLombok(new ClassAnnotationAttributes("@Data","lombok.Data"))
     *
     * @param attributes 注解属性集合
     * @return this
     * @since 3.5.10
     */
    public EntityConfigBuilder enableLombok(ClassAnnotationAttributes... attributes) {
        this.entity.lombok = true;
        this.entity.defaultLombok = false;
        for (ClassAnnotationAttributes attribute : attributes) {
            this.addClassAnnotation(attribute);
        }
        return this;
    }

    /**
     * 开启Boolean类型字段移除is前缀
     *
     * @return this
     * @since 3.5.0
     */
    public EntityConfigBuilder enableRemoveIsPrefix() {
        this.entity.booleanColumnRemoveIsPrefix = true;
        return this;
    }

    /**
     * 开启生成实体时生成字段注解
     *
     * @return this
     * @since 3.5.0
     */
    public EntityConfigBuilder enableTableFieldAnnotation() {
        this.entity.tableFieldAnnotationEnable = true;
        return this;
    }

    /**
     * 开启 ActiveRecord 模式
     *
     * @return this
     * @since 3.5.0
     */
    public EntityConfigBuilder enableActiveRecord() {
        this.entity.activeRecord = true;
        return this;
    }

    /**
     * 设置乐观锁数据库表字段名称
     *
     * @param versionColumnName 乐观锁数据库字段名称
     * @return this
     */
    public EntityConfigBuilder versionColumnName(String versionColumnName) {
        this.entity.versionColumnName = versionColumnName;
        return this;
    }

    /**
     * 设置乐观锁实体属性字段名称
     *
     * @param versionPropertyName 乐观锁实体属性字段名称
     * @return this
     */
    public EntityConfigBuilder versionPropertyName(String versionPropertyName) {
        this.entity.versionPropertyName = versionPropertyName;
        return this;
    }

    /**
     * 逻辑删除数据库字段名称
     *
     * @param logicDeleteColumnName 逻辑删除字段名称
     * @return this
     */
    public EntityConfigBuilder logicDeleteColumnName(String logicDeleteColumnName) {
        this.entity.logicDeleteColumnName = logicDeleteColumnName;
        return this;
    }

    /**
     * 逻辑删除实体属性名称
     *
     * @param logicDeletePropertyName 逻辑删除实体属性名称
     * @return this
     */
    public EntityConfigBuilder logicDeletePropertyName(String logicDeletePropertyName) {
        this.entity.logicDeletePropertyName = logicDeletePropertyName;
        return this;
    }

    /**
     * 数据库表映射到实体的命名策略
     *
     * @param namingStrategy 数据库表映射到实体的命名策略
     * @return this
     */
    public EntityConfigBuilder naming(NamingStrategy namingStrategy) {
        this.entity.naming = namingStrategy;
        return this;
    }

    /**
     * 数据库表字段映射到实体的命名策略
     *
     * @param namingStrategy 数据库表字段映射到实体的命名策略
     * @return this
     */
    public EntityConfigBuilder columnNaming(NamingStrategy namingStrategy) {
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
    public EntityConfigBuilder addSuperEntityColumns(String... superEntityColumns) {
        return addSuperEntityColumns(Arrays.asList(superEntityColumns));
    }

    public EntityConfigBuilder addSuperEntityColumns(List<String> superEntityColumnList) {
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
    public EntityConfigBuilder addIgnoreColumns(String... ignoreColumns) {
        return addIgnoreColumns(Arrays.asList(ignoreColumns));
    }

    public EntityConfigBuilder addIgnoreColumns(List<String> ignoreColumnList) {
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
    public EntityConfigBuilder addTableFills(IFill... tableFills) {
        return addTableFills(Arrays.asList(tableFills));
    }

    /**
     * 添加表字段填充
     *
     * @param tableFillList 填充字段集合
     * @return this
     * @since 3.5.0
     */
    public EntityConfigBuilder addTableFills(List<IFill> tableFillList) {
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
    public EntityConfigBuilder idType(IdType idType) {
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
    public EntityConfigBuilder convertFileName(ConverterFileName converter) {
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
    public EntityConfigBuilder formatFileName(String format) {
        return convertFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 覆盖已有文件
     *
     * @since 3.5.3
     */
    public EntityConfigBuilder enableFileOverride() {
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
    public EntityConfigBuilder javaTemplate(String template) {
        this.entity.javaTemplate = template;
        return this;
    }

    /**
     * 禁用实体生成
     *
     * @return this
     * @since 3.5.6
     */
    public EntityConfigBuilder disable() {
        this.entity.generate = false;
        return this;
    }

    /**
     * 添加类注解
     *
     * @param attributes 注解属性
     * @return this
     * @since 3.5.10
     */
    public EntityConfigBuilder addClassAnnotation(ClassAnnotationAttributes attributes) {
        this.entity.classAnnotations.add(attributes);
        return this;
    }

    /**
     * 指定字段注解处理器
     *
     * @param tableFieldAnnotationHandler 字段处理器
     * @return this
     * @since 3.5.10
     */
    public EntityConfigBuilder tableFieldAnnotationHandler(ITableFieldAnnotationHandler tableFieldAnnotationHandler) {
        this.entity.tableFieldAnnotationHandler = tableFieldAnnotationHandler;
        return this;
    }

    /**
     * 指定表注解处理器
     * @param tableAnnotationHandler 表注解处理器
     * @since 3.5.10
     * @return this
     */
    public EntityConfigBuilder tableAnnotationHandler(ITableAnnotationHandler tableAnnotationHandler){
        this.entity.tableAnnotationHandler = tableAnnotationHandler;
        return this;
    }

    /**
     * 设置是否生成ToString方法
     *
     * @param toString 是否生成
     * @return this
     * @since 3.5.10
     */
    public EntityConfigBuilder toString(boolean toString) {
        this.entity.toString = toString;
        return this;
    }

    /**
     * 设置字段是否生成文档注释
     *
     * @param fieldUseJavaDoc 是否生成文档注释
     * @return this
     * @since 3.5.10
     */
    public EntityConfigBuilder fieldUseJavaDoc(boolean fieldUseJavaDoc) {
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
    public EntityConfigBuilder importPackageFunction(Function<Set<String>, List<String>> importPackageFunction) {
        this.entity.importPackageFunction = importPackageFunction;
        return this;
    }

    /**
     * 注解处理方法 (含类与字段)
     *
     * @param annotationAttributesFunction 注解处理
     * @return this
     * @since 3.5.11
     */
    public EntityConfigBuilder annotationAttributesFunction(Function<List<? extends AnnotationAttributes>, List<AnnotationAttributes>> annotationAttributesFunction) {
        this.entity.annotationAttributesFunction = annotationAttributesFunction;
        return this;
    }


    protected Optional<Class<?>> tryLoadClass(String className) {
        try {
            return Optional.of(ClassUtils.toClassConfident(className));
        } catch (Exception e) {
            //当父类实体存在类加载器的时候,识别父类实体字段，不存在的情况就只有通过指定superEntityColumns属性了。
        }
        return Optional.empty();
    }
}
