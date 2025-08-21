package io.github.bootystar.mybatisplus.generator;

import io.github.bootystar.mybatisplus.generator.config.po.ClassPayload;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author bootystar
 */
public class ClassPayloadTest {
    
    @Test
    void test1(){
        ClassPayload classPayload = new ClassPayload(Map.class);
        String clazz = classPayload.classReturnGenericTypeStr();
        System.out.println(clazz);
    }
}
