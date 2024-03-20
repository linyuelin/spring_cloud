package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter{
    
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		//リクエストのパラメーターを取得
		ServerHttpRequest request =  exchange.getRequest();
	    MultiValueMap<String, String> params =  request.getQueryParams();
	    
		//authorization パラメーターを特定
	    
	   String auth = params.getFirst("authorization");
	   
		//値はadminにイコールかどうかを確認 
	    if ("admin".equals(auth)) {
	    	//はい パス
	    	return chain.filter(exchange);
	    }
	    
	    //　未ログイン
	    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	   // いいえ インターセプト
		return exchange.getResponse().setComplete();
	}

}
