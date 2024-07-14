package br.com.Veloxium.Econix.Controller;

import br.com.Veloxium.Econix.Service.PayService;
import br.com.Veloxium.Econix.model.DataPayDTO;
import br.com.Veloxium.Econix.model.RegisterDataPayDTO;
import br.com.Veloxium.Econix.model.UpdateDataPayDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pay")
@SecurityRequirement(name = "bearer-key")
public class PayController {
    @Autowired
    private PayService service;
    @GetMapping
    public ResponseEntity<Page<DataPayDTO>> allPay(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(this.service.findall(pageable));
    }
    @GetMapping("{id}")
    public ResponseEntity<DataPayDTO> payID(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findByID(id));
    }
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DataPayDTO> updatepay(@PathVariable Long id,@RequestBody UpdateDataPayDTO dto){
        return ResponseEntity.ok(this.service.update(id,dto));
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletePay(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    @Transactional
    public ResponseEntity<DataPayDTO> registerPay(@RequestBody @Valid RegisterDataPayDTO dto, UriComponentsBuilder builder){
        var pay = this.service.register(dto,builder);
        var uri = builder.path("pay/{id}").buildAndExpand(pay.id()).toUri();
        return ResponseEntity.created(uri).body(pay);
    }

    @PutMapping("status/{id}")
    @Transactional
    public ResponseEntity<DataPayDTO> pay(@PathVariable Long id){
        return ResponseEntity.ok(this.service.pay(id));
    }




}
