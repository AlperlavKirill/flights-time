import model.Ticket;
import service.JSONService;
import service.TicketService;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Main {

    private final static String JSON_FILE = "src/main/resources/tickets.json";
    private final static String DEPARTURE = "VVO";
    private final static String ARRIVAL = "TLV";
    private final static String JSON_ARRAY_NAME = "tickets";
    private final static int PERCENTILE = 90;

    public static void main(String[] args) {
        try {
            List<Ticket> tickets = JSONService.getListFromJson(JSON_FILE, JSON_ARRAY_NAME);
            System.out.println("Среднее время полета между Владивостоком и Тель-Авивом составляет:\n"
                    + beautyOutput(TicketService.calculateAverageTime(tickets, ARRIVAL, DEPARTURE)));
            System.out.println("90-й перцентиль времени между Владивостоком и Тель-Авивом составляет:\n"
                    + beautyOutput(TicketService.calculatePercentile(tickets, PERCENTILE, ARRIVAL, DEPARTURE)));
        } catch (IOException e) {
            System.err.println("error parsing json file");
            System.exit(1);
        }
    }

    private static String beautyOutput(int minutes) {
        Duration duration = Duration.ofMinutes(minutes);
        return duration.toHoursPart() + " часов " + duration.toMinutesPart() + " минут.";
    }
}
