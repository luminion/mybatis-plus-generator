package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.ConfigAdapter;
import lombok.Getter;

/**
 * 汇总构建器
 * @author bootystar
 */
@Getter
public class ConfigBuilder {
    private final Datasource.Builder datasourceBuilder;
    private final Global.Builder globalBuilder = new Global.Builder();
    private final Pkg.Builder packageBuilder = new Pkg.Builder();
    private final Strategy.Builder strategyBuilder = new Strategy.Builder();
    private final Injection.Builder injectionBuilder = new Injection.Builder();
    private final Mapper.Builder mapperBuilder = new Mapper.Builder();
    private final Service.Builder serviceBuilder = new Service.Builder();
    private final Entity.Builder entityBuilder = new Entity.Builder();
    private final Controller.Builder controllerBuilder = new Controller.Builder();
    
    public ConfigBuilder(String url, String username, String password) {
        datasourceBuilder = new Datasource.Builder(url, username, password);
    }

    public ConfigAdapter build() {
        return new ConfigAdapter(
                datasourceBuilder.build(),
                globalBuilder.build(),
                packageBuilder.build(),
                strategyBuilder.build(),
                injectionBuilder.build(),
                entityBuilder.build(),
                mapperBuilder.build(),
                serviceBuilder.build(),
                controllerBuilder.build()
        );
    }


}
