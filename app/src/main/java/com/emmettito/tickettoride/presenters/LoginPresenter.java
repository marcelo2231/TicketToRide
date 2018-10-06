package com.emmettito.tickettoride.presenters;

import java.util.Observable;
import java.util.Observer;

public class LoginPresenter extends Observable{


    public interface LoginView extends Observer{

        void login();

        void register();
    }
}
