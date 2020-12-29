package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
public class DashboardPage implements Page<Dashboard> {

    @NotNull
    @NonNull
    private Integer count;

    private List<Dashboard> data = new ArrayList<>();

    public DashboardPage() {
    }

    @NonNull
    @Override
    public Integer getCount() {
        return count;
    }

    @NonNull
    @Override
    public List<Dashboard> getData() {
        return data;
    }

    public void setCount(@NonNull Integer count) {
        this.count = count;
    }

    public void setData(List<Dashboard> data) {
        this.data = data;
    }
}
