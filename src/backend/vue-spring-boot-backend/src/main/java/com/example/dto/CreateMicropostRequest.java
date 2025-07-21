package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for creating a new micropost")
public class CreateMicropostRequest {
    
    @Schema(description = "Title of the micropost", example = "My first post", required = true)
    private String title;
    
    @Schema(description = "ID of the user creating the post", example = "1", required = true)
    private Long userId;
    
    public CreateMicropostRequest() {}
    
    public CreateMicropostRequest(String title, Long userId) {
        this.title = title;
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
