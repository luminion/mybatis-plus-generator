package io.github.bootystar.mybatisplus.generator.config;

import com.baomidou.mybatisplus.generator.ITemplate;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.util.Map;

/**
 * @author bootystar
 */
public interface IReflectiveTemplate extends ITemplate {

    @Override
    default Map<String, Object> renderData(TableInfo tableInfo) {
        // todo 反射属性为map
       return null;
    }
}
