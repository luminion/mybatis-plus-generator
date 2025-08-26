package io.github.bootystar.mybatisplus.generator.config.builder;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.bootystar.mybatisplus.generator.config.core.ControllerConfig;
import io.github.bootystar.mybatisplus.generator.config.po.ClassPayload;
import io.github.bootystar.mybatisplus.generator.util.ReflectUtil;

/**
 * @author bootystar
 */
public class Controller extends ControllerConfig {
    public static class Builder {
        protected final Controller config = new Controller();

        protected Builder() {
        }

        protected ControllerConfig build() {
            return this.config;
        }

        /**
         * 父类控制器
         *
         * @param clazz 父类控制器
         * @return this
         */
        public Builder superClass(Class<?> clazz) {
            return superClass(clazz.getName());
        }

        /**
         * 父类控制器
         *
         * @param superClass 父类控制器类名
         * @return this
         */
        public Builder superClass(String superClass) {
            this.config.superClass = superClass;
            return this;
        }

        /**
         * 关闭@RestController控制器
         *
         * @return this
         * @since 3.5.0
         */
        public Builder disableRestController() {
            this.config.restController = false;
            return this;
        }

        /**
         * 关闭驼峰转连字符
         *
         * @return this
         * @since 3.5.0
         */
        public Builder disableHyphenStyle() {
            this.config.hyphenStyle = false;
            return this;
        }

        /**
         * controller请求前缀
         *
         * @param url url
         * @return this
         */
        public Builder baseUrl(String url) {
            if (url == null || url.isEmpty()) {
                this.config.baseUrl = null;
                return this;
            }
            if (!url.startsWith("/")) {
                url = "/" + url;
            }
            if (url.endsWith("/")) {
                url = url.substring(0, url.length() - 1);
            }
            this.config.baseUrl = url;
            return this;
        }

        /**
         * 跨域注解
         *
         * @return this
         */
        public Builder enableCrossOrigins() {
            this.config.crossOrigins = true;
            return this;
        }

        /**
         * 复杂参数查询不再使用post, 而是使用get
         *
         * @return this
         */
        public Builder disablePostQuery() {
            this.config.postQuery = false;
            return this;
        }

        /**
         * 增删查改使用restful风格
         *
         * @return this
         */
        public Builder enableRestful() {
            this.config.restful = true;
            return this;
        }

        /**
         * 禁用路径变量
         *
         * @return this
         */
        public Builder disablePathVariable() {
            this.config.pathVariable = false;
            return this;
        }

        /**
         * 禁用消息体接收数据
         *
         * @return this
         */
        public Builder disableRequestBody() {
            this.config.requestBody = false;
            return this;
        }

        /**
         * 指定controller的返回结果包装类及方法
         *
         * @param methodReference 方法引用
         * @return this
         */
        public <R> Builder returnMethod(SFunction<Object, R> methodReference) {
            this.config.returnMethod = ReflectUtil.lambdaMethodInfo(methodReference, Object.class);
            return this;
        }

        /**
         * 指定controller返回的分页包装类及方法
         *
         * @param methodReference 方法参考
         * @return this
         */
        public <O, R> Builder pageMethod(SFunction<IPage<O>, R> methodReference) {
            this.config.pageMethod = ReflectUtil.lambdaMethodInfo(methodReference, IPage.class);
            return this;
        }

        /**
         * 指定查询的DTO
         *
         * @return this
         */
        public Builder queryDTO(Class<?> queryDTO) {
            this.config.queryDTO = new ClassPayload(queryDTO);
            return this;
        }


    }
}
