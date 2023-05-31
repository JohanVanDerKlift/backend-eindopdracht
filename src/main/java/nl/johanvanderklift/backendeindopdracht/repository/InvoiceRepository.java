package nl.johanvanderklift.backendeindopdracht.repository;

import nl.johanvanderklift.backendeindopdracht.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
