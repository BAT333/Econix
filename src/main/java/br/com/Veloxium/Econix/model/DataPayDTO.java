package br.com.Veloxium.Econix.model;

import br.com.Veloxium.Econix.domain.Payment;
import br.com.Veloxium.Econix.domain.Status;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DataPayDTO (
        Long id,
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
){
    public DataPayDTO(Payment pay) {
        this(pay.getId(),pay.getValue(), pay.getName(), pay.getNumber(), pay.getExpiration(), pay.getCode(),pay.getStatus(), pay.getOrderId(), pay.getOrderId());
    }
}
