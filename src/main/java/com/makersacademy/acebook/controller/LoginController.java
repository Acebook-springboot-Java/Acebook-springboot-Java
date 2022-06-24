// package com.makersacademy.acebook.controller;

// @Controller
// public class LoginController {
// private static final Logger logger =
// LogManager.getLogger(UsersController.class);

// @Autowired
// UserRepository userRepository;
// @Autowired
// UserService userService;

// @PostMapping("/login")
// public ResponseEntity<?> signup(@RequestBody User user) {
// logger.info("---------POST request for /users---------");
// if (userRepository.findByUsername(user.getUsername()) == null) {
// return ResponseHandler.generateResponse(HttpStatus.CREATED, false, "username
// created", user);
// }
// return ResponseHandler.generateResponse(HttpStatus.CONFLICT, true,
// "duplicated username", user);

// }

// }