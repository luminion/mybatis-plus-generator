package io.github.bootystar.mybatisplus.enhancer.core;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * mapper
 *
 * @author bootystar
 */
public interface DynamicMapper<T, V> extends BaseMapper<T> {

    List<V> listByDTO(Object s, IPage<V> page);

}
