package com.objectcomputing.cubeboard;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Timespan {

    DAILY,
    WEEKLY,
    MONTHLY;

    @JsonValue
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
