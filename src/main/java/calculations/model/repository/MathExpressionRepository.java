package calculations.model.repository;

import calculations.model.entity.MathExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.UUID;

public interface MathExpressionRepository extends JpaRepository<MathExpression, UUID>,
                                                  JpaSpecificationExecutor<MathExpression>,
                                                  QuerydslPredicateExecutor<MathExpression> {
    List<MathExpression> findByInput(String input);
}
