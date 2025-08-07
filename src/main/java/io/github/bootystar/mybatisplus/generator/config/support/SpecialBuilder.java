package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;

/**
 * 特殊配置构建器
 * 
 * @author bootystar
 */
public class SpecialBuilder implements IConfigBuilder<Special> {
    private final Special special = new Special();
    @Override
    public Special build() {
        return special;
    }
}
