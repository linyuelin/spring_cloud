package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.注文をクエリ
        Order order = orderMapper.findById(orderId);
        //2.restTemplateによってhttpリクエストを発送し、ユーザー情報を取得
        String url = "http://localhost:8081/user/"  + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        //3.ユーザー情報をorderに組み込む
        order.setUser(user);
        
        // 4.リターン
        return order;
    }
}
