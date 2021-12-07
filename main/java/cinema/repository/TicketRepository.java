package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cinema.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
