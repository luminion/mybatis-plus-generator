package io.github.bootystar.mybatisplus.generator.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EntityConfig {

    private String superclass;
    private boolean enableSerialVersionUID = true;
    private boolean enableChainModel = false;
    private boolean enableLombok = false;
    private boolean enableTableFieldAnnotation = false;
    private boolean enableRemoveIsPrefix = false;
    private boolean activeRecord = false;
    private boolean enableColumnConstant = false;
    private String versionFieldName;
    private String logicDeleteFieldName;
    private NamingStrategy naming = NamingStrategy.no_change;
    private NamingStrategy columnNaming = NamingStrategy.no_change;
    private IdType idType;

}