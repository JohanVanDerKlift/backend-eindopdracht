package nl.johanvanderklift.backendeindopdracht.dto;

import nl.johanvanderklift.backendeindopdracht.model.OrderLine;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    public Long id;
    public LocalDateTime dateTime;
    public List<OrderLine> orderLines;
}
