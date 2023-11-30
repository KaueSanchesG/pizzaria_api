package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.dto.login.UserDTO;
import pizzaria.pizzaria.entity.UserEntity;
import pizzaria.pizzaria.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getId(@PathVariable("id") final Long id){
        return new ResponseEntity<>(service.getId(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> create(@RequestBody @Validated UserEntity entity){
        return new ResponseEntity<>(service.create(entity), HttpStatus.OK);
    }
}
