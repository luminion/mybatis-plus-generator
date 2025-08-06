package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceConfig {

    private String superServiceClass;
    private String superServiceImplClass;
    private boolean serviceInterface = true;
    private String serviceSuffix;
    private String serviceImplSuffix;

}