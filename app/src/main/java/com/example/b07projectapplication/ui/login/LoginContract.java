package com.example.b07projectapplication.ui.login;

public interface LoginContract {

    public interface Model{                 //Check for correct input using Firebase
        public void validateLogin();
    }

    public interface View{                  //Update the UI for the user
        public void sendUserToLogin();
        public void sendUserToOwner();
        public String getEmail();
        public String getPassword();
    }

    public interface Presenter{              //Check for correct code using classes and start transition functions
        public void login();
    }
}
