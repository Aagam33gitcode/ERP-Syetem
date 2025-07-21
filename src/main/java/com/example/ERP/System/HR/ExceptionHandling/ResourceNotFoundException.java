package com.example.ERP.System.HR.ExceptionHandling;



public class ResourceNotFoundException extends RuntimeException{
public ResourceNotFoundException(String msg){
    super(msg);
}
public static class ResourceAlreadyExistsException extends ResourceNotFoundException{
    public ResourceAlreadyExistsException(String msg){
        super(msg);
    }
}

}

