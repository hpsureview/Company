package com.example.firma.Controller;

import com.example.firma.Dto.IshchiCompany;
import com.example.firma.Service.AddressService;
import com.example.firma.Service.IshchiService;
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
@RequestMapping("Address")
public class AddressController {
    @Autowired
    AddressService service;

    @PostMapping("/addinsert")
    public ResponseEntity<?> insert(@Valid @RequestBody IshchiCompany dto){
        String insert1 = service.insert1(dto);
        if(insert1.equals("Muavfaqiyatli joylandi"))
            return ResponseEntity.ok().body(insert1);
        else if(insert1.equals("Bunady address mavjud"))
            return ResponseEntity.status(208).body(insert1);
        return ResponseEntity.badRequest().body(insert1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> select(@PathVariable Integer id){
        if(service.select(id)==null)
            return ResponseEntity.badRequest().body("Buday address yo'q");
        return ResponseEntity.ok().body(service.select(id));
    }

    @PutMapping("/addupdate/{id}")
    public ResponseEntity<?> upadte(@PathVariable Integer id, @Valid @RequestBody IshchiCompany dto){
        String s = service.update(dto, id);
        if(s.equals("Muavfaqiyatli yangilandi"))
            return ResponseEntity.ok().body(s);
        else if(s.equals("Bunady address mavjud"))
            return ResponseEntity.status(208).body(s);
        return ResponseEntity.badRequest().body(s);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if(service.delete(id))
            return ResponseEntity.ok().body("Muavfaqiyatli o'chirildi");
        return ResponseEntity.badRequest().body("Bunday address yo'q");
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
