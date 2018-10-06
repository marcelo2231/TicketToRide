package com.emmettito.tickettoride.presenters;

import com.emmettito.models.Player;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class LoginPresenter extends Observable{


    public interface LoginView extends Observer{

        void login();

        void register();
    }
}
