package entity;

public class TicketUser {
    private User user;
    private BuyMovieTicket buyMovieTicket;

    public TicketUser(User user, BuyMovieTicket buyMovieTicket) {
        this.user = user;
        this.buyMovieTicket = buyMovieTicket;
    }
}
