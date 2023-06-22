package com.codecrackers.bankapi.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionHandlerController {

        @ExceptionHandler(CustomerException.class)
        public ResponseEntity<ErrorResponse> handleCustomerException(CustomerException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus());
            return new ResponseEntity<>(errorResponse, ex.getStatus());
        }

        public static class ErrorResponse {
            private String message;
            private HttpStatus status;

            public ErrorResponse(String message, HttpStatus status) {
                this.message = message;
                this.status = status;
            }

            public String getMessage() {
                return message;
            }

            public HttpStatus getStatus() {
                return status;
            }
        }

}
