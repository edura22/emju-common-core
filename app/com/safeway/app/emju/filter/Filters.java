package com.safeway.app.emju.filter;

import javax.inject.Inject;

import play.api.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.http.HttpFilters;

public class Filters implements HttpFilters {

    private final AccessLogFilter accessLogFilter;

    @Inject
    public Filters(final AccessLogFilter accessLogFilter) {
        this.accessLogFilter = accessLogFilter;
    }

    @Inject
    CORSFilter corsFilter;

    @Override
    public EssentialFilter[] filters() {
        return new EssentialFilter[] {
            corsFilter,
            accessLogFilter
        };
    }

}
