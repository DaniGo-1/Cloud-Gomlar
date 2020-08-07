package com.bytesw.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HeaderFilterPre extends ZuulFilter {
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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String header = request.getHeader("farmacy");
        if(header == null || header.isEmpty()){
            throw new ZuulException("Farmacia no encontrada", 401, "Farmacia no encontrada");
        }
        System.out.println("Request Method: " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
        return null;
    }
}
