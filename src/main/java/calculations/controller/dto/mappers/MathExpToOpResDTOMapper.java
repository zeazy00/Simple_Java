package calculations.controller.dto.mappers;


import calculations.controller.dto.HistoryDTO;
import calculations.controller.dto.OperationResultDTO;
import calculations.model.entity.MathExpression;
import org.mapstruct.Mapper;

@Mapper
public interface MathExpToOpResDTOMapper {

    OperationResultDTO mathExpToOpRes(MathExpression mathExpression);

    HistoryDTO mathExpToDTO(MathExpression mathExpression);
}
