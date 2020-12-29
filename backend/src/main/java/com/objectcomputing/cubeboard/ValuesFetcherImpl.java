package com.objectcomputing.cubeboard;

import edu.umd.cs.findbugs.annotations.NonNull;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class ValuesFetcherImpl implements ValuesFetcher {
    List<BigDecimal> sampleValues = Arrays.asList(
            new BigDecimal("0.85171616"),
            new BigDecimal("0.85484695"),
            new BigDecimal("0.85106385"),
            new BigDecimal("0.8483923"),
            new BigDecimal("0.8475294"),
            new BigDecimal("0.8478169"),
            new BigDecimal("0.8499788"),
            new BigDecimal("0.84961766"),
            new BigDecimal("0.8478169"),
            new BigDecimal("0.8497621"),
            new BigDecimal("0.8525149"),
            new BigDecimal("0.850919"),
            new BigDecimal("0.85411686"),
            new BigDecimal("0.8545548"),
            new BigDecimal("0.856898"),
            new BigDecimal("0.8595496"),
            new BigDecimal("0.85873765"),
            new BigDecimal("0.8552857"),
            new BigDecimal("0.85178876"),
            new BigDecimal("0.8483923"),
            new BigDecimal("0.8450942"),
            new BigDecimal("0.8476731"),
            new BigDecimal("0.84253097"),
            new BigDecimal("0.84090143"),
            new BigDecimal("0.84203434"),
            new BigDecimal("0.8435971"),
            new BigDecimal("0.8439531"),
            new BigDecimal("0.8494012"),
            new BigDecimal("0.84853625"),
            new BigDecimal("0.8457375"),
            new BigDecimal("0.8444519"),
            new BigDecimal("0.84652495"),
            new BigDecimal("0.84309924"),
            new BigDecimal("0.8342371"),
            new BigDecimal("0.83752096"),
            new BigDecimal("0.8392783"),
            new BigDecimal("0.8470269"),
            new BigDecimal("0.84824836"),
            new BigDecimal("0.8464534"),
            new BigDecimal("0.8440955"),
            new BigDecimal("0.84968984"),
            new BigDecimal("0.8438819"),
            new BigDecimal("0.8380122"),
            new BigDecimal("0.8399126"),
            new BigDecimal("0.8436683"),
            new BigDecimal("0.84652495"),
            new BigDecimal("0.8450942"),
            new BigDecimal("0.84954554"),
            new BigDecimal("0.84868026"),
            new BigDecimal("0.8501232"),
            new BigDecimal("0.8462385"),
            new BigDecimal("0.8443807"),
            new BigDecimal("0.8435971"),
            new BigDecimal("0.8499788")
    );

    @NonNull
    @Override
    public List<Value> findByLabel(@NonNull @NotBlank String label) throws NoSuchLabelException {
        if (!label.equalsIgnoreCase(WidgetFetcherImpl.SLOT_LABEL)) {
            throw new NoSuchLabelException();
        }
        List<Value> l = new ArrayList<>();

        Instant day = Instant.now();
        for (int i = 0; i < 31; i++) {

            Value data = new Value();
            data.setValue(sampleValues.get(i));
            data.setTimestamp(day.getEpochSecond());
            System.out.println(day.toString());
            data.setPeriod(i);
            l.add(data);
            day = day.minus( 1, ChronoUnit.DAYS);
        }
        return l;
    }
}
