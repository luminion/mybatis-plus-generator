package io.github.bootystar.mybatisplus.generator.generator.core;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import io.github.bootystar.mybatisplus.generator.config.DtoConfig;
import io.github.bootystar.mybatisplus.generator.config.GlobalCustomConfig;
import io.github.bootystar.mybatisplus.generator.config.VoConfig;
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
public abstract class AbstractGenerator implements EnhanceGenerator {
    protected DataSourceConfig.Builder dataSourceConfigBuilder;
    protected GlobalConfig.Builder globalConfigBuilder = new GlobalConfig.Builder();
    protected PackageConfig.Builder packageConfigBuilder = new PackageConfig.Builder();
    protected StrategyConfig.Builder strategyConfigBuilder = new StrategyConfig.Builder();
    protected DtoConfig.Builder dtoConfigBuilder = new DtoConfig.Builder();
    protected VoConfig.Builder voConfigBuilder = new VoConfig.Builder();
    protected GlobalCustomConfig.Builder globalCustomConfigBuilder = new GlobalCustomConfig.Builder();

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
//        this.strategyConfigBuilder.entityBuilder()
//                .javaTemplate("/bootystar/templates/vm/base/entity.java")
//        ;
//        this.strategyConfigBuilder.mapperBuilder()
//                .mapperAnnotation(org.apache.ibatis.annotations.Mapper.class)
//                .mapperTemplate("/bootystar/templates/vm/base/mapper.java")
//                .mapperXmlTemplate("/bootystar/templates/vm/base/mapper.xml")
//        ;
//        this.strategyConfigBuilder.serviceBuilder()
//                .serviceTemplate("/bootystar/templates/vm/base/service.java")
//                .serviceImplTemplate("/bootystar/templates/vm/base/serviceImpl.java")
//        ;
//        this.strategyConfigBuilder.controllerBuilder()
//                .template("/bootystar/templates/vm/base/controller.java")
//        ;
    }

    private void execute() {
        log.debug("==========================准备生成文件...==========================");
        // 初始化配置
        ConfigBuilder config = new ConfigBuilder(
                this.packageConfigBuilder.build(),
                this.dataSourceConfigBuilder.build(),
                this.strategyConfigBuilder.build(),
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
        String path = config.getPathInfo().get(OutputFile.parent);
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
    public EnhanceGenerator dataSourceConfig(Consumer<DataSourceConfig.Builder> consumer) {
        consumer.accept(dataSourceConfigBuilder);
        return this;
    }

    public EnhanceGenerator globalConfig(Consumer<GlobalConfig.Builder> consumer) {
        consumer.accept(globalConfigBuilder);
        return this;
    }

    public EnhanceGenerator packageConfig(Consumer<PackageConfig.Builder> consumer) {
        consumer.accept(packageConfigBuilder);
        return this;
    }

    public EnhanceGenerator strategyConfig(Consumer<StrategyConfig.Builder> consumer) {
        consumer.accept(strategyConfigBuilder);
        return this;
    }

    public EnhanceGenerator dtoConfig(Consumer<DtoConfig.Builder> consumer) {
        consumer.accept(dtoConfigBuilder);
        return this;
    }

    public EnhanceGenerator voConfig(Consumer<VoConfig.Builder> consumer) {
        consumer.accept(voConfigBuilder);
        return this;
    }

    public EnhanceGenerator globalCustomConfig(Consumer<GlobalCustomConfig.Builder> consumer) {
        consumer.accept(globalCustomConfigBuilder);
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
        packageConfigBuilder.pathInfo(Collections.singletonMap(OutputFile.mapper, projectPath + "/src/main/resources/" + path));
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
        customConfigBuilder
                .editExcludeColumns("create_time", "update_time", "create_by", "update_by", "created_by", "updated_by", "create_at", "update_at", "created_at", "updated_at")
                .sortColumn("order", false)
                .sortColumn("rank", false)
                .sortColumn("sort", false)
                .sortColumn("seq", false)
                .sortColumn("sequence", false)
                .sortColumn("create_time", true)
                .sortColumn("id", true)
        ;
        strategyConfigBuilder.entityBuilder()
                .idType(IdType.ASSIGN_ID)
                .logicDeleteColumnName("deleted")
                .versionColumnName("version")
                .disableSerialVersionUID()
                .enableLombok()
        ;
        strategyConfigBuilder.mapperBuilder()
                .mapperAnnotation(org.apache.ibatis.annotations.Mapper.class)
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
        customConfigBuilder
                .enableFileOverride()
        ;
        strategyConfigBuilder.entityBuilder()
                .enableFileOverride()
        ;
        strategyConfigBuilder.mapperBuilder()
                .enableFileOverride()
        ;
        strategyConfigBuilder.serviceBuilder()
                .enableFileOverride()
        ;
        strategyConfigBuilder.controllerBuilder()
                .enableFileOverride()
        ;
        return this;
    }
}
