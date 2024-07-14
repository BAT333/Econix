package br.com.Veloxium.Econix.Service;

import br.com.Veloxium.Econix.Repository.PayRepository;
import br.com.Veloxium.Econix.domain.Payment;
import br.com.Veloxium.Econix.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service

public class PayService {
    @Autowired
    private PayRepository repository;
   /* @Autowired
    private LoginFeing feing;

    */

    public Page<DataPayDTO> findall(Pageable pageable) {
       return repository.findByActiveTrue(pageable).map(DataPayDTO::new);
    }

    public  DataPayDTO findByID(Long id) {
        var pay = repository.findByIdAndActiveTrue(id);
        return pay.map(DataPayDTO::new).orElse(null);
    }

    public DataPayDTO update(Long id, UpdateDataPayDTO dto) {
        var pay = repository.findByIdAndActiveTrue(id);
        pay.ifPresent(payment -> payment.update(dto));
        return pay.map(DataPayDTO::new)
                .orElse(null);
    }

    public void delete(Long id) {
        var pay = repository.findByIdAndActiveTrue(id);
        pay.ifPresent(Payment::delete);

    }
    public DataPayDTO register(RegisterDataPayDTO dto, UriComponentsBuilder builder) {
        var pay = repository.save(new Payment(dto));
        return new DataPayDTO(pay);
    }

    public DataPayDTO pay(Long id) {
        var pay = repository.findByIdAndActiveTrue(id);
        pay.ifPresent(Payment::pay);
        return pay.map(DataPayDTO::new)
                .orElse(null);
    }
}
