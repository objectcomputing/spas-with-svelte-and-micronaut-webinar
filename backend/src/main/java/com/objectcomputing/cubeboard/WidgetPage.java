package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
public class WidgetPage implements Page<Widget> {
    @NonNull
    @NotNull
    private Integer count;

    @NonNull
    @NotNull
    private List<Widget> data = new ArrayList<>();

    @NonNull
    @Override
    public Integer getCount() {
        return count;
    }

    @NonNull
    @Override
    public List<Widget> getData() {
        return data;
    }

    public void setCount(@NonNull Integer count) {
        this.count = count;
    }

    public void setData(@NonNull List<Widget> data) {
        this.data = data;
    }
}
