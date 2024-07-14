package br.com.Veloxium.Econix.domain;

import br.com.Veloxium.Econix.model.RegisterDataPayDTO;
import br.com.Veloxium.Econix.model.UpdateDataPayDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "value",nullable = false)
    private BigDecimal value;
    @Column(name = "names",nullable = false,length = 100)
    private String name;
    @Column(name = "numbers",nullable = false,length = 19)
    private String number;
    @Column(name = "expirations",nullable = false,length = 7)
    private String expiration;
    @Column(name = "codes",nullable = false,precision = 3)
    private String code;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "form_payment_id")
    private Long formOfPaymentId;
    @Column(name = "actives")
    private boolean active;


    public Payment(RegisterDataPayDTO dto) {
        this.value = dto.value();
        this.name= dto.name();
        this.number = dto.number();
        this.expiration = dto.expiration();
        this.code = dto.code();
        this.status = dto.status();
        this.orderId = dto.orderId();
        this.formOfPaymentId = dto.formOfPaymentId();
        this.active = true;

    }


    public void update(UpdateDataPayDTO dto) {
        if(dto.value() != null){this.value = dto.value();}
        if(dto.status() != null){this.status = dto.status();}
        if(dto.code() != null){this.code = dto.code();}
        if(dto.formOfPaymentId() != null){this.formOfPaymentId = dto.formOfPaymentId();}
        if(dto.expiration() != null){this.expiration = dto.expiration();}
        if(dto.name() != null){this.name = dto.name();}
        if(dto.number() != null){this.number = dto.number();}
        if(dto.orderId() != null){this.orderId = dto.orderId();}

    }
    public void delete(){
        this.active = false;
    }


    public void pay() {
        this.status = Status.CONFIRMED;
    }
}
