package com.happytech.ElectronicStore.helper;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String message;
     private HttpStatus status;
     private Boolean success;
}
