package br.com.Veloxium.Econix.Service.core;

import br.com.Veloxium.Econix.model.DataPayDTO;
import br.com.Veloxium.Econix.model.RegisterDataPayDTO;
import br.com.Veloxium.Econix.model.UpdateDataPayDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface Services {
    <T> ResponseEntity<Page<T>> findall(Pageable pageable);
    <T>ResponseEntity<T> findByID(Long id);
    <T> ResponseEntity<T> update(Long id, UpdateDataPayDTO dto);
    <T>ResponseEntity<T> delete(Long id);
    <T>ResponseEntity<T> register(RegisterDataPayDTO dto, UriComponentsBuilder builder);
}
