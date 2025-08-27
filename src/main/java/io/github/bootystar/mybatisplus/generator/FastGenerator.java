package io.github.bootystar.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import io.github.bootystar.mybatisplus.generator.config.GeneratorConfig;
import io.github.bootystar.mybatisplus.generator.config.rules.DateType;
import io.github.bootystar.mybatisplus.generator.config.rules.DbColumnType;
import io.github.bootystar.mybatisplus.generator.config.support.*;
import io.github.bootystar.mybatisplus.generator.engine.VelocityTemplateEngine;
import io.github.bootystar.mybatisplus.generator.fill.Column;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;

import java.util.function.Function;

/**
 * @author bootystar
 */
@Slf4j
public class FastGenerator {
    private final GeneratorConfig config;

    private FastGenerator(String url, String username, String password) {
        this.config = new GeneratorConfig(url, username, password);
    }

    public static FastGenerator create(String url, String username, String password) {
        return new FastGenerator(url, username, password);
    }

    public void execute(String... tableNames) {
        if (tableNames.length > 0) {
            config.getStrategyConfig().adapter().addInclude(tableNames);
        }
        this.execute();
    }

    private void execute() {
        log.debug("==========================准备生成文件...==========================");
        VelocityTemplateEngine templateEngine = new VelocityTemplateEngine(this.config);
        // 模板引擎初始化执行文件输出
        templateEngine.batchOutput().open();
        log.debug("==========================文件生成完成！！！==========================");
        String banner = "\n" + ".------..------..------..------..------..------..------..------..------.\n" + "|B.--. ||O.--. ||O.--. ||T.--. ||Y.--. ||S.--. ||T.--. ||A.--. ||R.--. |\n" + "| :(): || :/\\: || :/\\: || :/\\: || (\\/) || :/\\: || :/\\: || (\\/) || :(): |\n" + "| ()() || :\\/: || :\\/: || (__) || :\\/: || :\\/: || (__) || :\\/: || ()() |\n" + "| '--'B|| '--'O|| '--'O|| '--'T|| '--'Y|| '--'S|| '--'T|| '--'A|| '--'R|\n" + "`------'`------'`------'`------'`------'`------'`------'`------'`------'\n";
        System.out.println(banner);
        System.out.println("execute success! check files in following folder:");
        String path = config.getOutputConfig().getOutputDir();
        System.out.println(new File(path).getAbsolutePath());
    }

    public FastGenerator initialize() {
        this.datasource(e -> e.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
            if (JdbcType.TINYINT == metaInfo.getJdbcType()) {
                return DbColumnType.INTEGER;
            }
            if (JdbcType.SMALLINT == metaInfo.getJdbcType()) {
                return DbColumnType.INTEGER;
            }
            return typeRegistry.getColumnType(metaInfo);
        })).global(e -> e.dateType(DateType.TIME_PACK)).output(e -> e
//                        .outputDir()
        ).strategy(e -> e).injection(e -> e).entity(e -> e.idType(IdType.ASSIGN_ID).logicDeleteColumnName("deleted").versionColumnName("version")
//                        .disableSerialVersionUID()
                .addTableFills(new Column("create_time", FieldFill.INSERT_UPDATE), new Column("update_time", FieldFill.INSERT_UPDATE), new Column("created_time", FieldFill.INSERT_UPDATE), new Column("updated_time", FieldFill.INSERT_UPDATE), new Column("create_at", FieldFill.INSERT_UPDATE), new Column("update_at", FieldFill.INSERT_UPDATE), new Column("created_at", FieldFill.INSERT_UPDATE), new Column("updated_at", FieldFill.INSERT_UPDATE), new Column("creator_id", FieldFill.INSERT_UPDATE), new Column("updater_id", FieldFill.INSERT_UPDATE), new Column("create_by", FieldFill.INSERT_UPDATE), new Column("update_by", FieldFill.INSERT_UPDATE), new Column("created_by", FieldFill.INSERT_UPDATE), new Column("updated_by", FieldFill.INSERT_UPDATE)

                )).mapper(e -> e.mapperAnnotation(org.apache.ibatis.annotations.Mapper.class).sortColumn("order", false).sortColumn("rank", false).sortColumn("sort", false).sortColumn("seq", false).sortColumn("sequence", false).sortColumn("create_time", true).sortColumn("id", true)).service(e -> e).controller(e -> e);
        return this;
    }


    public FastGenerator datasource(Function<DataSourceConfig.Adapter, DataSourceConfig.Adapter> builder) {
        builder.apply(this.config.getDataSourceConfig().adapter());
        return this;
    }

    public FastGenerator global(Function<GlobalConfig.Adapter, GlobalConfig.Adapter> builder) {
        builder.apply(this.config.getGlobalConfig().adapter());
        return this;
    }

    public FastGenerator output(Function<OutputConfig.Adapter, OutputConfig.Adapter> builder) {
        builder.apply(this.config.getOutputConfig().adapter());
        return this;
    }

    public FastGenerator strategy(Function<StrategyConfig.Adapter, StrategyConfig.Adapter> builder) {
        builder.apply(this.config.getStrategyConfig().adapter());
        return this;
    }

    public FastGenerator injection(Function<InjectionConfig.Adapter, InjectionConfig.Adapter> builder) {
        builder.apply(this.config.getInjectionConfig().adapter());
        return this;
    }

    public FastGenerator entity(Function<EntityConfig.Adapter, EntityConfig.Adapter> builder) {
        builder.apply(this.config.getEntityConfig().adapter());
        return this;
    }

    public FastGenerator mapper(Function<MapperConfig.Adapter, MapperConfig.Adapter> builder) {
        builder.apply(this.config.getMapperConfig().adapter());
        return this;
    }

    public FastGenerator service(Function<ServiceConfig.Adapter, ServiceConfig.Adapter> builder) {
        builder.apply(this.config.getServiceConfig().adapter());
        return this;
    }

    public FastGenerator controller(Function<ControllerConfig.Adapter, ControllerConfig.Adapter> builder) {
        builder.apply(this.config.getControllerConfig().adapter());
        return this;
    }

    public FastGenerator model(Function<ModelConfig.Adapter, ModelConfig.Adapter> builder) {
        builder.apply(this.config.getModelConfig().adapter());
        return this;
    }


}