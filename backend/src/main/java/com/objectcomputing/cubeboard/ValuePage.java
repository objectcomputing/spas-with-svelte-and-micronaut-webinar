package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
public class ValuePage implements Page<Value> {
    @NotNull
    @NonNull
    private Integer count;

    @NonNull
    @NotNull
    private List<Value> data = new ArrayList<>();

    public ValuePage() {
    }

    @Override
    @NonNull
    public Integer getCount() {
        return count;
    }

    public void setCount(@NonNull Integer count) {
        this.count = count;
    }

    @Override
    @NonNull
    public List<Value> getData() {
        return data;
    }

    public void setData(List<Value> data) {
        this.data = data;
    }
}
