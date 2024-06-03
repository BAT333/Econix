package br.com.Veloxium.Econix.Repository;

import br.com.Veloxium.Econix.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayRepository extends JpaRepository<Payment,Long> {
    Page<Payment> findByActiveTrue(Pageable pageable);

    Optional<Payment> findByIdAndActiveTrue(Long id);
}
