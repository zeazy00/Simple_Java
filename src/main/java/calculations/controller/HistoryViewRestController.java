package calculations.controller;

import calculations.controller.dto.OperationResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

//@RestController
//@AllArgsConstructor
public class HistoryViewRestController {

   // private final MathExpressionRepository repository;

    @GetMapping
    public List<OperationResultDTO> getPage(@RequestParam LocalDateTime dateFrom,
                                          @RequestParam LocalDateTime dateTo){

        return null;
    }

    @GetMapping
    public List<OperationResultDTO> getFilteredPage(){
        
        return null;
    }

}
