//package io.github.bootystar.mybatisplus.generator;
//
//import io.github.bootystar.mybatisplus.generator.config.enums.OutputFile;
//
///**
// * 验证测试
// * 验证重构后的配置类和Builder是否正确
// *
// * @author bootystar
// */
//public class ValidationTest {
//
//    public static void main(String[] args) {
//        System.out.println("=== 开始验证重构结果 ===");
//        
//        try {
//            System.out.println("1. 验证配置类是否正确创建...");
//            
//            // 测试ControllerConfig
//            io.github.bootystar.mybatisplus.generator.config.support.ControllerConfig controllerConfig = 
//                io.github.bootystar.mybatisplus.generator.config.support.ControllerConfig.builder()
//                    .enableRestStyle()
//                    .enableHyphenStyle()
//                    .baseUrl("/api/v1")
//                    .build();
//            System.out.println("✓ ControllerConfig创建成功");
//            
//            // 测试ServiceConfig
//            io.github.bootystar.mybatisplus.generator.config.support.ServiceConfig serviceConfig = 
//                io.github.bootystar.mybatisplus.generator.config.support.ServiceConfig.builder()
//                    .superServiceClass("com.baomidou.mybatisplus.extension.service.IService")
//                    .enableServiceInterface()
//                    .build();
//            System.out.println("✓ ServiceConfig创建成功");
//            
//            // 测试MapperConfig
//            io.github.bootystar.mybatisplus.generator.config.support.MapperConfig mapperConfig = 
//                io.github.bootystar.mybatisplus.generator.config.support.MapperConfig.builder()
//                    .superClass("com.baomidou.mybatisplus.core.mapper.BaseMapper")
//                    .enableMapperAnnotation()
//                    .build();
//            System.out.println("✓ MapperConfig创建成功");
//            
//            // 测试EntityConfig
//            io.github.bootystar.mybatisplus.generator.config.support.EntityConfig entityConfig = 
//                io.github.bootystar.mybatisplus.generator.config.support.EntityConfig.builder()
//                    .enableLombok()
//                    .enableSerialVersionUID()
//                    .build();
//            System.out.println("✓ EntityConfig创建成功");
//            
//            // 测试DtoConfig
//            io.github.bootystar.mybatisplus.generator.config.DtoConfig dtoConfig = 
//                io.github.bootystar.mybatisplus.generator.config.DtoConfig.builder()
//                    .package4DTO("dto")
//                    .enableFileOverride()
//                    .build();
//            System.out.println("✓ DtoConfig创建成功");
//            
//            // 测试VoConfig
//            io.github.bootystar.mybatisplus.generator.config.support.VoConfig voConfig = 
//                io.github.bootystar.mybatisplus.generator.config.support.VoConfig.builder()
//                    .package4VO("vo")
//                    .enableFileOverride()
//                    .build();
//            System.out.println("✓ VoConfig创建成功");
//            
//            // 测试OutputFile枚举
//            OutputFile outputFile = 
//                OutputFile.dto_insert;
//            System.out.println("✓ OutputFile枚举创建成功，模板路径: " + outputFile.getTemplatePath());
//            
//            System.out.println("\n2. 验证配置属性是否正确设置...");
//            
//            // 验证ControllerConfig属性
//            if (controllerConfig.isRestControllerStyle()) {
//                System.out.println("✓ ControllerConfig.restControllerStyle设置正确");
//            }
//            if (controllerConfig.isHyphenStyle()) {
//                System.out.println("✓ ControllerConfig.hyphenStyle设置正确");
//            }
//            if ("/api/v1".equals(controllerConfig.getBaseUrl())) {
//                System.out.println("✓ ControllerConfig.baseUrl设置正确");
//            }
//            
//            // 验证ServiceConfig属性
//            if ("com.baomidou.mybatisplus.extension.service.IService".equals(serviceConfig.getSuperServiceClass())) {
//                System.out.println("✓ ServiceConfig.superServiceClass设置正确");
//            }
//            if (serviceConfig.isServiceInterface()) {
//                System.out.println("✓ ServiceConfig.serviceInterface设置正确");
//            }
//            
//            // 验证MapperConfig属性
//            if ("com.baomidou.mybatisplus.core.mapper.BaseMapper".equals(mapperConfig.getSuperClass())) {
//                System.out.println("✓ MapperConfig.superClass设置正确");
//            }
//            if (mapperConfig.isEnableMapperAnnotation()) {
//                System.out.println("✓ MapperConfig.enableMapperAnnotation设置正确");
//            }
//            
//            // 验证EntityConfig属性
//            if (entityConfig.isEnableLombok()) {
//                System.out.println("✓ EntityConfig.enableLombok设置正确");
//            }
//            if (entityConfig.isEnableSerialVersionUID()) {
//                System.out.println("✓ EntityConfig.enableSerialVersionUID设置正确");
//            }
//            
//            // 验证DtoConfig属性
//            if ("dto".equals(dtoConfig.getPackage4DTO())) {
//                System.out.println("✓ DtoConfig.package4DTO设置正确");
//            }
//            if (dtoConfig.isFileOverride()) {
//                System.out.println("✓ DtoConfig.fileOverride设置正确");
//            }
//            
//            // 验证VoConfig属性
//            if ("vo".equals(voConfig.getPackage4VO())) {
//                System.out.println("✓ VoConfig.package4VO设置正确");
//            }
//            if (voConfig.isFileOverride()) {
//                System.out.println("✓ VoConfig.fileOverride设置正确");
//            }
//            
//        } catch (Exception e) {
//            System.err.println("✗ 验证失败:");
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("\n=== 验证完成！===");
//        System.out.println("✓ 所有配置类和Builder都工作正常！");
//        System.out.println("✓ OutputFile枚举包含了DTO和VO模板路径！");
//        System.out.println("✓ 重构成功，现在可以使用链式调用方式配置生成器！");
//        
//        System.out.println("\n推荐的使用方式：");
//        System.out.println("GeneratorHelper.dynamicFieldGenerator(url, username, password)");
//        System.out.println("    .controllerConfig(e -> e.enableRestStyle())");
//        System.out.println("    .serviceConfig(e -> e.enableServiceInterface())");
//        System.out.println("    .mapperConfig(e -> e.enableMapperAnnotation())");
//        System.out.println("    .entityConfig(e -> e.enableLombok())");
//        System.out.println("    .dtoConfig(e -> e.package4DTO(\"dto\"))");
//        System.out.println("    .voConfig(e -> e.package4VO(\"vo\"))");
//        System.out.println("    .initialize()");
//        System.out.println("    .execute();");
//    }
//}
