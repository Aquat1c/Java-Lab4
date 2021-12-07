package cinema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "from_id", nullable = false)
    private Film from;

    @Column(name = "confirmed_price", nullable = false)
    private Double confirmedPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Film getFilmRent() {
        return from;
    }

    public void setFilmRent(Film from) {
        this.from = from;
    }


    public Double getConfirmedPrice() {
        return confirmedPrice;
    }

    public void setConfirmedPrice(Double confirmedPrice) {
        this.confirmedPrice = confirmedPrice;
    }

}
