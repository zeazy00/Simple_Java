package calculations.model.repository;

import calculations.model.entity.MathExpression;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MathExpressionRepository extends JpaRepository<MathExpression, UUID> {
}
