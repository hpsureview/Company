package com.example.firma.Controller;

import com.example.firma.Model.Company;
import com.example.firma.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/firma")
public class CompanyController {

    @Autowired
    CompanyService service;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid @RequestBody Company company){
        if(service.insert(company))
            return ResponseEntity.ok().body("Muavfaqiyatli joylandi");
        return  ResponseEntity.status(208).body("Bunday firma mavjud");
    }

    @GetMapping
    public ResponseEntity<?> select(){
        return ResponseEntity.ok(service.select());
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody Company company){
       String s= service.update(id, company);
        if(s.equals("Muvaffaqiyatli yangilandi"))
            return ResponseEntity.ok().body(s);
        else if(s.equals("Bunday firma mavjud"))
            return ResponseEntity.status(208).body(s);
        return ResponseEntity.badRequest().body(s);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if(service.delete(id))
            return ResponseEntity.ok().body("Muvaffaqiyatli o'chirildi");
        return ResponseEntity.badRequest().body("Bunday firma yo'q");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
