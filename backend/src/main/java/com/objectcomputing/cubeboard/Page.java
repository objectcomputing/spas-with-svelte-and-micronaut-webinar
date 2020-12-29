package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.List;

public interface Page<T> {

    @NonNull
    Integer getCount();

    @NonNull
    List<T> getData();
}
