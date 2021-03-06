package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cinema.domain.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}
