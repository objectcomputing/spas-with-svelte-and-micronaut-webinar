package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Introspected
public class BigDecimalData {
    private long timestamp;

    @NonNull
    @NotNull
    private BigDecimal value;

    public BigDecimalData() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @NonNull
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(@NonNull BigDecimal value) {
        this.value = value;
    }
}
