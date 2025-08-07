package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.baomidou.mybatisplus.generator.util.ClassUtils;
import io.github.bootystar.mybatisplus.generator.config.IReflectiveTemplate;
import io.github.bootystar.mybatisplus.generator.enums.OutputFile;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * controller配置
 *
 * @see com.baomidou.mybatisplus.generator.config.builder.Controller
 * @author bootystar
 */
@Getter
public class ControllerConfig implements IReflectiveTemplate {

    /**
     * 生成 <code>@RestController</code> 控制器（默认 true）
     * <pre>
     *      <code>@RestController</code> 
     *      -> 
     *      <code>@Controller</code>  
     * </pre>
     */
    protected boolean restStyle = true;

    /**
     * 驼峰转连字符（默认 true）
     * <pre>
     *       <code>@RequestMapping("/manager-user-action-history")</code>
     *       -> 
     *       <code>@RequestMapping("/managerUserActionHistory")</code>
     * </pre>
     */
    protected boolean hyphenStyle = true;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    protected String superClass;

    /**
     * 转换输出控制器文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterFileName = (entityName -> entityName + ConstVal.CONTROLLER);
    
    /**
     * 是否覆盖已有文件（默认 false）
     *
     * @since 3.5.2
     */
    protected boolean fileOverride;
    /**
     * 是否生成
     *
     * @since 3.5.6
     */
    protected boolean generate = true;

    /**
     * 模板路径
     * @since 3.5.6
     */
    protected String templatePath = OutputFile.controller.getTemplatePath();
    

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("controllerMappingHyphen", StringUtils.camelToHyphen(tableInfo.getEntityPath()));
        data.put("controllerMappingHyphenStyle", this.hyphenStyle);
        data.put("restControllerStyle", this.restStyle);
        data.put("superControllerClassPackage", StringUtils.isBlank(superClass) ? null : superClass);
        data.put("superControllerClass", ClassUtils.getSimpleName(this.superClass));
        return data;
    }

}