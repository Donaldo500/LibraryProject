package com.layer.notfound;

public class NotFound extends RuntimeException {
    public NotFound(String title){
        super(title);
    }
}
