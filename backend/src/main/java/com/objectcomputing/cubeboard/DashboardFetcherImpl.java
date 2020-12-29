package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Singleton
public class DashboardFetcherImpl implements DashboardFetcher {
    private final Dashboard dashboard;

    public DashboardFetcherImpl() {
        Dashboard dashboard = new Dashboard();
        dashboard.setName("Currency Exchange rates");
        dashboard.setBackground("wood");
        this.dashboard = dashboard;
    }

    @Override
    @NonNull
    public List<Dashboard> findAll() {
        return Collections.singletonList(dashboard);
    }

    @Override
    @NonNull
    public Optional<Dashboard> findBySlug(@NonNull @NotBlank String slug) {
        if (slug.equals(dashboard.getSlug())) {
            return Optional.of(dashboard);
        }
        return Optional.empty();
    }
}
