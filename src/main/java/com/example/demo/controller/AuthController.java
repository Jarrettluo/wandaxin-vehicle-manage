package com.example.demo.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// https://www.cnblogs.com/jinbuqi/p/11014415.html

@RestController
@RequestMapping("/auth")
public class AuthController {

//     @PostMapping(value = "/register", produces="application/json")
//     public ResponseEntity<Result> register(
//             @Valid @RequestBody RegisterRequest register,
//             BindingResult bindingResult
//     ) {
//         if(bindingResult.hasErrors()) {
//             //rfc4918 - 11.2. 422: Unprocessable Entity
// //			res.setStatus(422);
// //			res.setMessage("输入错误");
// //			res.putData("fieldErrors", bindingResult.getFieldErrors());
//
//             Result res1 = MiscUtil.getValidateError(bindingResult);
//
//             return new ResponseEntity<Result>(res1, HttpStatus.UNPROCESSABLE_ENTITY);
//         }
//
//         Result res = new Result(200, "ok");
//         return ResponseEntity.ok(res);
//     }
}