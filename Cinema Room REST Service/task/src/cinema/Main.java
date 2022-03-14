package cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        populateCinema();
        SpringApplication.run(Main.class, args);
    }

    private static void populateCinema() {
        for (int i = 1; i <= SeatsManager.maxRows; i++) {
            for (int j = 1; j <= SeatsManager.maxCols; j++) {
                Seat processedSeat = new Seat(i, j);
                SeatsManager.seats.add(processedSeat);
                SeatsManager.seatsSold.put(processedSeat.toString(), false);
            }
        }
    }
}
