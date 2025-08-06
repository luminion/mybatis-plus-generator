package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ControllerConfig {

    private String superClass;
    private boolean restControllerStyle = false;
    private boolean hyphenStyle = false;
    private String controllerSuffix;

}