package br.com.Veloxium.Econix.Service;

import br.com.Veloxium.Econix.Repository.PayRepository;
import br.com.Veloxium.Econix.Service.core.Services;
import br.com.Veloxium.Econix.domain.Payment;
import br.com.Veloxium.Econix.http.LoginFeing;
import br.com.Veloxium.Econix.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Primary
public class PayService implements Services {
    @Autowired
    private PayRepository repository;
    @Autowired
    private LoginFeing feing;
    @Override
    public ResponseEntity<Page<DataPayDTO>> findall(Pageable pageable) {
       return ResponseEntity.ok( repository.findByActiveTrue(pageable).map(DataPayDTO::new));
    }
    @Override
    public  ResponseEntity<DataPayDTO> findByID(Long id) {
        var pay = repository.findByIdAndActiveTrue(id);
        return pay.map(payment -> ResponseEntity.ok(new DataPayDTO(payment))).orElse(ResponseEntity.noContent().build());
    }
    @Override
    public ResponseEntity<DataPayDTO> update(Long id, UpdateDataPayDTO dto) {
        var pay = repository.findByIdAndActiveTrue(id);
        pay.ifPresent(payment -> payment.update(dto));
        return pay.map(payment ->
                ResponseEntity.ok(new DataPayDTO(payment)))
                .orElse(ResponseEntity.noContent().build());
    }
    @Override
    public ResponseEntity delete(Long id) {
        var pay = repository.findByIdAndActiveTrue(id);
        pay.ifPresent(Payment::delete);
        return ResponseEntity.noContent().build();
    }
    @Override
    public ResponseEntity<DataPayDTO> register(RegisterDataPayDTO dto, UriComponentsBuilder builder) {
        var pay = repository.save(new Payment(dto));
        var uri = builder.path("pay/{id}").buildAndExpand(pay.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataPayDTO(pay));
    }

    public ResponseEntity<DataPayDTO> pay(Long id) {
        var pay = repository.findByIdAndActiveTrue(id);
        pay.ifPresent(Payment::pay);
        return pay.map(payment ->
                        ResponseEntity.ok(new DataPayDTO(payment)))
                .orElse(ResponseEntity.noContent().build());
    }
}
