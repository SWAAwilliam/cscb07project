package com.example.b07projectapplication.ui.login;

public interface LoginContract {

    public interface Model{                 //Check for correct input using Firebase
        public void validateAccount(String email, String password);
        public void setPresenter(LoginContract.Presenter presenter);
    }

    public interface View{                  //Update the UI for the user
        public void sendUserToCustomer();
        public void sendUserToOwner();
        public String getEmail();
        public String getPassword();
        public void displayMessage(boolean isOwner);
        public void displayError(String error);
    }

    public interface Presenter{             //Check for correct code using classes and start transition functions
        public void checkInput();
        public void successfulLogin(boolean isOwner);
        public void checkError(String error);
    }
}
