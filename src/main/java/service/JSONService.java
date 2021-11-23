package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JSONService {
    public static List<Ticket> getListFromJson(String jsonFilePath, String arrayName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List<Ticket>> ticketsMap = objectMapper
                .readValue(new File(jsonFilePath), new TypeReference<Map<String, List<Ticket>>>(){});
        return ticketsMap.get(arrayName);
    }
}
