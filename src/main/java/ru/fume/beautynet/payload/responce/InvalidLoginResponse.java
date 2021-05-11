package ru.fume.beautynet.payload.responce;

public class InvalidLoginResponse {
    private String username;
    private String passwword;

    public InvalidLoginResponse(){
        this.username = "Invalid Username";
        this.passwword = "Invalid Password";
    }
}
