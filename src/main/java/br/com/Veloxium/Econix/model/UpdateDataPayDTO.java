package br.com.Veloxium.Econix.model;

import br.com.Veloxium.Econix.domain.Status;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateDataPayDTO(
        @NotNull
        BigDecimal value,
        @NotNull
        String name,
        @NotNull
        String number,
        @NotNull
        String expiration,
        @NotNull
        String code,
        @NotNull
        Status status,
        @NotNull
        Long orderId,
        @NotNull
        Long formOfPaymentId
) {
}
