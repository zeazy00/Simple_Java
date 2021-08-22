package calculations.controller.dto.mappers;


import calculations.controller.dto.HistoryDTO;
import calculations.controller.dto.OperationResultDTO;
import calculations.model.entity.MathExpression;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MathExpToOpResDTOMapper {

    @Mapping(target = "operation", source = "operation.opName")
    OperationResultDTO mathExpToOpRes(MathExpression mathExpression);

    @Mapping(target = "operation", source = "operation.opName")
    HistoryDTO mathExpToDTO(MathExpression mathExpression);
}
