package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.OrderLineDto;
import nl.johanvanderklift.backendeindopdracht.exception.RecordNotFoundException;
import nl.johanvanderklift.backendeindopdracht.model.Order;
import nl.johanvanderklift.backendeindopdracht.model.OrderLine;
import nl.johanvanderklift.backendeindopdracht.repository.OrderLineRepository;
import nl.johanvanderklift.backendeindopdracht.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public OrderLineDto getOrderLineById(Long id) {
        Optional<OrderLine> orderLine= orderLineRepository.findById(id);
        if (orderLine.isPresent()) {
            OrderLineDto dto = transferOrderLineToDto(orderLine.get());
            return dto;
        } else {
            throw new RecordNotFoundException("Order line with id: " + id + " not found");
        }
    }

    public List<OrderLineDto> getOrderLinesByIds(Iterable<Long> ids) {
        Iterable<OrderLine> orderLines = orderLineRepository.findAllById(ids);
        List<OrderLineDto> dtos = new ArrayList<>();
        for (OrderLine orderLine : orderLines) {
            dtos.add(transferOrderLineToDto(orderLine));
        }
        return dtos;
    }

    public void updateOrderLine(Long id, OrderLineDto dto) {
        Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(id);
        if (optionalOrderLine.isPresent()) {
            orderLineRepository.save(transferDtoToOrderline(dto, optionalOrderLine.get()));
        } else {
            throw new RecordNotFoundException("Order line with id: " + id + " not found");
        }
    }

    public void deleteOrderLine(Long id) {
        Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(id);
        if (optionalOrderLine.isPresent()) {
            orderLineRepository.delete(optionalOrderLine.get());
        } else {
            throw new RecordNotFoundException("Order line with id: " + id + " not found");
        }
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
        orderLine.setTotalPrice(dto.price * dto.amount);
        if (dto.orderId != null) {
            Optional<Order> order = orderRepository.findById(dto.orderId);
            order.ifPresent(orderLine::setOrder);
        }
        return orderLine;
    }

    private OrderLineDto transferOrderLineToDto(OrderLine orderLine) {
        OrderLineDto dto = new OrderLineDto();
        dto.amount = orderLine.getAmount();
        dto.dishName = orderLine.getDishName();
        dto.price = orderLine.getPrice();
        dto.totalPrice = orderLine.getTotalPrice();
        return dto;
    }
}
