package com.example.controller;

import com.example.entity.Micropost;
import com.example.entity.User;
import com.example.repository.MicropostRepository;
import com.example.repository.UserRepository;
import com.example.dto.CreateMicropostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/microposts")
@Tag(name = "Microposts", description = "Micropost management API")
@CrossOrigin(origins = "http://localhost:5173")
public class MicropostController {
    
    @Autowired
    private MicropostRepository micropostRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    @Operation(summary = "Get all microposts", description = "Retrieve all microposts with user information")
    public List<Micropost> getAllMicroposts() {
        return micropostRepository.findAllWithUser();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get micropost by ID", description = "Retrieve a specific micropost by its ID")
    public ResponseEntity<Micropost> getMicropostById(@PathVariable Long id) {
        Optional<Micropost> micropost = micropostRepository.findById(id);
        return micropost.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Create new micropost", description = "Create a new micropost")
    public ResponseEntity<?> createMicropost(@RequestBody CreateMicropostRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("User not found"));
        }
        
        Micropost micropost = new Micropost(request.getTitle(), request.getUserId());
        Micropost savedMicropost = micropostRepository.save(micropost);
        
        savedMicropost.setUser(userOpt.get());
        
        return ResponseEntity.ok(savedMicropost);
    }
    
    public static class ErrorResponse {
        private String error;
        
        public ErrorResponse(String error) {
            this.error = error;
        }
        
        public String getError() {
            return error;
        }
        
        public void setError(String error) {
            this.error = error;
        }
    }
}
