package dev.java._x.desafiorelogio.repository;

import dev.java._x.desafiorelogio.entity.Relogio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RelogioRepository extends JpaRepository<Relogio, UUID> , JpaSpecificationExecutor<Relogio> {

}
