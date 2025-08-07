package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;

/**
 * controller配置构建器
 *
 * @author bootystar
 * @see com.baomidou.mybatisplus.generator.config.builder.Controller.Builder
 */
public class ControllerConfigBuilder implements IConfigBuilder<ControllerConfig> {
    private final ControllerConfig controller = new ControllerConfig();

    @Override
    public ControllerConfig build() {
        return controller;
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
     * @see Controller.Builder#enableHyphenStyle()
     * @since 3.5.0
     */
    public ControllerConfigBuilder disableHyphenStyle() {
        this.controller.hyphenStyle = false;
        return this;
    }

    /**
     * 关闭@RestController控制器, 使用@Controller
     *
     * @return this
     * @see Controller.Builder#enableRestStyle()
     * @since 3.5.0
     */
    public ControllerConfigBuilder disableRestStyle() {
        this.controller.restStyle = false;
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


}
