//package fr.diginamic.app.controller;
//
//import fr.diginamic.app.dto.UserDto;
//import fr.diginamic.app.dto.UserMapper;
//import fr.diginamic.app.model.User;
//import fr.diginamic.app.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping
//    public UserDto createUser(@RequestBody UserDto userDto) {
//        User user = UserMapper.toEntity(userDto);
//        return UserMapper.toDto(userService.save(user));
//    }
//
//    @GetMapping
//    public List<UserDto> getAllUsers() {
//        return userService.findAll()
//                .stream()
//                .map(UserMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/{id}")
//    public UserDto getUserById(@PathVariable Long id) {
//        return userService.findById(id)
//                .map(UserMapper::toDto)
//                .orElse(null);
//    }
//}
