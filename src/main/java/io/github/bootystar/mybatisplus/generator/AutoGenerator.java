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
package io.github.bootystar.mybatisplus.generator;

import io.github.bootystar.mybatisplus.generator.config.ConfigAdapter;
import io.github.bootystar.mybatisplus.generator.config.OutputFile;
import io.github.bootystar.mybatisplus.generator.config.core.InjectionConfig;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.engine.AbstractTemplateEngine;
import io.github.bootystar.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.util.List;

/**
 * 生成文件
 *
 * @author YangHu, tangguo, hubin
 * @since 2016-08-30
 */
@Getter
@Slf4j
public class AutoGenerator {

    /**
     * 配置信息
     */
    protected ConfigAdapter config;
    
    public AutoGenerator(ConfigAdapter config) {
        //这个是必须参数,其他都是可选的,后续去除默认构造更改成final
        this.config = config;
    }

    /**
     * 生成代码
     */
    public void execute() {
        this.execute(null);
    }

    /**
     * 生成代码
     *
     * @param templateEngine 模板引擎
     */
    public void execute(AbstractTemplateEngine templateEngine) {
        log.debug("==========================准备生成文件...==========================");
        // 初始化配置
        if (null == config) {
            throw new IllegalArgumentException("请先指定配置信息");
        }
        if (null == templateEngine) {
            templateEngine = new VelocityTemplateEngine(config);
        }
        // 模板引擎初始化执行文件输出
        templateEngine.batchOutput().open();
        log.debug("==========================文件生成完成！！！==========================");
        String banner = "\n" +
                ".------..------..------..------..------..------..------..------..------.\n" +
                "|B.--. ||O.--. ||O.--. ||T.--. ||Y.--. ||S.--. ||T.--. ||A.--. ||R.--. |\n" +
                "| :(): || :/\\: || :/\\: || :/\\: || (\\/) || :/\\: || :/\\: || (\\/) || :(): |\n" +
                "| ()() || :\\/: || :\\/: || (__) || :\\/: || :\\/: || (__) || :\\/: || ()() |\n" +
                "| '--'B|| '--'O|| '--'O|| '--'T|| '--'Y|| '--'S|| '--'T|| '--'A|| '--'R|\n" +
                "`------'`------'`------'`------'`------'`------'`------'`------'`------'\n";
        System.out.println(banner);
        System.out.println("execute success! check files in following folder:");
        String path = config.getPathInfo().get(OutputFile.parent);
        System.out.println(new File(path).getAbsolutePath());
    }
    
    protected List<TableInfo> getAllTableInfoList() {
        return config.getTableInfo();
    }

    public InjectionConfig getInjectionConfig() {
        return config.getInjectionConfig();
    }

}
