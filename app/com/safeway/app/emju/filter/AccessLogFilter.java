package com.safeway.app.emju.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.dispatch.OnComplete;
import play.api.mvc.*;
import scala.Function1;
import scala.concurrent.Future;

public class AccessLogFilter implements Filter {
	private final static Logger LOGGER = LoggerFactory.getLogger("accessLogger");

    @Override
    public Future<Result> apply(Function1<RequestHeader, Future<Result>> nextFilter, RequestHeader requestHeader) {

    	Future<Result> future = nextFilter.apply(requestHeader);
        
    	future.onComplete(new OnComplete<Result>() {
                    public void onComplete(Throwable t, Result result){
                    	int statusCode;
                    	if(t!=null)
                    	{
                    		statusCode = play.mvc.Http.Status.INTERNAL_SERVER_ERROR;
                    	}
                    	else
                    	{
                    		statusCode = result.header().status();
                    	}
                     	String msg = "status="+statusCode+ 
                     			" method="+requestHeader.method()+
                				" uri="+requestHeader.uri()+
                				" remote-address="+requestHeader.remoteAddress()+
                				" domain="+requestHeader.domain()+
                				" query-string="+requestHeader.rawQueryString()+
                				" referer="+(requestHeader.headers().get("referer").nonEmpty()?requestHeader.headers().get("referer"):"N/A")+
                				" user-agent="+(requestHeader.headers().get("user-agent").nonEmpty()?requestHeader.headers().get("user-agent"):"N/A");

                     	LOGGER.info(msg);
                      }
                  }, play.api.libs.concurrent.Execution.defaultContext());
        
        return future;
     }

    @Override
    public EssentialAction apply(EssentialAction next) {
        return Filter$class.apply(this, next);
    }
}