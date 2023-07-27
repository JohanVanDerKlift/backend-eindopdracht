package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.InvoiceDto;
import nl.johanvanderklift.backendeindopdracht.dto.OrderDto;
import nl.johanvanderklift.backendeindopdracht.exception.RecordNotFoundException;
import nl.johanvanderklift.backendeindopdracht.model.Invoice;
import nl.johanvanderklift.backendeindopdracht.model.Order;
import nl.johanvanderklift.backendeindopdracht.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Long createInvoice(InvoiceDto dto) {
        Invoice invoice = new Invoice();
        invoiceRepository.save(transferDtoToInvoice(dto, invoice));
        return invoice.getId();
    }

    public List<InvoiceDto> getAllInvoices() {
        Iterable<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDto> dtos = new ArrayList<>();
        for (Invoice invoice : invoices) {
            dtos.add(transferInvoiceToDto(invoice));
        }
        return dtos;
    }

    public InvoiceDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        return transferInvoiceToDto(invoice);
    }

    public boolean isInvoicePaid(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            InvoiceDto invoiceDto = transferInvoiceToDto(optionalInvoice.get());
            return invoiceDto.isPaid;
        } else {
            throw new RecordNotFoundException("invoice with id " + id + " not found");
        }
    }

    public void updateInvoice(Long id, InvoiceDto dto) {
        Invoice oldInvoice = invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        if (oldInvoice != null) {
            invoiceRepository.save(transferDtoToInvoice(dto, oldInvoice));
        }
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    private Invoice transferDtoToInvoice(InvoiceDto dto, Invoice invoice) {
        invoice.setTax(dto.tax);
        invoice.setDeliveryCost(dto.deliveryCost);
        invoice.setTotalPrice(dto.totalPrice);
        invoice.setIsPaid(dto.isPaid);
        return invoice;
    }

    private InvoiceDto transferInvoiceToDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();
        dto.id = invoice.getId();
        dto.tax = invoice.getTax();
        dto.deliveryCost = invoice.getDeliveryCost();
        dto.totalPrice = invoice.getTotalPrice();
        dto.isPaid = invoice.getIsPaid();
        return dto;
    }
}
