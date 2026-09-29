package brittaju.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(String description, BigDecimal amount, String category, LocalDate expenseDate) {}