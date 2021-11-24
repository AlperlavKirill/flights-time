import junit.framework.TestCase;
import model.Ticket;
import service.TicketService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketServiceTest extends TestCase {
    List<Ticket> ListToTest = new ArrayList<>();

    @Override
    protected void setUp() {
        // duration 405m
        Ticket ticket1 = new Ticket("VVO", "Владивосток", "TLV", "Тель-Авив"
                , "12.05.18", "16:50", "12.05.18",
                "23:35", "SU", 1, 16700);

        //duration 350m
        Ticket ticket2 = new Ticket("VVO", "Владивосток", "TLV", "Тель-Авив",
                "12.05.18", "16:20", "12.05.18",
                "22:10", "TK", 3, 12400);

        //duration 605m
        Ticket ticket3 = new Ticket("VVO", "Владивосток", "TLV", "Тель-Авив",
                "12.05.18", "6:10", "12.05.18",
                "16:15", "S7", 0, 17400);

        ListToTest.addAll(Arrays.asList(ticket1, ticket2, ticket3));
    }

    public void testCalculateAverageTime() {
        int actual = TicketService.calculateAverageTime(ListToTest, "TLV", "VVO");
        int expected = 453;
        assertEquals(expected, actual);
    }

    public void testCalculatePercentile() {
        int actual = TicketService.calculatePercentile(ListToTest, 50, "TLV", "VVO");
        int expected = 405;
        assertEquals(expected, actual);
    }
}
