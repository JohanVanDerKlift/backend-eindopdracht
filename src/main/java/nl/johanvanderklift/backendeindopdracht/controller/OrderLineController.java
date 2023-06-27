package nl.johanvanderklift.backendeindopdracht.controller;

import nl.johanvanderklift.backendeindopdracht.dto.OrderDto;
import nl.johanvanderklift.backendeindopdracht.dto.OrderLineDto;
import nl.johanvanderklift.backendeindopdracht.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order_lines")
public class OrderLineController {
    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @PostMapping
    public ResponseEntity<Object> createOrderLine(@RequestBody OrderLineDto dto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            Long newId = orderLineService.createOrderLine(dto);
            return ResponseEntity.ok(newId);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderLineDto> getOrderLineById(@PathVariable Long id) {
        OrderLineDto dto = orderLineService.getOrderLineById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<OrderLineDto>> getOrderLinesByIds(Iterable<Long> ids) {
        List<OrderLineDto> dtos = orderLineService.getOrderLinesByIds(ids);
        return ResponseEntity.ok().body(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrderLine(@PathVariable Long id, @RequestParam OrderLineDto dto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            orderLineService.updateOrderLine(id, dto);
            return ResponseEntity.ok().body(dto);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteOrderLine(@PathVariable Long id) {
        orderLineService.deleteOrderLine(id);
    }

    private String getBindingResult(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField()).append(": ").append(fe.getDefaultMessage()).append("\n");
        }
        return sb.toString();
    }
}
