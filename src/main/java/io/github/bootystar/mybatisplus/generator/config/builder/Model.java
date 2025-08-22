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
         * 禁用查询dto继承实体类
         *
         * @return this
         */
        public Builder disableQueryDTOExtendsEntity() {
            this.config.extendsQueryDTO = true;
            return this;
        }

        /**
         * 禁用vo继承实体类
         *
         * @return this
         */
        public Builder disableVOExtendsEntity() {
            this.config.extendsVO = true;
            return this;
        }
    }
    
}
