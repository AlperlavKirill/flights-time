package service;

import model.Ticket;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TicketService {
    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yy");
    private final static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");

    public static int calculateAverageTime(List<Ticket> tickets, String arrival, String departure) {
        return (int) tickets.stream()
                .filter(ticket -> ((Objects.equals(ticket.getOrigin(), departure)) && (Objects.equals(ticket.getDestination(), arrival))))
                .mapToLong(ticket -> {

                    LocalDate departureDate = LocalDate.parse(ticket.getDepartureDate(), DATE_FORMAT);
                    LocalDate arrivalDate = LocalDate.parse(ticket.getArrivalDate(), DATE_FORMAT);
                    LocalTime departureTime = LocalTime.parse(ticket.getDepartureTime(), TIME_FORMAT);
                    LocalTime arrivalTime = LocalTime.parse(ticket.getArrivalTime(), TIME_FORMAT);

                    LocalDateTime departureFullTime = LocalDateTime.of(departureDate, departureTime);
                    LocalDateTime arrivalFullTime = LocalDateTime.of(arrivalDate, arrivalTime);

                    return Duration.between(departureFullTime, arrivalFullTime).toMinutes();
                })
                .average().orElse(0);
    }

    public static int calculatePercentile(List<Ticket> tickets, int percentile) {
        List<Integer> durations = tickets.stream().mapToInt(ticket -> {

            LocalDate departureDate = LocalDate.parse(ticket.getDepartureDate(), DATE_FORMAT);
            LocalDate arrivalDate = LocalDate.parse(ticket.getArrivalDate(), DATE_FORMAT);
            LocalTime departureTime = LocalTime.parse(ticket.getDepartureTime(), TIME_FORMAT);
            LocalTime arrivalTime = LocalTime.parse(ticket.getArrivalTime(), TIME_FORMAT);

            LocalDateTime departureFullTime = LocalDateTime.of(departureDate, departureTime);
            LocalDateTime arrivalFullTime = LocalDateTime.of(arrivalDate, arrivalTime);

            return (int) Duration.between(departureFullTime, arrivalFullTime).toMinutes();
        }).sorted().boxed().collect(Collectors.toList());

        int position = tickets.size() * percentile / 100;
        return durations.get(position);
    }
}
