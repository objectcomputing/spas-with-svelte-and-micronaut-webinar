package com.objectcomputing.cubeboard;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class WidgetFetcherImpl implements WidgetFetcher {

    public static final String SLOT_LABEL = "" + 55L;

    private final List<Widget> widgets;

    public WidgetFetcherImpl() {
        List<Widget> widgets = new ArrayList<>();
        Widget widget = createSampleWidget();
        widgets.add(widget);
        this.widgets = widgets;
    }

    private Widget createSampleWidget() {
        Widget widget = new Widget();
        widget.setId(12L);
        widget.setDashboard("currency-exchange-rates");
        widget.setKind("counter");
        widget.setTitle("European Central Bank");
        widget.setRow(0);
        widget.setColumn(0);
        widget.setWidth(2);
        widget.setHeight(1);
        Slot slot = new Slot();
        slot.setLabel(SLOT_LABEL);
        slot.setSubtitle("USD / EUR");
        slot.setTimespan(Timespan.MONTHLY);
        widget.getSlots().add(slot);
        return widget;
    }

    @Override
    public List<Widget> findAll() {
        return widgets;
    }
}
