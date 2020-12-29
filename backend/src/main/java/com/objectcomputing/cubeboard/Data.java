package com.objectcomputing.cubeboard;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Data {
    private long timestamp;

    public Data() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
