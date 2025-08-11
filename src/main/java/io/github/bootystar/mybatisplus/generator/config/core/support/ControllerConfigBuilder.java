package io.github.bootystar.mybatisplus.generator.config.core.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.po.ClassPayload;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import io.github.bootystar.mybatisplus.generator.util.ReflectUtil;

import java.util.Map;

/**
 * @author bootystar
 */
public class ControllerConfigBuilder implements IConfigBuilder<ControllerConfig> {
    protected final ControllerConfig controller = new ControllerConfig();

    @Override
    public ControllerConfig build() {
        return this.controller;
    }

    /**
     * 父类控制器
     *
     * @param clazz 父类控制器
     * @return this
     */
    public ControllerConfigBuilder superClass(Class<?> clazz) {
        return superClass(clazz.getName());
    }

    /**
     * 父类控制器
     *
     * @param superClass 父类控制器类名
     * @return this
     */
    public ControllerConfigBuilder superClass(String superClass) {
        this.controller.superClass = superClass;
        return this;
    }

    /**
     * 关闭驼峰转连字符
     *
     * @return this
     * @since 3.5.0
     */
    public ControllerConfigBuilder disableHyphenStyle() {
        this.controller.hyphenStyle = true;
        return this;
    }

    /**
     * 关闭@RestController控制器
     *
     * @return this
     * @since 3.5.0
     */
    public ControllerConfigBuilder disableRestController() {
        this.controller.restController = false;
        return this;
    }

    /**
     * 转换输出文件名称
     *
     * @param converter 　转换处理
     * @return this
     * @since 3.5.0
     */
    public ControllerConfigBuilder convertFileName(ConverterFileName converter) {
        this.controller.converterFileName = converter;
        return this;
    }

    /**
     * 格式化文件名称
     *
     * @param format 　格式
     * @return this
     * @since 3.5.0
     */
    public ControllerConfigBuilder formatFileName(String format) {
        return convertFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 覆盖已有文件
     *
     * @since 3.5.3
     */
    public ControllerConfigBuilder enableFileOverride() {
        this.controller.fileOverride = true;
        return this;
    }

    /**
     * 禁用生成
     *
     * @return this
     * @since 3.5.6
     */
    public ControllerConfigBuilder disable() {
        this.controller.generate = false;
        return this;
    }

    /**
     * 指定模板路径
     *
     * @param template 模板路径
     * @return this
     * @since 3.5.6
     */
    public ControllerConfigBuilder template(String template) {
        this.controller.templatePath = template;
        return this;
    }

    // ============自定义项==============

    /**
     * controller请求前缀
     *
     * @param url url
     * @return this
     */
    public ControllerConfigBuilder baseUrl(String url) {
        if (url == null || url.isEmpty()) {
            this.controller.baseUrl = url;
            return this;
        }
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        this.controller.baseUrl = url;
        return this;
    }

    /**
     * 跨域注解
     *
     * @return this
     */
    public ControllerConfigBuilder enableCrossOrigins() {
        this.controller.crossOrigins = true;
        return this;
    }

    /**
     * 使用javax包作为javaEE api
     * <p>springboot2.x使用javax, springboot3.x使用jakarta</p>
     * 默认使用jakarta
     *
     * @return this
     */
    public ControllerConfigBuilder enableJavax() {
        this.controller.javaApiPackage = "javax";
        return this;
    }


    /**
     * 指定controller的返回结果包装类及方法
     *
     * @param methodReference 方法引用
     * @return this
     */
    public <R> ControllerConfigBuilder returnMethod(SFunction<Object, R> methodReference) {
        this.controller.returnMethod = ReflectUtil.lambdaMethodInfo(methodReference, Object.class);
        return this;
    }

    /**
     * 指定controller返回的分页包装类及方法
     *
     * @param methodReference 方法参考
     * @return this
     */
    public <O, R> ControllerConfigBuilder pageMethod(SFunction<IPage<O>, R> methodReference) {
        this.controller.pageMethod = ReflectUtil.lambdaMethodInfo(methodReference, IPage.class);
        return this;
    }

    /**
     * 使用@AutoWired替换@Resource
     *
     * @return this
     */
    public ControllerConfigBuilder enableAutoWired() {
        this.controller.autoWired = true;
        return this;
    }

    /**
     * 禁止复杂查询使用post请求(使用Get请求替代)
     *
     * @return this
     */
    public ControllerConfigBuilder disablePost() {
        this.controller.postQuery = false;
        return this;
    }
  
    /**
     * 禁止get请求使用@RequestBody注解
     *
     * @return this
     */
    public ControllerConfigBuilder disableGetRequestBody() {
        this.controller.getRequestBody = false;
        return this;
    }

    /**
     * 增删查改使用restful风格
     *
     * @return this
     */
    public ControllerConfigBuilder enableRestful() {
        this.controller.restful = true;
        return this;
    }

    /**
     * 禁用路径变量
     *
     * @return {@link B }
     */
    public ControllerConfigBuilder disablePathVariable() {
        this.controller.pathVariable = false;
        return this;
    }

    /**
     * 禁用消息体接收数据
     *
     * @return this
     */
    public ControllerConfigBuilder disableRequestBody() {
        this.controller.requestBody = false;
        return this;
    }

    /**
     * 禁用参数校验注解
     *
     * @return this
     */
    public ControllerConfigBuilder disableValidated() {
        this.controller.validated = false;
        return this;
    }
}
