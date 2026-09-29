package brittaju.controller;

import brittaju.dto.IncomeRequest;
import brittaju.entity.Income;
import brittaju.entity.User;
import brittaju.service.IncomeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/income")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/")
    public ResponseEntity<Income> createIncome(@RequestBody IncomeRequest request,
                                               @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(incomeService.createIncome(request, user));
    }

    @GetMapping("/")
    public ResponseEntity<List<Income>> getAllIncomes(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(incomeService.getAllIncome(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncome(@PathVariable Long id,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(incomeService.getIncome(id, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long id,
            @RequestBody IncomeRequest request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(incomeService.updateIncome(id, request, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id,
            @AuthenticationPrincipal User user) {
        incomeService.deleteIncome(id, user);
        return ResponseEntity.ok().build();
    }
}