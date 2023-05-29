package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.OrderDto;
import nl.johanvanderklift.backendeindopdracht.model.Order;
import nl.johanvanderklift.backendeindopdracht.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Long createOrder(OrderDto dto) {
        Order order = new Order();
        orderRepository.save(transferDtoToOrder(dto, order));
        return order.getId();
    }

    private Order transferDtoToOrder(OrderDto dto, Order order) {
        order.setDeliveryCost(dto.deliveryCost);
        order.setTax(dto.tax);
        order.setTotalPrice(dto.totalPrice);
        return order;
    }
}
