package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.OrderDto;
import nl.johanvanderklift.backendeindopdracht.exception.RecordNotFoundException;
import nl.johanvanderklift.backendeindopdracht.model.Order;
import nl.johanvanderklift.backendeindopdracht.model.OrderLine;
import nl.johanvanderklift.backendeindopdracht.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;

    public OrderService(OrderRepository orderRepository, OrderLineService orderLineService) {
        this.orderRepository = orderRepository;
        this.orderLineService = orderLineService;
    }

    public Long createOrder(OrderDto dto) {
        Order order = new Order();
        orderRepository.save(transferDtoToOrder(dto, order));
        for (OrderLine orderLine : order.getOrderLines()) {
            orderLineService.setOrderId(order, orderLine);
        }
        return order.getId();
    }

    public List<OrderDto> getAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        List<OrderDto> dtos = new ArrayList<>();
        for (Order order : orders) {
            dtos.add(transferOrderToDto(order));
        }
        return dtos;
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        return transferOrderToDto(order);
    }

    public void updateOrder(Long id, OrderDto dto) {
        Order oldOrder = orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        if (oldOrder != null) {
            orderRepository.save(transferDtoToOrder(dto, oldOrder));
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private Order transferDtoToOrder(OrderDto dto, Order order) {
        order.setDateTime(LocalDateTime.now());
        return order;
    }

    private OrderDto transferOrderToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.id = order.getId();
        dto.dateTime = order.getDateTime();
        return dto;
    }
}
