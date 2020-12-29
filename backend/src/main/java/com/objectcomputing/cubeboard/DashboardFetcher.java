package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.annotation.DefaultImplementation;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@DefaultImplementation(DashboardFetcherImpl.class)
public interface DashboardFetcher {

    @NonNull
    List<Dashboard> findAll();

    @NonNull
    Optional<Dashboard> findBySlug(@NonNull @NotBlank String slug);
}
