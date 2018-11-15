package io.uslab.edgeservice.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ZuulPreFilter extends com.netflix.zuul.ZuulFilter {

    Logger logger = LoggerFactory.getLogger(ZuulPreFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
       // return true;
        RequestContext ctx = RequestContext.getCurrentContext();
        return !ctx.containsKey("FORWARD_TO_KEY") // a filter has already forwarded
                && !ctx.containsKey("SERVICE_ID_KEY");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("The context path test --> "+ request.getContextPath());
        System.out.println("MY TEST OF ZUUL...");
        System.out.println("Sandeep Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
        if (request.getParameter("sample") != null) {
            // put the serviceId in `RequestContext`
            ctx.put("SERVICE_ID_KEY", request.getParameter("XYZ-13579-999"));
        }
        /*
        HttpServletRequest request = RequestContext.getCurrentContext()
                .getRequest();
        logger.info("The context path test --> "+ request.getContextPath());*

        */return null;
    }
}
