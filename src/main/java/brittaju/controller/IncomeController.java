package brittaju.controller;

import brittaju.dto.IncomeRequest;
import brittaju.entity.IncomeEntity;
import brittaju.entity.UserEntity;
import brittaju.service.IncomeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/")
    public ResponseEntity<IncomeEntity> createIncome(@RequestBody IncomeRequest request,
                                                     @AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(incomeService.createIncome(request, userEntity));
    }

    @GetMapping("/")
    public ResponseEntity<List<IncomeEntity>> getAllIncomes(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(incomeService.getAllIncome(userEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeEntity> getIncome(@PathVariable Long id,
                                                  @AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(incomeService.getIncome(id, userEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeEntity> updateIncome(@PathVariable Long id,
                                                     @RequestBody IncomeRequest request,
                                                     @AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(incomeService.updateIncome(id, request, userEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id,
            @AuthenticationPrincipal UserEntity userEntity) {
        incomeService.deleteIncome(id, userEntity);
        return ResponseEntity.ok().build();
    }
}