package cinema.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import cinema.domain.Ticket;
import cinema.domain.TicketType;
import cinema.repository.TicketRepository;
import cinema.repository.TicketTypeRepository;
import cinema.service.TicketService;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final double filmCost;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketTypeRepository ticketTypeRepository,
            @Value("${price-per-minute}") double filmCost) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.filmCost = filmCost;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<TicketType> getAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }

    @Override
    public void createTicket(Ticket ticket) {
        ticket.setId(null);
        ticketRepository.save(ticket);
    }

    @Override
    public double getTicketPrice(Ticket ticket) {
        return getFilmCost(ticket) * filmCost * ticket.getTicketType().getMultiplier();
    }

    @Override
    public Optional<TicketType> findTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id);
    }

    private double getFilmCost(Ticket ticket) {
        double dx = ticket.getFilmRent().getMinutes() / ticket.getFilmRent().getWeek();
        return Math.sqrt(dx * dx);
    }

}
