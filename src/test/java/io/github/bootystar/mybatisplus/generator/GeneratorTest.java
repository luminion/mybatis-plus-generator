//package io.github.bootystar.mybatisplus.generator;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import io.github.bootystar.mybatisplus.generator.generator.core.EnhanceGenerator;
//
///**
// * 生成器测试类
// * 验证重构后的链式调用功能
// *
// * @author bootystar
// */
//public class GeneratorTest {
//
//    private String url;
//    private String username;
//    private String password;
//
//    public void setUp() {
//        // 使用H2内存数据库进行测试
//        url = "jdbc:h2:mem:testdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
//        username = "sa";
//        password = "";
//    }
//
//    public void testChainedConfiguration() {
//        try {
//            EnhanceGenerator generator = GeneratorHelper.dynamicFieldGenerator(url, username, password)
//                    .controllerConfig(e -> e
//                            .enableRestStyle()
//                            .enableHyphenStyle()
//                            .baseUrl("/api/v1")
//                            .enableCrossOrigins()
//                            .enableJakartaApi()
//                            .enableAutoWired()
//                            .enableRestful()
//                            .disablePathVariable()
//                            .disableRequestBody()
//                            .disableValidated()
//                            .disablePostQuery()
//                    )
//                    .serviceConfig(e -> e
//                            .superServiceClass("com.baomidou.mybatisplus.extension.service.IService")
//                            .superServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")
//                            .enableServiceInterface()
//                            .formatServiceFileName("%sService")
//                            .formatServiceImplFileName("%sServiceImpl")
//                            .enableFileOverride()
//                            .disableInsert()
//                            .disableUpdate()
//                            .disableDelete()
//                            .disableSelect()
//                            .disableOverrideMethods()
//                    )
//                    .mapperConfig(e -> e
//                            .superClass("com.baomidou.mybatisplus.core.mapper.BaseMapper")
//                            .enableMapperAnnotation()
//                            .enableBaseResultMap()
//                            .enableBaseColumnList()
//                            .formatMapperFileName("%sMapper")
//                            .formatXmlFileName("%sMapper")
//                            .enableFileOverride()
//                            .sortColumn("id", true)
//                            .sortColumn("create_time", true)
//                            .sortColumnClear()
//                    )
//                    .entityConfig(e -> e
//                            .superClass("java.io.Serializable")
//                            .enableSerialVersionUID()
//                            .enableChainModel()
//                            .enableLombok()
//                            .enableTableFieldAnnotation()
//                            .enableRemoveIsPrefix()
//                            .enableActiveRecord()
//                            .enableColumnConstant()
//                            .versionColumnName("version")
//                            .logicDeleteColumnName("deleted")
//                            .idType(IdType.ASSIGN_ID)
//                            .enableFileOverride()
//                    )
//                    .dtoConfig(e -> e
//                            .package4DTO("dto")
//                            .path4DTO("/test/dto")
//                            .enableFileOverride()
//                            .disableInsert()
//                            .disableUpdate()
//                            .disableSelect()
//                            .disableImport()
//                            .disableExport()
//                            .editExcludeColumns("create_time", "update_time")
//                            .useFastExcel()
//                            .useMapSelectDTO()
//                    )
//                    .voConfig(e -> e
//                            .package4VO("vo")
//                            .path4VO("/test/vo")
//                            .enableFileOverride()
//                            .disableExport()
//                    )
//                    .globalCustomConfig(e -> e
//                            .enableSwaggerModelWithAnnotation()
//                            .enableSwaggerAnnotationWithUUID()
//                            .swaggerUUID("test-uuid")
//                            .disableExtraClassLinkComment()
//                            .nowTime("2024-01-01 00:00:00")
//                            .disableOverrideMethods()
//                    )
//                    .packageConfig(pkg -> pkg
//                            .parent("io.github.bootystar.test")
//                            .entity("entity")
//                            .mapper("mapper")
//                            .service("service")
//                            .serviceImpl("service.impl")
//                            .controller("controller")
//                            .xml("mapper")
//                    )
//                    .globalConfig(global -> global
//                            .author("test-author")
//                            .outputDir(System.getProperty("user.dir") + "/target/generated-test-sources")
//                            .disableOpenDir()
//                    )
//                    .initialize();
//
//            System.out.println("链式调用配置测试成功！");
//            System.out.println("生成器配置完成，可以调用execute()方法生成代码");
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("链式调用配置测试失败", e);
//        }
//    }
//
//    public void testExtraCodeGenerator() {
//        try {
//            EnhanceGenerator generator = GeneratorHelper.extraCodeGenerator(url, username, password)
//                    .initialize()
//                    .packageConfig(pkg -> pkg.parent("io.github.bootystar.test"))
//                    .globalConfig(global -> global
//                            .author("test-author")
//                            .outputDir(System.getProperty("user.dir") + "/target/generated-test-sources")
//                            .disableOpenDir()
//                    );
//
//            System.out.println("额外代码生成器测试成功！");
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("额外代码生成器测试失败", e);
//        }
//    }
//
//    public void testEnhancerGenerator() {
//        try {
//            EnhanceGenerator generator = GeneratorHelper.enhancerGenerator(url, username, password)
//                    .initialize()
//                    .packageConfig(pkg -> pkg.parent("io.github.bootystar.test"))
//                    .globalConfig(global -> global
//                            .author("test-author")
//                            .outputDir(System.getProperty("user.dir") + "/target/generated-test-sources")
//                            .disableOpenDir()
//                    );
//
//            System.out.println("增强生成器测试成功！");
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("增强生成器测试失败", e);
//        }
//    }
//
//    /**
//     * 主方法，用于测试链式调用
//     */
//    public static void main(String[] args) {
//        GeneratorTest test = new GeneratorTest();
//        test.setUp();
//
//        System.out.println("开始测试链式调用配置...");
//        test.testChainedConfiguration();
//
//        System.out.println("开始测试额外代码生成器...");
//        test.testExtraCodeGenerator();
//
//        System.out.println("开始测试增强生成器...");
//        test.testEnhancerGenerator();
//
//        System.out.println("所有测试完成！");
//    }
//}
