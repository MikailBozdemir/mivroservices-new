package com.hepsisurada.orderservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.hepsisurada.orderservice.dto.InventoryResponse;
import com.hepsisurada.orderservice.dto.OrderLineItemsDto;
import com.hepsisurada.orderservice.dto.OrderRequest;
import com.hepsisurada.orderservice.event.OrderPlaceEvent;
import com.hepsisurada.orderservice.model.Order;
import com.hepsisurada.orderservice.model.OrderLineItems;
import com.hepsisurada.orderservice.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements com.hepsisurada.orderservice.service.OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String,OrderPlaceEvent> kafkaTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,WebClient.Builder webClientBuilder
    ,KafkaTemplate kafkaTemplate){
        this.orderRepository=orderRepository;
        this.webClientBuilder=webClientBuilder;
        this.kafkaTemplate=kafkaTemplate;
    }

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineList(orderLineItems);
       List<String> skuCodes=  order.getOrderLineList()
                                    .stream()
                                    .map(OrderLineItems::getSkueCode)
                                    .toList();
        //Call inventory service and place order is  product is in stock
        InventoryResponse[] inventoryResponseArray=  webClientBuilder.build()
                    .get()
                    .uri("http://inventory-service/api/inventory",
                    uriBuilder->uriBuilder.queryParam("skuCode",skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block(); // senkron

                 Boolean allProductsInStock= Arrays.stream(inventoryResponseArray)
                                                   .allMatch(InventoryResponse:: isInStock);

            if(allProductsInStock){
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic",new OrderPlaceEvent(order.getOrderNumber()));
                return "Order Placed Successfully";
            }else{
                throw new IllegalArgumentException("Product is not in the stock");
            }
        
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkueCode(orderLineItemsDto.getSkueCode());
        return orderLineItems;

    }
}
