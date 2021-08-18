package calculations.controller.dto.mappers;


import calculations.controller.dto.OperationResultDTO;
import calculations.model.entity.MathExpression;

public interface MathExpToOpResDTOMapper {
    //TODO: add mapstruct
    OperationResultDTO mathExpToOpRes(MathExpression mathExpression);
}
