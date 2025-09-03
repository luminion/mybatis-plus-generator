package com.example;

/**
 * @author bootystar
 */
public class R<T> {
    private T data;
    public R(T data) {
    }
    public static <T> R<T> of(T data) {
        return new R<>(data);
    }
}
