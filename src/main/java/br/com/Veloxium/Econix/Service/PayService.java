package br.com.Veloxium.Econix.Service;

import br.com.Veloxium.Econix.Repository.PayRepository;
import br.com.Veloxium.Econix.domain.Payment;
import br.com.Veloxium.Econix.model.DataPayDTO;
import br.com.Veloxium.Econix.model.RegisterDataPayDTO;
import br.com.Veloxium.Econix.model.UpdateDataPayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PayService {
    @Autowired
    private PayRepository repository;

    public ResponseEntity<Page<DataPayDTO>> allPay(Pageable pageable) {
       return ResponseEntity.ok( repository.findByActiveTrue(pageable).map(DataPayDTO::new));
    }
    public ResponseEntity<DataPayDTO> payID(Long id) {
        var pay = repository.findByIdAndActiveTrue(id);
        return pay.map(payment -> ResponseEntity.ok(new DataPayDTO(payment))).orElse(ResponseEntity.noContent().build());
    }

    public ResponseEntity<DataPayDTO> update(Long id, UpdateDataPayDTO dto) {
        var pay = repository.findByIdAndActiveTrue(id);
        pay.ifPresent(payment -> payment.update(dto));
        return pay.map(payment ->
                ResponseEntity.ok(new DataPayDTO(payment)))
                .orElse(ResponseEntity.noContent().build());
    }

    public ResponseEntity delete(Long id) {
        var pay = repository.findByIdAndActiveTrue(id);
        pay.ifPresent(Payment::delete);
        return ResponseEntity.noContent().build();
    }

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
