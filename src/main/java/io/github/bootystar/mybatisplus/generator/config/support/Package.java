package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import io.github.bootystar.mybatisplus.generator.config.IReflectiveTemplate;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包配置
 *
 * @see com.baomidou.mybatisplus.generator.config.PackageConfig
 * @author bootystar
 */
@Getter
public class Package implements IReflectiveTemplate {
    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    protected String parent = "com.baomidou";
    /**
     * 父包模块名
     */
    protected String moduleName = "";
    /**
     * Entity包名
     */
    protected String entity = "entity";
    /**
     * Service包名
     */
    protected String service = "service";
    /**
     * Service Impl包名
     */
    protected String serviceImpl = "service.impl";
    /**
     * Mapper包名
     */
    protected String mapper = "mapper";
    /**
     * Mapper XML包名
     */
    protected String xml = "mapper.xml";
    /**
     * Controller包名
     */
    protected String controller = "controller";
    /**
     * 路径配置信息
     */
    protected Map<String, String> pathInfo;
    /**
     * 包配置信息
     *
     * @since 3.5.0
     */
    protected final Map<String, String> packageInfo = new HashMap<>();

    /**
     * 父包名
     */
    public String getParent() {
        if (StringUtils.isNotBlank(moduleName)) {
            return parent + StringPool.DOT + moduleName;
        }
        return parent;
    }

    /**
     * 连接父子包名
     *
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    public String joinPackage(String subPackage) {
        String parent = getParent();
        return StringUtils.isBlank(parent) ? subPackage : (parent + StringPool.DOT + subPackage);
    }

    /**
     * 获取包配置信息
     *
     * @return 包配置信息
     * @since 3.5.0
     */
    public Map<String, String> getPackageInfo() {
        return getPackageInfo((InjectionConfig) null);
    }

    /**
     * 获取包配置信息
     *
     * @param injectionConfig 配置文件信息
     * @return 包配置信息
     * @since 3.5.10
     */
    public Map<String, String> getPackageInfo(InjectionConfig injectionConfig) {
        if (packageInfo.isEmpty()) {
            packageInfo.put(ConstVal.MODULE_NAME, this.getModuleName());
            packageInfo.put(ConstVal.ENTITY, this.joinPackage(this.getEntity()));
            packageInfo.put(ConstVal.MAPPER, this.joinPackage(this.getMapper()));
            packageInfo.put(ConstVal.XML, this.joinPackage(this.getXml()));
            packageInfo.put(ConstVal.SERVICE, this.joinPackage(this.getService()));
            packageInfo.put(ConstVal.SERVICE_IMPL, this.joinPackage(this.getServiceImpl()));
            packageInfo.put(ConstVal.CONTROLLER, this.joinPackage(this.getController()));
            packageInfo.put(ConstVal.PARENT, this.getParent());
//            if (injectionConfig != null) {
//                List<CustomFile> customFiles = injectionConfig.getCustomFiles();
//                for (CustomFile customFile : customFiles) {
//                    if (StringUtils.isNotBlank(customFile.getPackageName())) {
//                        String name = customFile.getShortName();
//                        if (StringUtils.isNotBlank(this.parent)
//                                && customFile.getPackageName().startsWith(this.parent)) {
//                            packageInfo.put(name, customFile.getPackageName());
//                        } else {
//                            packageInfo.put(name, this.joinPackage(customFile.getPackageName()));
//                        }
//                    }
//                }
//            }
        }
        return Collections.unmodifiableMap(this.packageInfo);
    }

    
    
}
