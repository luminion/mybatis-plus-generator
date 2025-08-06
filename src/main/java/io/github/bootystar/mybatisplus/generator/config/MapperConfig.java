package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MapperConfig {

    private String superClass;
    private boolean enableMapperAnnotation = false;
    private boolean enableBaseResultMap = false;
    private boolean enableBaseColumnList = false;
    private String mapperSuffix;
    private String xmlSuffix;

}