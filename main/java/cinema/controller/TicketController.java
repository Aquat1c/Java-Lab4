package cinema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;
import cinema.domain.Ticket;
import cinema.service.FilmService;
import cinema.service.TicketService;

import javax.servlet.http.HttpSession;

@Controller
public class TicketController {

    private final FilmService filmService;
    private final TicketService ticketService;

    public TicketController(FilmService filmService, TicketService ticketService) {
        this.filmService = filmService;
        this.ticketService = ticketService;
    }

    @GetMapping("/get-tickets")
    public String getGetTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "get-tickets";
    }

    @GetMapping("/add-ticket")
    public String getAddTicket(Model model) {
        model.addAttribute("filmss", filmService.getAllFilms());
        model.addAttribute("ticketTypes", ticketService.getAllTicketTypes());
        return "add-ticket";
    }

    @PostMapping("/add-ticket")
    public String postAddTicket(@RequestParam Long from, @RequestParam Long to, @RequestParam Long type, HttpSession session) {
        Ticket ticket = new Ticket();
        ticket.setTicketType(ticketService.findTicketTypeById(type).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        ticket.setFilmRent(filmService.findFilmById(from).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        session.setAttribute("ticket", ticket);
        return "redirect:/add-ticket-confirmation";
    }

    @GetMapping("/add-ticket-confirmation")
    public String getAddTicketConfirmation(Model model, @SessionAttribute Ticket ticket) {
        model.addAttribute("ticket", ticket);
        model.addAttribute("price", ticketService.getTicketPrice(ticket));
        return "add-ticket-confirmation";
    }

    @PostMapping("/add-ticket-confirmation")
    public String postAddTicketConfirmation(@SessionAttribute Ticket ticket, HttpSession session) {
        ticket.setConfirmedPrice(ticketService.getTicketPrice(ticket));
        ticketService.createTicket(ticket);
        session.removeAttribute("ticket");
        return "redirect:/get-tickets";
    }

}
