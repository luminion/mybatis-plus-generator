package io.github.bootystar.mybatisplus.generator.generator.core;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import io.github.bootystar.mybatisplus.generator.config.*;
import io.github.bootystar.mybatisplus.generator.config.builder.BaseBuilder;
import io.github.bootystar.mybatisplus.generator.engine.EnhanceVelocityTemplateEngine;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * 自定义配置代码生成器-抽象父类
 *
 * @author bootystar
 */
@Getter
@Slf4j
@SuppressWarnings("unused")
public abstract class AbstractGenerator<B extends BaseBuilder<B>> implements EnhanceGenerator<B> {

    protected B customConfigBuilder;
    protected DataSourceConfig.Builder dataSourceConfigBuilder;
    protected GlobalConfig.Builder globalConfigBuilder = new GlobalConfig.Builder();
    protected PackageConfig.Builder packageConfigBuilder = new PackageConfig.Builder();
    protected com.baomidou.mybatisplus.generator.config.StrategyConfig.Builder strategyConfigBuilder = new com.baomidou.mybatisplus.generator.config.StrategyConfig.Builder();
    protected DtoConfig.Builder dtoConfigBuilder = new DtoConfig.Builder();
    protected VoConfig.Builder voConfigBuilder = new VoConfig.Builder();
    protected GlobalCustomConfig.Builder globalCustomConfigBuilder = new GlobalCustomConfig.Builder();

    // 新增的配置Builder
    protected ControllerConfig.Builder controllerConfigBuilder = new ControllerConfig.Builder();
    protected ServiceConfig.Builder serviceConfigBuilder = new ServiceConfig.Builder();
    protected MapperConfig.Builder mapperConfigBuilder = new MapperConfig.Builder();
    protected EntityConfig.Builder entityConfigBuilder = new EntityConfig.Builder();

    public AbstractGenerator(String url, String username, String password) {
        this.dataSourceConfigBuilder = new DataSourceConfig.Builder(url, username, password);
        this.globalConfigBuilder
                .dateType(DateType.TIME_PACK)
                .author("bootystar")
                .outputDir(System.getProperty("user.dir") + "/src/main/java")
        ;
        this.packageConfigBuilder
                .parent("io.github.bootystar")
                .xml("mapper")
        ;
    }

    public AbstractGenerator(String url, String username, String password, B customConfigBuilder) {
        this(url, username, password);
        this.customConfigBuilder = customConfigBuilder;
    }

    /**
     * 构建StrategyConfig，整合所有配置
     */
    private com.baomidou.mybatisplus.generator.config.StrategyConfig buildStrategyConfig() {
        return strategyConfigBuilder.build();
    }

    private void execute() {
        log.debug("==========================准备生成文件...==========================");
        // 初始化配置
        io.github.bootystar.mybatisplus.generator.config.builder.ConfigBuilder config = new io.github.bootystar.mybatisplus.generator.config.builder.ConfigBuilder(
                this.packageConfigBuilder.build(),
                this.dataSourceConfigBuilder.build(),
                this.buildStrategyConfig(),
                this.globalConfigBuilder.build(),
                this.dtoConfigBuilder.build(),
                this.voConfigBuilder.build(),
                this.globalCustomConfigBuilder.build()
        );
        EnhanceVelocityTemplateEngine templateEngine = new EnhanceVelocityTemplateEngine(config);
        // 模板引擎初始化执行文件输出
        templateEngine.batchOutput();
        log.debug("==========================文件生成完成！！！==========================");
        String banner = "\n" +
                ".------..------..------..------..------..------..------..------..------.\n" +
                "|B.--. ||O.--. ||O.--. ||T.--. ||Y.--. ||S.--. ||T.--. ||A.--. ||R.--. |\n" +
                "| :(): || :/\\: || :/\\: || :/\\: || (\\/) || :/\\: || :/\\: || (\\/) || :(): |\n" +
                "| ()() || :\\/: || :\\/: || (__) || :\\/: || :\\/: || (__) || :\\/: || ()() |\n" +
                "| '--'B|| '--'O|| '--'O|| '--'T|| '--'Y|| '--'S|| '--'T|| '--'A|| '--'R|\n" +
                "`------'`------'`------'`------'`------'`------'`------'`------'`------'\n";
        System.out.println(banner);
        System.out.println("execute success! check files in following folder:");
        String path = config.getPathInfo().get(io.github.bootystar.mybatisplus.generator.config.OutputFile.parent);
        System.out.println(new File(path).getAbsolutePath());
    }

    /**
     * 执行
     *
     * @param tableNames 表名(不填为全部)
     */
    @Override
    public void execute(String... tableNames) {
        if (tableNames != null && tableNames.length > 0) {
            strategyConfigBuilder.addInclude(Arrays.asList(tableNames));
        }
        execute();
    }

    @Override
    public EnhanceGenerator<B> dataSourceConfig(Consumer<DataSourceConfig.Builder> consumer) {
        consumer.accept(dataSourceConfigBuilder);
        return this;
    }

    public EnhanceGenerator<B> globalConfig(Consumer<GlobalConfig.Builder> consumer) {
        consumer.accept(globalConfigBuilder);
        return this;
    }

    public EnhanceGenerator<B> packageConfig(Consumer<PackageConfig.Builder> consumer) {
        consumer.accept(packageConfigBuilder);
        return this;
    }

    public EnhanceGenerator<B> strategyConfig(Consumer<com.baomidou.mybatisplus.generator.config.StrategyConfig.Builder> consumer) {
        consumer.accept(strategyConfigBuilder);
        return this;
    }

    @Override
    public EnhanceGenerator<B> dtoConfig(Consumer<DtoConfig.Builder> consumer) {
        consumer.accept(dtoConfigBuilder);
        return this;
    }

    @Override
    public EnhanceGenerator<B> voConfig(Consumer<VoConfig.Builder> consumer) {
        consumer.accept(voConfigBuilder);
        return this;
    }

    @Override
    public EnhanceGenerator<B> globalCustomConfig(Consumer<GlobalCustomConfig.Builder> consumer) {
        consumer.accept(globalCustomConfigBuilder);
        return this;
    }

    // 新增的配置方法
    @Override
    public EnhanceGenerator<B> controllerConfig(Consumer<ControllerConfig.Builder> consumer) {
        consumer.accept(controllerConfigBuilder);
        return this;
    }

    @Override
    public EnhanceGenerator<B> serviceConfig(Consumer<ServiceConfig.Builder> consumer) {
        consumer.accept(serviceConfigBuilder);
        return this;
    }

    @Override
    public EnhanceGenerator<B> mapperConfig(Consumer<MapperConfig.Builder> consumer) {
        consumer.accept(mapperConfigBuilder);
        return this;
    }

    @Override
    public EnhanceGenerator<B> entityConfig(Consumer<EntityConfig.Builder> consumer) {
        consumer.accept(entityConfigBuilder);
        return this;
    }

    @Override
    public EnhanceGenerator<B> customConfig(Consumer<B> consumer) {
        if (customConfigBuilder != null) {
            consumer.accept(customConfigBuilder);
        }
        return this;
    }

    @Override
    public EnhanceGenerator<B> injectionConfig(Consumer<InjectionConfig.Builder> consumer) {
        // 暂时不实现，保持接口兼容性
        return this;
    }

    /**
     * mapper.xml文件在项目根目录/src/main/resources/下的对应目录
     *
     * @param path 目录
     * @return {@link AbstractGenerator }
     */
    @Override
    public EnhanceGenerator<B> mapperXmlResource(String path) {
        String projectPath = System.getProperty("user.dir");
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        packageConfigBuilder.pathInfo(Collections.singletonMap(io.github.bootystar.mybatisplus.generator.config.OutputFile.mapperXml, projectPath + "/src/main/resources/" + path));
        return this;
    }

    /**
     * 初始化常用配置项
     *
     * @return {@link AbstractGenerator }
     */
    @Override
    public EnhanceGenerator<B> initialize() {
        globalConfigBuilder
                .disableOpenDir()
        ;
//        dataSourceConfigBuilder
//                .typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//                    if (JdbcType.TINYINT == metaInfo.getJdbcType()) {
//                        return DbColumnType.INTEGER;
//                    }
//                    if (JdbcType.SMALLINT == metaInfo.getJdbcType()) {
//                        return DbColumnType.INTEGER;
//                    }
//                    return typeRegistry.getColumnType(metaInfo);
//                })
//        ;
        // 使用新的配置结构
        dtoConfigBuilder
                .editExcludeColumns("create_time", "update_time", "create_by", "update_by", "created_by", "updated_by", "create_at", "update_at", "created_at", "updated_at")
        ;
        mapperConfigBuilder
                .sortColumn("order", false)
                .sortColumn("rank", false)
                .sortColumn("sort", false)
                .sortColumn("seq", false)
                .sortColumn("sequence", false)
                .sortColumn("create_time", true)
                .sortColumn("id", true)
                .enableMapperAnnotation()
        ;
        entityConfigBuilder
                .idType(IdType.ASSIGN_ID)
                .logicDeleteColumnName("deleted")
                .versionColumnName("version")
                .disableSerialVersionUID()
                .enableLombok()
        ;
        serviceConfigBuilder
                .formatServiceFileName("%sService")
        ;
        controllerConfigBuilder
                .enableHyphenStyle()
                .enableRestStyle()
        ;

        // 同时配置官方的StrategyConfig
        strategyConfigBuilder.entityBuilder()
                .idType(IdType.ASSIGN_ID)
                .logicDeleteColumnName("deleted")
                .versionColumnName("version")
                .disableSerialVersionUID()
                .enableLombok()
        ;
        strategyConfigBuilder.mapperBuilder()
                .enableMapperAnnotation()
        ;
        strategyConfigBuilder.serviceBuilder()
                .formatServiceFileName("%sService")
        ;
        strategyConfigBuilder.controllerBuilder()
                .enableHyphenStyle()
                .enableRestStyle()
        ;
        return this;
    }


    /**
     * 启用全局文件覆盖
     *
     * @return {@link AbstractGenerator }
     */
    @Override
    public EnhanceGenerator<B> enableGlobalFileOverwrite() {
        dtoConfigBuilder.enableFileOverride();
        voConfigBuilder.enableFileOverride();
        entityConfigBuilder.enableFileOverride();
        mapperConfigBuilder.enableFileOverride();
        serviceConfigBuilder.enableFileOverride();
        controllerConfigBuilder.enableFileOverride();
        return this;
    }
}
