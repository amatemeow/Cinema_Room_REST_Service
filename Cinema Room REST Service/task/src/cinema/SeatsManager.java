package cinema;

import cinema.exceptions.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@RestController
public class SeatsManager {
    public static final Map<String, Boolean> seatsSold = new ConcurrentHashMap<>();
    public static final ArrayList<Seat> seats = new ArrayList<>();
    public static final int maxRows = 9;
    public static final int maxCols = 9;
    public static final Map<UUID, Seat> tokens = new ConcurrentHashMap<>();

    private LinkedHashMap<String, Object> populateSeatsJSON() {
        LinkedHashMap<String, Object> JSON = new LinkedHashMap<>();
        JSON.put("total_rows", maxRows);
        JSON.put("total_columns", maxCols);
        JSON.put("available_seats", seats);

        return JSON;
    }

    @GetMapping("/seats")
    public Map<String, Object> getAvailableSeats() {
        return populateSeatsJSON();
    }

    @PostMapping("/purchase")
    public LinkedHashMap<String, Object> purchaseSeat(@RequestBody Map<String, Integer> chosenSeat)
            throws AlreadyPurchasedException, OutOfCinemaRangeException {
        if (chosenSeat.get("row") > maxRows || chosenSeat.get("row") < 0
                || chosenSeat.get("column") > maxCols || chosenSeat.get("column") < 0) {
            throw new OutOfCinemaRangeException();
        }

        Seat tmpSeat = new Seat(chosenSeat.get("row"), chosenSeat.get("column"));

        if (seatsSold.get(tmpSeat.toString())) {
            throw new AlreadyPurchasedException();
        }

        seatsSold.replace(tmpSeat.toString(), true);

        UUID uuid = UUID.randomUUID();
        tokens.put(uuid, tmpSeat);

        return new LinkedHashMap<>(Map.of("token", uuid, "ticket", tmpSeat));
    }

    @PostMapping("/return")
    public LinkedHashMap<String, Object> returnSeat(@RequestBody Map<String, UUID> tokenInfo) throws WrongTokenException {
        UUID token = tokenInfo.get("token");
        if (!tokens.containsKey(token)) {
            throw new WrongTokenException();
        }

        Seat tmpSeat = tokens.get(token);
        tokens.remove(token);
        seatsSold.replace(tmpSeat.toString(), false);

        return new LinkedHashMap<>(Map.of("returned_ticket", tmpSeat));
    }

    @PostMapping("/stats")
    public LinkedHashMap<String, Integer> getStatistics(@RequestParam(required = false) String password)
            throws WrongPasswordException {
        if (!"super_secret".equals(password)) {
            throw new WrongPasswordException();
        }

        int income = 0;
        if (!tokens.isEmpty()) {
            for (Seat seat : tokens.values()) {
                income += seat.getPrice();
            }
        }

        LinkedHashMap<String, Integer> responseBody = new LinkedHashMap<>();
        responseBody.put("current_income", income);
        responseBody.put("number_of_available_seats", (int) seatsSold.values().stream().filter(s -> !s).count());
        responseBody.put("number_of_purchased_tickets", tokens.size());

        return responseBody;
    }
}

