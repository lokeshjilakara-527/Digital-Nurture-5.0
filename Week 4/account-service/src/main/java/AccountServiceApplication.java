

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Account microservice (port 8081). Everything for this service is in this one file. */
@SpringBootApplication
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}

/** Simple data model as a Java record (auto JSON). */
record Account(String number, String type, double balance, String customerName) { }

/** REST endpoints + in-memory data (no database needed). */
@RestController
@RequestMapping("/accounts")
class AccountController {

    private final List<Account> accounts = List.of(
        new Account("A1001", "SAVINGS", 52000.0, "Alger Wilson"),
        new Account("A1002", "CURRENT", 118500.0, "Klein Moretti"),
        new Account("A1003", "SAVINGS", 9800.0, "Audrey Hall")
    );

    @GetMapping                                   // GET /accounts
    public List<Account> getAll() {
        return accounts;
    }

    @GetMapping("/{number}")                       // GET /accounts/A1001
    public ResponseEntity<Account> getByNumber(@PathVariable String number) {
        return accounts.stream()
                .filter(a -> a.number().equalsIgnoreCase(number))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
