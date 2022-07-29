
package com.restapi.portfolio.exception;

/**
 * @author "Fausto Stradiotto"
 */
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
