package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.OrderLineDto;
import nl.johanvanderklift.backendeindopdracht.model.Order;
import nl.johanvanderklift.backendeindopdracht.model.OrderLine;
import nl.johanvanderklift.backendeindopdracht.repository.OrderLineRepository;
import nl.johanvanderklift.backendeindopdracht.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    public OrderLineService(OrderLineRepository orderLineRepository, OrderRepository orderRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
    }

    public Long createOrderLine(OrderLineDto dto) {
        OrderLine orderLine = new OrderLine();
        orderLineRepository.save(transferDtoToOrderline(dto, orderLine));
        return orderLine.getId();
    }

    public void setOrderId(Order order, OrderLine orderLine) {
        OrderLine oldOrderLine = orderLineRepository.findById(orderLine.getId()).orElseThrow();
        oldOrderLine.setOrder(order);
        orderLineRepository.save(oldOrderLine);
    }

    private OrderLine transferDtoToOrderline(OrderLineDto dto, OrderLine orderLine) {
        orderLine.setAmount(dto.amount);
        orderLine.setDishName(dto.dishName);
        orderLine.setPrice(dto.price);
        Optional<Order> order = orderRepository.findById(dto.orderId);
        order.ifPresent(orderLine::setOrder);
        return orderLine;
    }
}
