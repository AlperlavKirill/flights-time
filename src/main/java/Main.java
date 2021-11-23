import model.Ticket;
import service.JSONService;
import service.TicketService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main {

    private final static String JSON_FILE = "src/main/resources/tickets.json";
    private final static String DEPARTURE = "VVO";
    private final static String ARRIVAL = "TLV";
    private final static int PERCENTILE = 90;

    public static void main(String[] args) {
        try {
            List<Ticket> tickets = JSONService.getListFromJson(JSON_FILE, "tickets");
            System.out.println(TicketService.calculateAverageTime(tickets, ARRIVAL, DEPARTURE));
            System.out.println(TicketService.calculatePercentile(tickets, PERCENTILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
