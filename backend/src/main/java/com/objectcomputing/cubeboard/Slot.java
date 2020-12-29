package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;

@Introspected
public class Slot {
    /**
     * The descriptive text appearing next to the data value.
     */
    @Nullable
    private String subtitle;

    /**
     * The time period this slot displays. Only applicable in counters and graphs.
     */
    @Nullable
    private Timespan timespan;

    /**
     * The color with which data is displayed in HTML notation, eg. #C11F70. Not applicable in timelines.
     */
    @Nullable
    private String color;

    /**
     * The label of the data source connected to this slot.
     */
    @NonNull
    @NotBlank
    private String label;

    @Nullable
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(@Nullable String subtitle) {
        this.subtitle = subtitle;
    }

    @Nullable
    public Timespan getTimespan() {
        return timespan;
    }

    public void setTimespan(@Nullable Timespan timespan) {
        this.timespan = timespan;
    }

    @Nullable
    public String getColor() {
        return color;
    }

    public void setColor(@Nullable String color) {
        this.color = color;
    }

    public Slot() {
    }

    public Slot(@NonNull @NotBlank String label) {
        this.label = label;
    }

    @NonNull
    public String getLabel() {
        return label;
    }

    public void setLabel(@NonNull String label) {
        this.label = label;
    }
}
