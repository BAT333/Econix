package br.com.Veloxium.Econix.Controller;

import br.com.Veloxium.Econix.Repository.PayRepository;
import br.com.Veloxium.Econix.Service.PayService;
import br.com.Veloxium.Econix.Service.core.Services;
import br.com.Veloxium.Econix.domain.Payment;
import br.com.Veloxium.Econix.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private PayService service;
    @GetMapping
    public ResponseEntity<Page<DataPayDTO>> allPay(@PageableDefault Pageable pageable){
        return this.service.findall(pageable);
    }
    @GetMapping("{id}")
    public ResponseEntity<DataPayDTO> payID(@PathVariable Long id){
        return this.service.findByID(id);
    }
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DataPayDTO> updatepay(@PathVariable Long id,@RequestBody UpdateDataPayDTO dto){
        return this.service.update(id,dto);
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletePay(@PathVariable Long id){
        return this.service.delete(id);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<DataPayDTO> registerPay(@RequestBody @Valid RegisterDataPayDTO dto, UriComponentsBuilder builder){
        return this.service.register(dto,builder);
    }

    @PutMapping("status/{id}")
    @Transactional
    public ResponseEntity<DataPayDTO> pay(@PathVariable Long id){
            return this.service.pay(id);
    }




}
