package com.objectcomputing.cubeboard;

import com.objectcomputing.cubeboard.versioning.ApiVersion;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;

@Controller("/api")
public class WidgetsController {

    private final WidgetFetcher widgetFetcher;

    public WidgetsController(WidgetFetcher widgetFetcher) {
        this.widgetFetcher = widgetFetcher;
    }

    /**
     *
     * @return Get a collection of the user's widget
     */
    @Version(ApiVersion.V1)
    @Get("/widgets")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public WidgetPage index() {
        List<Widget> widgetList = widgetFetcher.findAll();
        WidgetPage page = new WidgetPage();
        page.setCount(widgetList.size());
        page.setData(widgetList);
        return page;
    }
}
