package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.function.Consumer;

/**
 * 策略配置
 * 整合所有模块的配置类
 *
 * @author bootystar
 */
@Data
@Accessors(chain = true)
public class StrategyConfig {
    
    private EntityConfig entityConfig = new EntityConfig();
    private MapperConfig mapperConfig = new MapperConfig();
    private ServiceConfig serviceConfig = new ServiceConfig();
    private ControllerConfig controllerConfig = new ControllerConfig();
    private DtoConfig dtoConfig = new DtoConfig();
    private VoConfig voConfig = new VoConfig();

    public static class Builder {
        private final StrategyConfig strategyConfig = new StrategyConfig();

        public Builder entity(Consumer<EntityConfig.Builder> consumer) {
            EntityConfig.Builder builder = EntityConfig.builder();
            consumer.accept(builder);
            strategyConfig.setEntityConfig(builder.build());
            return this;
        }

        public Builder mapper(Consumer<MapperConfig.Builder> consumer) {
            MapperConfig.Builder builder = MapperConfig.builder();
            consumer.accept(builder);
            strategyConfig.setMapperConfig(builder.build());
            return this;
        }

        public Builder service(Consumer<ServiceConfig.Builder> consumer) {
            ServiceConfig.Builder builder = ServiceConfig.builder();
            consumer.accept(builder);
            strategyConfig.setServiceConfig(builder.build());
            return this;
        }

        public Builder controller(Consumer<ControllerConfig.Builder> consumer) {
            ControllerConfig.Builder builder = ControllerConfig.builder();
            consumer.accept(builder);
            strategyConfig.setControllerConfig(builder.build());
            return this;
        }

        public Builder dto(Consumer<DtoConfig.Builder> consumer) {
            DtoConfig.Builder builder = DtoConfig.builder();
            consumer.accept(builder);
            strategyConfig.setDtoConfig(builder.build());
            return this;
        }

        public Builder vo(Consumer<VoConfig.Builder> consumer) {
            VoConfig.Builder builder = VoConfig.builder();
            consumer.accept(builder);
            strategyConfig.setVoConfig(builder.build());
            return this;
        }

        // 为了向后兼容，保留原有的builder方法
        public EntityConfig.Builder entityBuilder() {
            return strategyConfig.getEntityConfig().builder();
        }

        public MapperConfig.Builder mapperBuilder() {
            return strategyConfig.getMapperConfig().builder();
        }

        public ServiceConfig.Builder serviceBuilder() {
            return strategyConfig.getServiceConfig().builder();
        }

        public ControllerConfig.Builder controllerBuilder() {
            return strategyConfig.getControllerConfig().builder();
        }

        public DtoConfig.Builder dtoBuilder() {
            return strategyConfig.getDtoConfig().builder();
        }

        public VoConfig.Builder voBuilder() {
            return strategyConfig.getVoConfig().builder();
        }

        public StrategyConfig build() {
            return strategyConfig;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // 为了向后兼容，保留原有的builder方法
    public EntityConfig.Builder entityBuilder() {
        return this.entityConfig.builder();
    }

    public MapperConfig.Builder mapperBuilder() {
        return this.mapperConfig.builder();
    }

    public ServiceConfig.Builder serviceBuilder() {
        return this.serviceConfig.builder();
    }

    public ControllerConfig.Builder controllerBuilder() {
        return this.controllerConfig.builder();
    }

    public DtoConfig.Builder dtoBuilder() {
        return this.dtoConfig.builder();
    }

    public VoConfig.Builder voBuilder() {
        return this.voConfig.builder();
    }
}
