package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.order.Ordered;
import javax.validation.constraints.NotBlank;

@Introspected
public class Dashboard implements Ordered {

    /**
     * The displayed dashboard name.
     */
    @NotBlank
    @NonNull
    private String name;

    @Nullable
    private String background;

    /**
     * A number used to order the dashboards when displaying them
     */
    private int order = 0;

    /**
     * A generated string used to refer to this dashboard in the API. For
     */
    @NotBlank
    @NonNull
    private String slug;

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
        setSlug(generateSlug(name));
    }

    private String generateSlug(String name) {
        return name.toLowerCase().trim().replaceAll(" ", "-");
    }

    @Nullable
    public String getBackground() {
        return background;
    }

    public void setBackground(@Nullable String background) {
        this.background = background;
    }

    @NonNull
    public String getSlug() {
        return slug;
    }

    public void setSlug(@NonNull String slug) {
        this.slug = slug;
    }
}
