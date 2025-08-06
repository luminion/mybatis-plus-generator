package io.github.bootystar.mybatisplus.generator.config;

import io.github.bootystar.mybatisplus.generator.info.MethodInfo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ControllerConfig {

    // MyBatis-Plus官方配置属性
    private String superClass;
    private boolean restControllerStyle = false;
    private boolean hyphenStyle = false;
    private String controllerSuffix = "Controller";
    private boolean fileOverride = false;
    private String template;

    // 自定义controller相关配置（从BaseConfig迁移）
    private String baseUrl;
    private boolean crossOrigins = false;
    private String javaApiPackage = "javax";
    private boolean autoWired = false;
    private MethodInfo returnMethod = new MethodInfo();
    private MethodInfo pageMethod = new MethodInfo();
    private boolean restful = false;
    private boolean pathVariable = true;
    private boolean requestBody = true;
    private boolean validated = true;
    private boolean postQuery = true;

    public static class Builder {
        private final ControllerConfig controllerConfig = new ControllerConfig();

        // MyBatis-Plus官方配置方法
        public Builder superClass(String superClass) {
            controllerConfig.setSuperClass(superClass);
            return this;
        }

        public Builder enableRestStyle() {
            controllerConfig.setRestControllerStyle(true);
            return this;
        }

        public Builder disableRestStyle() {
            controllerConfig.setRestControllerStyle(false);
            return this;
        }

        public Builder enableHyphenStyle() {
            controllerConfig.setHyphenStyle(true);
            return this;
        }

        public Builder disableHyphenStyle() {
            controllerConfig.setHyphenStyle(false);
            return this;
        }

        public Builder formatFileName(String formatFileName) {
            controllerConfig.setControllerSuffix(formatFileName.replace("%s", ""));
            return this;
        }

        public Builder enableFileOverride() {
            controllerConfig.setFileOverride(true);
            return this;
        }

        public Builder disableFileOverride() {
            controllerConfig.setFileOverride(false);
            return this;
        }

        public Builder template(String template) {
            controllerConfig.setTemplate(template);
            return this;
        }

        // 自定义配置方法（从BaseBuilder迁移）
        public Builder baseUrl(String baseUrl) {
            if (baseUrl == null || baseUrl.isEmpty()) {
                controllerConfig.setBaseUrl(baseUrl);
                return this;
            }
            if (!baseUrl.startsWith("/")) {
                baseUrl = "/" + baseUrl;
            }
            if (baseUrl.endsWith("/")) {
                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
            }
            controllerConfig.setBaseUrl(baseUrl);
            return this;
        }

        public Builder enableCrossOrigins() {
            controllerConfig.setCrossOrigins(true);
            return this;
        }

        public Builder enableJakartaApi() {
            controllerConfig.setJavaApiPackage("jakarta");
            return this;
        }

        public Builder enableAutoWired() {
            controllerConfig.setAutoWired(true);
            return this;
        }

        public Builder returnMethod(MethodInfo returnMethod) {
            controllerConfig.setReturnMethod(returnMethod);
            return this;
        }

        public Builder pageMethod(MethodInfo pageMethod) {
            controllerConfig.setPageMethod(pageMethod);
            return this;
        }

        public Builder enableRestful() {
            controllerConfig.setRestful(true);
            return this;
        }

        public Builder disableRestful() {
            controllerConfig.setRestful(false);
            return this;
        }

        public Builder disablePathVariable() {
            controllerConfig.setPathVariable(false);
            return this;
        }

        public Builder disableRequestBody() {
            controllerConfig.setRequestBody(false);
            return this;
        }

        public Builder disableValidated() {
            controllerConfig.setValidated(false);
            return this;
        }

        public Builder disablePostQuery() {
            controllerConfig.setPostQuery(false);
            return this;
        }

        public ControllerConfig build() {
            return controllerConfig;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}