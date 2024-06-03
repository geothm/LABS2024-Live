package ro.irian.labs2024.pizza.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ro.irian.labs2024.pizza.domain.vo.IngredientProjection;
import ro.irian.labs2024.pizza.dto.IngredientDTO;
import ro.irian.labs2024.pizza.service.IngredientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Long> saveIngredient(@Valid @RequestBody IngredientDTO ingredientDTO){

        return ResponseEntity.ok(ingredientService.saveIngredient(ingredientDTO));

    }

    @PutMapping
    public ResponseEntity<Long> updateIngredient(@Valid @RequestBody IngredientDTO ingredientDTO){

        return ResponseEntity.ok(ingredientService.updateIngredient(ingredientDTO));

    }

    @GetMapping("/{id}")
    public IngredientDTO getIngredientDTOById(@PathVariable Long id){
        return ingredientService.getIngredientDTOById(id);
    }

    @GetMapping
    public List<IngredientProjection> getAllIngredients(){
        return ingredientService.findAllIngredients();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> putErrorInMap(errors, error));
        return errors;
    }

    private void putErrorInMap(Map<String, String> errors, ObjectError error) {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    }
}
