package io.github.bootystar.mybatisplus.enhancer.query.unmodifiable;

import io.github.bootystar.mybatisplus.enhancer.query.SqlTree;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * @author bootystar
 */
@Getter
public class SqlTreeU implements SqlTree {

    protected Collection<SqlConditionU> conditions;

    protected SqlTreeU child;

    public SqlTreeU(Collection<SqlConditionU> conditions, SqlTreeU child) {
        this.conditions = conditions == null ? null : Collections.unmodifiableCollection(new LinkedHashSet<>(conditions));
        this.child = child;
    }
}
