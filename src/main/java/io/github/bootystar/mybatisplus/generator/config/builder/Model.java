package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.ModelConfig;

/**
 * @author bootystar
 */
public class Model extends ModelConfig {
    public static class Builder {
        protected final Model config = new Model();

        protected Builder() {
        }

        protected ModelConfig build() {
            return this.config;
        }
        
        /**
         * 查询dto继承实体类
         *
         * @return this
         */
        public Builder enableQueryDTOExtendsEntity() {
            this.config.queryDTOExtendsEntity = true;
            return this;
        }
        
        /**
         * 查询vo继承实体类
         *
         * @return this
         */
        public Builder enableQueryVOExtendsEntity() {
            this.config.queryVOExtendsEntity = true;
            return this;
        }
        
        /**
         * 使用新增dto作为导入dto
         *
         * @return this
         */
        public Builder enableImportOnCreateDTO() {
            this.config.importOnCreateDTO = true;
            return this;
        }
        
        /**
         * 使用vo作为导出vo
         *
         * @return this
         */
        public Builder enableExportOnQueryVO() {
            this.config.exportOnQueryVO = true;
            return this;
        }
        
    }
    
}
