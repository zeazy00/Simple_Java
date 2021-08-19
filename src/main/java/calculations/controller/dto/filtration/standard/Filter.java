package calculations.controller.dto.filtration.standard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;

public class Filter {

    public static FilterBuilder builder() {
        return new FilterBuilder();
    }

    public static class FilterBuilder {
        private final BooleanBuilder builder = new BooleanBuilder();

        public <T> FilterBuilder and(T value, Function<T, BooleanExpression> function) {
            if (value != null)
                builder.and(function.apply(value));

            return this;
        }

        public Predicate build(){
            return builder;
        }
    }

}
