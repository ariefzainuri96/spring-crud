package com.springcourse.simpleCrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;
}
