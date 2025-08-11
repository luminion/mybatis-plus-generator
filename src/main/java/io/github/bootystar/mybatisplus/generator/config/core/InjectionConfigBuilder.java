package io.github.bootystar.mybatisplus.generator.config.core;

import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.builder.CustomFile;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author bootystar
 */
public class InjectionConfigBuilder implements IConfigBuilder<InjectionConfig> {
    protected final InjectionConfig injectionConfig = new InjectionConfig();

    @Override
    public InjectionConfig build() {
        return this.injectionConfig;
    }

    /**
     * 输出文件之前消费者
     *
     * @param biConsumer 消费者
     * @return this
     */
    public InjectionConfigBuilder beforeOutputFile(BiConsumer<TableInfo, Map<String, Object>> biConsumer) {
        this.injectionConfig.beforeOutputFileBiConsumer = biConsumer;
        return this;
    }

    /**
     * 自定义配置 Map 对象
     *
     * @param customMap Map 对象
     * @return this
     */
    public InjectionConfigBuilder customMap(Map<String, Object> customMap) {
        this.injectionConfig.customMap = customMap;
        return this;
    }

    /**
     * 自定义配置模板文件
     *
     * @param customFile key为文件名称，value为文件路径
     * @return this
     */
    public InjectionConfigBuilder customFile(Map<String, String> customFile) {
        return customFile(customFile.entrySet().stream()
                .map(e -> new CustomFile.Builder().fileName(e.getKey()).templatePath(e.getValue()).build())
                .collect(Collectors.toList()));
    }

    public InjectionConfigBuilder customFile(CustomFile customFile) {
        this.injectionConfig.customFiles.add(customFile);
        return this;
    }

    public InjectionConfigBuilder customFile(List<CustomFile> customFiles) {
        this.injectionConfig.customFiles.addAll(customFiles);
        return this;
    }

    public InjectionConfigBuilder customFile(Consumer<CustomFile.Builder> consumer) {
        CustomFile.Builder builder = new CustomFile.Builder();
        consumer.accept(builder);
        this.injectionConfig.customFiles.add(builder.build());
        return this;
    }


}
