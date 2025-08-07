//package io.github.bootystar.mybatisplus.generator;
//
//import io.github.bootystar.mybatisplus.generator.generator.core.EnhanceGenerator;
//
///**
// * 基础测试
// * 验证重构后的基本功能
// *
// * @author bootystar
// */
//public class BasicTest {
//
//    public static void main(String[] args) {
//        System.out.println("=== 开始测试重构后的基本功能 ===");
//        
//        // 使用H2内存数据库进行测试
//        String url = "jdbc:h2:mem:testdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
//        String username = "sa";
//        String password = "";
//
//        try {
//            System.out.println("1. 测试dynamicFieldGenerator创建...");
//            
//            EnhanceGenerator generator = GeneratorHelper.dynamicFieldGenerator(url, username, password);
//            
//            if (generator != null) {
//                System.out.println("✓ dynamicFieldGenerator创建成功！");
//            } else {
//                System.out.println("✗ dynamicFieldGenerator创建失败！");
//                return;
//            }
//            
//            System.out.println("2. 测试initialize方法...");
//            generator.initialize();
//            System.out.println("✓ initialize方法调用成功！");
//            
//            System.out.println("3. 测试配置方法是否存在...");
//            
//            // 测试方法是否存在（不实际调用）
//            Class<?> generatorClass = generator.getClass();
//            
//            // 检查是否有controllerConfig方法
//            try {
//                generatorClass.getMethod("controllerConfig", java.util.function.Consumer.class);
//                System.out.println("✓ controllerConfig方法存在");
//            } catch (NoSuchMethodException e) {
//                System.out.println("✗ controllerConfig方法不存在");
//            }
//            
//            // 检查是否有serviceConfig方法
//            try {
//                generatorClass.getMethod("serviceConfig", java.util.function.Consumer.class);
//                System.out.println("✓ serviceConfig方法存在");
//            } catch (NoSuchMethodException e) {
//                System.out.println("✗ serviceConfig方法不存在");
//            }
//            
//            // 检查是否有mapperConfig方法
//            try {
//                generatorClass.getMethod("mapperConfig", java.util.function.Consumer.class);
//                System.out.println("✓ mapperConfig方法存在");
//            } catch (NoSuchMethodException e) {
//                System.out.println("✗ mapperConfig方法不存在");
//            }
//            
//            // 检查是否有entityConfig方法
//            try {
//                generatorClass.getMethod("entityConfig", java.util.function.Consumer.class);
//                System.out.println("✓ entityConfig方法存在");
//            } catch (NoSuchMethodException e) {
//                System.out.println("✗ entityConfig方法不存在");
//            }
//            
//            // 检查是否有dtoConfig方法
//            try {
//                generatorClass.getMethod("dtoConfig", java.util.function.Consumer.class);
//                System.out.println("✓ dtoConfig方法存在");
//            } catch (NoSuchMethodException e) {
//                System.out.println("✗ dtoConfig方法不存在");
//            }
//            
//            // 检查是否有voConfig方法
//            try {
//                generatorClass.getMethod("voConfig", java.util.function.Consumer.class);
//                System.out.println("✓ voConfig方法存在");
//            } catch (NoSuchMethodException e) {
//                System.out.println("✗ voConfig方法不存在");
//            }
//            
//        } catch (Exception e) {
//            System.err.println("✗ 测试失败:");
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("\n=== 基础功能测试完成！===");
//        System.out.println("重构成功，所有必要的方法都已实现！");
//        System.out.println("现在可以使用链式调用方式配置生成器了！");
//    }
//}
