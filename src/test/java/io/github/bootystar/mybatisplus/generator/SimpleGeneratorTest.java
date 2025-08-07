//package io.github.bootystar.mybatisplus.generator;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import io.github.bootystar.mybatisplus.generator.generator.core.EnhanceGenerator;
//
///**
// * 简单的生成器测试类
// * 验证重构后的链式调用功能
// *
// * @author bootystar
// */
//public class SimpleGeneratorTest {
//
//    public static void main(String[] args) {
//        // 使用H2内存数据库进行测试
//        String url = "jdbc:h2:mem:testdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
//        String username = "sa";
//        String password = "";
//
//        System.out.println("开始测试链式调用配置...");
//        
//        try {
//            // 测试链式调用配置
//            EnhanceGenerator generator = GeneratorHelper.dynamicFieldGenerator(url, username, password)
//                    .controllerConfig(e -> e
//                            .enableRestStyle()
//                            .enableHyphenStyle()
//                            .baseUrl("/api/v1")
//                            .enableCrossOrigins()
//                    )
//                    .serviceConfig(e -> e
//                            .superServiceClass("com.baomidou.mybatisplus.extension.service.IService")
//                            .superServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")
//                            .enableServiceInterface()
//                    )
//                    .mapperConfig(e -> e
//                            .superClass("com.baomidou.mybatisplus.core.mapper.BaseMapper")
//                            .enableMapperAnnotation()
//                            .enableBaseResultMap()
//                    )
//                    .entityConfig(e -> e
//                            .superClass("java.io.Serializable")
//                            .enableLombok()
//                            .idType(IdType.ASSIGN_ID)
//                            .logicDeleteColumnName("deleted")
//                    )
//                    .dtoConfig(e -> e
//                            .package4DTO("dto")
//                            .enableFileOverride()
//                    )
//                    .voConfig(e -> e
//                            .package4VO("vo")
//                            .enableFileOverride()
//                    )
//                    .globalCustomConfig(e -> e
//                            .enableSwaggerModelWithAnnotation()
//                            .nowTime("2024-01-01 00:00:00")
//                    )
//                    .packageConfig(pkg -> pkg
//                            .parent("io.github.bootystar.test")
//                            .entity("entity")
//                            .mapper("mapper")
//                            .service("service")
//                            .controller("controller")
//                    )
//                    .globalConfig(global -> global
//                            .author("test-author")
//                            .outputDir(System.getProperty("user.dir") + "/target/generated-test-sources")
//                            .disableOpenDir()
//                    )
//                    .initialize();
//
//            System.out.println("✓ 链式调用配置测试成功！");
//            System.out.println("✓ 生成器配置完成，可以调用execute()方法生成代码");
//            
//        } catch (Exception e) {
//            System.err.println("✗ 链式调用配置测试失败:");
//            e.printStackTrace();
//        }
//
//        System.out.println("\n开始测试额外代码生成器...");
//        
//        try {
//            EnhanceGenerator generator2 = GeneratorHelper.extraCodeGenerator(url, username, password)
//                    .initialize()
//                    .packageConfig(pkg -> pkg.parent("io.github.bootystar.test"))
//                    .globalConfig(global -> global
//                            .author("test-author")
//                            .outputDir(System.getProperty("user.dir") + "/target/generated-test-sources")
//                            .disableOpenDir()
//                    );
//
//            System.out.println("✓ 额外代码生成器测试成功！");
//            
//        } catch (Exception e) {
//            System.err.println("✗ 额外代码生成器测试失败:");
//            e.printStackTrace();
//        }
//
//        System.out.println("\n开始测试增强生成器...");
//        
//        try {
//            EnhanceGenerator generator3 = GeneratorHelper.enhancerGenerator(url, username, password)
//                    .initialize()
//                    .packageConfig(pkg -> pkg.parent("io.github.bootystar.test"))
//                    .globalConfig(global -> global
//                            .author("test-author")
//                            .outputDir(System.getProperty("user.dir") + "/target/generated-test-sources")
//                            .disableOpenDir()
//                    );
//
//            System.out.println("✓ 增强生成器测试成功！");
//            
//        } catch (Exception e) {
//            System.err.println("✗ 增强生成器测试失败:");
//            e.printStackTrace();
//        }
//
//        System.out.println("\n所有测试完成！");
//        System.out.println("重构成功，现在可以使用以下方式调用：");
//        System.out.println("GeneratorHelper.dynamicFieldGenerator(url, username, password)");
//        System.out.println("  .controllerConfig(e -> e.enableRestStyle())");
//        System.out.println("  .serviceConfig(e -> e.enableServiceInterface())");
//        System.out.println("  .mapperConfig(e -> e.enableMapperAnnotation())");
//        System.out.println("  .entityConfig(e -> e.enableLombok())");
//        System.out.println("  .dtoConfig(e -> e.package4DTO(\"dto\"))");
//        System.out.println("  .voConfig(e -> e.package4VO(\"vo\"))");
//        System.out.println("  .strategyConfig(e -> e.entityBuilder().enableLombok())");
//        System.out.println("  .initialize()");
//        System.out.println("  .execute();");
//    }
//}
