
package com.restapi.portfolio.dto;

/**
 * @author "Fausto Stradiotto"
 */
public class Msg {
    private String message;

    public Msg() {
    }

    public Msg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
