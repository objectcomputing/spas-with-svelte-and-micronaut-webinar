package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.annotation.DefaultImplementation;

import javax.validation.constraints.NotBlank;
import java.util.List;

@DefaultImplementation(ValuesFetcherImpl.class)
public interface ValuesFetcher {
    @NonNull
    List<Value> findByLabel(@NonNull @NotBlank String label) throws NoSuchLabelException;
}
