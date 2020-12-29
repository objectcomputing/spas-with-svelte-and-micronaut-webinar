package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

import java.math.BigDecimal;

@Introspected
public class Value extends Data {
    @Nullable
    private BigDecimal value;

    @Nullable
    private Integer period;

    public Value() {
    }

    @Nullable
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(@Nullable BigDecimal value) {
        this.value = value;
    }

    @Nullable
    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(@Nullable Integer period) {
        this.period = period;
    }
}
