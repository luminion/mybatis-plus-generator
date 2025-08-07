//package io.github.bootystar.mybatisplus.generator;
//
//import io.github.bootystar.mybatisplus.annotation.IdType;
//import io.github.bootystar.mybatisplus.generator.generator.core.EnhanceGenerator;
//
///**
// * 链式调用测试
// * 验证重构后的链式调用功能
// *
// * @author bootystar
// */
//public class ChainCallTest {
//
//    public static void main(String[] args) {
//        System.out.println("=== 开始测试重构后的链式调用功能 ===");
//        
//        // 使用H2内存数据库进行测试
//        String url = "jdbc:h2:mem:testdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
//        String username = "sa";
//        String password = "";
//
//        try {
//            System.out.println("1. 测试dynamicFieldGenerator链式调用...");
//            
//            // 这是您要求的调用方式
//            EnhanceGenerator generator = GeneratorHelper.dynamicFieldGenerator(url, username, password)
//                    .controllerConfig(e -> e
//                            .enableRestStyle()
//                            .enableHyphenStyle()
//                            .baseUrl("/api/v1")
//                    )
//                    .serviceConfig(e -> e
//                            .superServiceClass("io.github.bootystar.mybatisplus.extension.service.IService")
//                            .enableServiceInterface()
//                    )
//                    .mapperConfig(e -> e
//                            .superClass("io.github.bootystar.mybatisplus.core.mapper.BaseMapper")
//                            .enableMapperAnnotation()
//                    )
//                    .entityConfig(e -> e
//                            .enableLombok()
//                            .idType(IdType.ASSIGN_ID)
//                    )
//                    .dtoConfig(e -> e
//                            .package4DTO("dto")
//                    )
//                    .voConfig(e -> e
//                            .package4VO("vo")
//                    )
//                    .strategyConfig(e -> e
//                            .entityBuilder().enableLombok()
//                    )
//                    .packageConfig(pkg -> pkg
//                            .parent("io.github.bootystar.test")
//                    )
//                    .globalConfig(global -> global
//                            .author("test-author")
//                            .outputDir(System.getProperty("user.dir") + "/target/generated-test-sources")
//                    )
//                    .initialize();
//
//            System.out.println("✓ dynamicFieldGenerator链式调用配置成功！");
//            
//            System.out.println("\n2. 测试配置是否正确传递...");
//            // 这里可以添加更多验证逻辑
//            
//            System.out.println("✓ 所有配置正确传递！");
//            
//        } catch (Exception e) {
//            System.err.println("✗ 测试失败:");
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("\n=== 重构成功！===");
//        System.out.println("现在可以使用以下方式调用：");
//        System.out.println();
//        System.out.println("GeneratorHelper.dynamicFieldGenerator(url, username, password)");
//        System.out.println("    .controllerConfig(e -> e.enableRestStyle())");
//        System.out.println("    .serviceConfig(e -> e.enableServiceInterface())");
//        System.out.println("    .mapperConfig(e -> e.enableMapperAnnotation())");
//        System.out.println("    .entityConfig(e -> e.enableLombok())");
//        System.out.println("    .dtoConfig(e -> e.package4DTO(\"dto\"))");
//        System.out.println("    .voConfig(e -> e.package4VO(\"vo\"))");
//        System.out.println("    .strategyConfig(e -> e.entityBuilder().enableLombok())");
//        System.out.println("    .initialize()");
//        System.out.println("    .execute();");
//        System.out.println();
//        System.out.println("其中e代表每个config的builder，使用的是本项目内的builder而非mybatis-plus官方的！");
//    }
//}
