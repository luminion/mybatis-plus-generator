package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.TemplateConfig;

/**
 * @author bootystar
 */
public class Template extends TemplateConfig {
    public static class Builder {
        protected final Template config = new Template();

        protected Builder() {
        }

        protected TemplateConfig build() {
            return this.config;
        }

        /**
         * 实体
         *
         * @param template 模板
         */
        public Builder entity(String template) {
            this.config.entity = template;
            return this;
        }
//
//        /**
//         * 实体(kotlin)
//         * @param template 模板
//         */
//        public Builder entityKt(String template) {
//            this.config.entityKt = template;
//            return this;
//        }

        /**
         * 查询dto
         *
         * @param template 模板
         */
        public Builder entityQueryDTO(String template) {
            this.config.entityQueryDTO = template;
            return this;
        }

        /**
         * 新增dto
         *
         * @param template 模板
         */
        public Builder entityInsertDTO(String template) {
            this.config.entityInsertDTO = template;
            return this;
        }

        /**
         * 修改dto
         *
         * @param template 模板
         */
        public Builder entityUpdateDTO(String template) {
            this.config.entityUpdateDTO = template;
            return this;
        }

        /**
         * vo
         *
         * @param template 模板
         */
        public Builder entityVO(String template) {
            this.config.entityVO = template;
            return this;
        }

        /**
         * mapper
         *
         * @param template 模板
         */
        public Builder mapper(String template) {
            this.config.mapper = template;
            return this;
        }

        /**
         * mapper xml
         *
         * @param template 模板
         */
        public Builder mapperXml(String template) {
            this.config.mapperXml = template;
            return this;
        }

        /**
         * 服务
         *
         * @param template 模板
         */
        public Builder service(String template) {
            this.config.service = template;
            return this;
        }

        /**
         * 服务实现
         *
         * @param template 模板
         */
        public Builder serviceImpl(String template) {
            this.config.serviceImpl = template;
            return this;
        }

        /**
         * 控制器
         *
         * @param template 模板
         */
        public Builder controller(String template) {
            this.config.controller = template;
            return this;
        }

    }


}
