package brittaju.controller;

import brittaju.dto.ExpenseRequest;
import brittaju.entity.ExpenseEntity;
import brittaju.entity.UserEntity;
import brittaju.service.ExpenseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/")
    public ResponseEntity<ExpenseEntity> createExpense(@RequestBody ExpenseRequest request,
                                                       @AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(expenseService.createExpense(request, userEntity));
    }

    @GetMapping("/")
    public ResponseEntity<List<ExpenseEntity>> getAllExpenses(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(expenseService.getAllExpenses(userEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseEntity> getExpense(@PathVariable Long id,
                                                    @AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(expenseService.getExpense(id, userEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseEntity> updateExpense(@PathVariable Long id,
                                                       @RequestBody ExpenseRequest request,
                                                       @AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(expenseService.updateExpense(id, request, userEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id,
            @AuthenticationPrincipal UserEntity userEntity) {
        expenseService.deleteExpense(id, userEntity);
        return ResponseEntity.ok().build();
    }
}