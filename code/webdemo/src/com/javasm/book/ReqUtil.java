package com.javasm.book;

import javax.servlet.http.HttpServletRequest;

public class ReqUtil {
    private HttpServletRequest req;

    public ReqUtil(HttpServletRequest req) {
        this.req = req;
    }

    public String strParam(String s){
        return req.getParameter(s);
    }

    public Integer intParam(String s){
        Integer attr = null;
        String attrStr = req.getParameter("attribution");
        if(attrStr !=null){
            attr = Integer.parseInt(attrStr);
        }
        return  attr;
    }
}
