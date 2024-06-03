package br.com.Veloxium.Econix.model;

import br.com.Veloxium.Econix.domain.Status;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record RegisterDataPayDTO(
        @NotNull
        @Positive
        BigDecimal value,
        @NotNull
        @Size(min = 2)
        String name,
        @NotNull
        @Size(max=19)
        String number,
        @NotNull
        @Size(max=7)
        String expiration,
        @NotNull
        @Size(min=3, max=3)
        String code,
        @NotNull
        Status status,
        @NotNull
        Long orderId,
        @NotNull
        Long formOfPaymentId
) {
}
