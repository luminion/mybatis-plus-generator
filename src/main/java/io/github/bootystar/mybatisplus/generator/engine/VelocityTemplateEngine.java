/*
 * Copyright (c) 2011-2025, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.bootystar.mybatisplus.generator.engine;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import io.github.bootystar.mybatisplus.generator.config.Configurer;
import io.github.bootystar.mybatisplus.generator.config.enums.TemplateLoadWay;
import io.github.bootystar.mybatisplus.generator.config.interfaces.ConstVal;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Velocity 模板引擎实现文件输出
 *
 * @author hubin
 * @since 2018-01-10
 */
public class VelocityTemplateEngine extends AbstractTemplateEngine {
    private VelocityEngine velocityEngine;

    {
        try {
            Class.forName("org.apache.velocity.util.DuckType");
        } catch (ClassNotFoundException e) {
            // velocity1.x的生成格式错乱 https://github.com/baomidou/generator/issues/5
            LOGGER.warn("Velocity 1.x is outdated, please upgrade to 2.x or later.");
        }
    }

    public VelocityTemplateEngine(Configurer configAdapter) {
        super(configAdapter);
        if (null == velocityEngine) {
            Properties p = new Properties();
            p.setProperty(Velocity.ENCODING_DEFAULT, ConstVal.UTF8);
            p.setProperty(Velocity.INPUT_ENCODING, ConstVal.UTF8);
            if (getConfigAdapter().getTemplateLoadWay().isFile()) {
                // 文件模板
                p.setProperty(ConstVal.VM_LOAD_PATH_KEY, ConstVal.VM_LOAD_PATH_VALUE);
                p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, StringPool.EMPTY);
                p.setProperty("file.resource.loader.unicode", StringPool.TRUE);
            } else {
                // 文本模板
                p.setProperty(Velocity.RESOURCE_LOADER, TemplateLoadWay.STRING.getValue());
            }
            velocityEngine = new VelocityEngine(p);
        }
    }

    @Override
    public String writer(Map<String, Object> objectMap, String templateName, String templateString) {
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(new VelocityContext(objectMap), writer, templateName, templateString);
        return writer.toString();
    }

    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, File outputFile) throws Exception {
        Template template = velocityEngine.getTemplate(this.templateFilePath(templatePath), ConstVal.UTF8);
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             OutputStreamWriter ow = new OutputStreamWriter(fos, ConstVal.UTF8);
             BufferedWriter writer = new BufferedWriter(ow)) {
            template.merge(new VelocityContext(objectMap), writer);
        }
        LOGGER.debug("模板:{};  文件:{}", templatePath, outputFile);
    }


    @Override
    public String templateFilePath(String filePath) {
        final String dotVm = ".vm";
        return filePath.endsWith(dotVm) ? filePath : filePath + dotVm;
    }
}
