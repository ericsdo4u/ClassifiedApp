package africa.semicolon.classified.controller;

import africa.semicolon.classified.dtos.*;
import africa.semicolon.classified.exceptions.ClassifiedExceptions;
import africa.semicolon.classified.response.ApiResponse;
import africa.semicolon.classified.services.PostService;
import africa.semicolon.classified.services.UserService;
import africa.semicolon.classified.services.ViewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
@AllArgsConstructor
@RestController
public class UserController {
@Autowired
private UserService userService;
@Autowired
private PostService postService;
@Autowired
private ViewService viewService;
        @PostMapping("/register")
        public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
            try {
                var result = userService.register(registerRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
            }catch (ClassifiedExceptions e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }
        @PostMapping("/post-ads")
        public ResponseEntity<?> post_ads(@RequestBody PostRequest postRequest){
            try {
                var result = postService.postAds(postRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
            }catch (ClassifiedExceptions e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

    @PostMapping("/editPost")
    public ResponseEntity<?> edit(@RequestBody PostRequest postRequest){
        try {
            var result = postService.editPost(postRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (ClassifiedExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
        @PostMapping("/login")
        public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest){
            try {
                var result = userService.login(loginRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
            }catch (ClassifiedExceptions e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }
        @PostMapping("/logOut")
        public ResponseEntity<?> userLogOut(@RequestBody LogOutRequest logOutRequest) {
            try {
                var result = userService.logOut(logOutRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
            } catch (ClassifiedExceptions e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }
        @PostMapping("/deleteByUsername")
        public ResponseEntity<?> deleteUser(@RequestBody DeleteRequest deleteRequest) {
            try {
                var result = userService.deleteByUsername(deleteRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
            } catch (ClassifiedExceptions e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }
    @PostMapping("/viewPost")
    public ResponseEntity<?> viewed(@RequestBody ViewRequest viewRequest) {
        try {
            var result = viewService.viewPost(viewRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (ClassifiedExceptions e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/viewPost-by-title")
    public ResponseEntity<?> findByTitle(@RequestBody String title) {
        try {
            var result = postService.findAllBy(title);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (ClassifiedExceptions e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/view-by-username/{username}")
    public ResponseEntity<?> getAllPostForUser(@PathVariable("username") String username) {
        try {
            var result = postService.findAllPostBelongingTo(username);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (ClassifiedExceptions e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {
            var result = postService.findAllPost();
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (ClassifiedExceptions e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
