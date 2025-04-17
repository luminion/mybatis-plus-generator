package io.github.bootystar.mybatisplus.generator.util;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.bootystar.mybatisplus.generator.info.MethodInfo;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author bootystar
 */
public class GenReflectHelper {

    /**
     * lambda方法信息
     *
     * @param methodReference lambda方法引用
     * @param parameterClass  参数类型
     * @return {@link MethodInfo }
     */
    public static MethodInfo lambdaMethodInfo(SFunction<?, ?> methodReference, Class<?> parameterClass) {
        String methodName = "", className = "";
        try {
            Method lambdaMethod = methodReference.getClass().getDeclaredMethod("writeReplace");
            lambdaMethod.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) lambdaMethod.invoke(methodReference);
            className = serializedLambda.getImplClass().replace("/", ".");
            methodName = serializedLambda.getImplMethodName();
            Class<?> methodClass = Class.forName(className);
            try {
                Method returnMethod = methodClass.getMethod(methodName, parameterClass);
                Class<?> returnType = returnMethod.getReturnType();
                int modifiers = returnMethod.getModifiers();
                if (!returnType.equals(methodClass) || !Modifier.isPublic(modifiers)) {
                    throw new NoSuchMethodException("no public method found which return instance of class itself");
                }
                return new MethodInfo(returnMethod);
            } catch (Exception e) {
                Constructor<?> constructor = methodClass.getConstructor(parameterClass);
                return new MethodInfo(constructor);
            }
        } catch (Exception e) {
            String msg = String.format("can't find constructor or method with parameter[%s] source:%s.%s() ", parameterClass.getName(), className, methodName);
            throw new IllegalStateException(msg);
        }
    }
}

