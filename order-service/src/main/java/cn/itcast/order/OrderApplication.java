package cn.itcast.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.config.DefaultFeignConfiguration;


@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
@EnableFeignClients (clients = UserClient.class ,defaultConfiguration = DefaultFeignConfiguration.class)

public class OrderApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
        
    }
    
    //RestTemplate オブジェクトの作成と注入
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }
    
    //ロードバランサーのルールを設定する
//    @Bean
//    public IRule randomReule() {
//    	return new RandomRule();
//    }

}