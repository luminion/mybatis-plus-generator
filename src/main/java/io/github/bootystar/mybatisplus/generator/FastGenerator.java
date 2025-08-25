package io.github.bootystar.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import io.github.bootystar.mybatisplus.generator.config.builder.*;
import io.github.bootystar.mybatisplus.generator.config.rules.DateType;
import io.github.bootystar.mybatisplus.generator.config.rules.DbColumnType;
import io.github.bootystar.mybatisplus.generator.fill.Column;
import org.apache.ibatis.type.JdbcType;

import java.util.function.Function;

/**
 * @author bootystar
 */
public class FastGenerator {
    
    private final ConfigBuilder configBuilder;

    private FastGenerator(String url, String username, String password) {
        this.configBuilder = new ConfigBuilder(url, username, password);
    }

    public static FastGenerator create(String url, String username, String password) {
        return new FastGenerator(url, username, password);
    }

    public void execute(String... tableNames) {
        if (tableNames.length > 0) {
            configBuilder.getStrategyBuilder().addInclude(tableNames);
        }
        new AutoGenerator(configBuilder.build()).execute();
    }

    public FastGenerator initialize() {
        this.datasource(e->e
                        .typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            if (JdbcType.TINYINT == metaInfo.getJdbcType()) {
                                return DbColumnType.INTEGER;
                            }
                            if (JdbcType.SMALLINT == metaInfo.getJdbcType()) {
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .global(e->e
                        .dateType(DateType.TIME_PACK)
                )
                .output(e->e
//                        .outputDir()
                )
                .strategy(e->e
                )
                .injection(e->e
                )
                .entity(e->e
                        .idType(IdType.ASSIGN_ID)
                        .logicDeleteColumnName("deleted")
                        .versionColumnName("version")
//                        .disableSerialVersionUID()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT_UPDATE)
                                ,new Column("update_time", FieldFill.INSERT_UPDATE)
                                ,new Column("created_time", FieldFill.INSERT_UPDATE)
                                ,new Column("updated_time", FieldFill.INSERT_UPDATE)
                                ,new Column("create_at", FieldFill.INSERT_UPDATE)
                                ,new Column("update_at", FieldFill.INSERT_UPDATE)
                                ,new Column("created_at", FieldFill.INSERT_UPDATE)
                                ,new Column("updated_at", FieldFill.INSERT_UPDATE)
                                ,new Column("creator_id", FieldFill.INSERT_UPDATE)
                                ,new Column("updater_id", FieldFill.INSERT_UPDATE)
                                ,new Column("create_by", FieldFill.INSERT_UPDATE)
                                ,new Column("update_by", FieldFill.INSERT_UPDATE)
                                ,new Column("created_by", FieldFill.INSERT_UPDATE)
                                ,new Column("updated_by", FieldFill.INSERT_UPDATE)
                                
                        )
                )
                .mapper(e->e
                        .mapperAnnotation(org.apache.ibatis.annotations.Mapper.class)
                        .sortColumn("order", false)
                        .sortColumn("rank", false)
                        .sortColumn("sort", false)
                        .sortColumn("seq", false)
                        .sortColumn("sequence", false)
                        .sortColumn("create_time", true)
                        .sortColumn("id", true)
                )
                .service(e->e
                        .formatServiceFileName("%sService")
                )
                .controller(e->e
                )
        ;
        return this;
    }


    public FastGenerator datasource(Function<Datasource.Builder, Datasource.Builder> builder) {
        builder.apply(this.configBuilder.getDatasourceBuilder());
        return this;
    }

    public FastGenerator global(Function<Global.Builder, Global.Builder> builder) {
        builder.apply(this.configBuilder.getGlobalBuilder());
        return this;
    }

    public FastGenerator output(Function<Output.Builder, Output.Builder> builder) {
        builder.apply(this.configBuilder.getOutputBuilder());
        return this;
    }

    public FastGenerator strategy(Function<Strategy.Builder, Strategy.Builder> builder) {
        builder.apply(this.configBuilder.getStrategyBuilder());
        return this;
    }

    public FastGenerator injection(Function<Injection.Builder, Injection.Builder> builder) {
        builder.apply(this.configBuilder.getInjectionBuilder());
        return this;
    }

    public FastGenerator entity(Function<Entity.Builder, Entity.Builder> builder) {
        builder.apply(this.configBuilder.getEntityBuilder());
        return this;
    }

    public FastGenerator mapper(Function<Mapper.Builder, Mapper.Builder> builder) {
        builder.apply(this.configBuilder.getMapperBuilder());
        return this;
    }

    public FastGenerator service(Function<Service.Builder, Service.Builder> builder) {
        builder.apply(this.configBuilder.getServiceBuilder());
        return this;
    }

    public FastGenerator controller(Function<Controller.Builder, Controller.Builder> builder) {
        builder.apply(this.configBuilder.getControllerBuilder());
        return this;
    }
    
    public FastGenerator model(Function<Model.Builder, Model.Builder> builder) {
        builder.apply(this.configBuilder.getModelBuilder());
        return this;
    }


}