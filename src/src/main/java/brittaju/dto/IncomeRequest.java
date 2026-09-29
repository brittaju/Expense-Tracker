package brittaju.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IncomeRequest(BigDecimal amount, String category, LocalDate expenseDate) {}