package com.example;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author bootystar
 */
public class P<T> {
    private T data;

    public P(IPage<T> data) {
    }

    public static <T> P<T> of(IPage page) {
        return new P(page);
    }
}
