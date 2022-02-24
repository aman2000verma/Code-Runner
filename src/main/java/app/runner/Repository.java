package app.runner;


import app.runner.models.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Code, Integer> {
}
