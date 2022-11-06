package com.lio.chatserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T>{

    @JsonFormat( pattern =  "yyyy-MM-dd" )
    private LocalDate timestamp;

    private HttpStatus httpStatus;

    private Integer status;

    private String message;

    private boolean ok;

    private T data;

}