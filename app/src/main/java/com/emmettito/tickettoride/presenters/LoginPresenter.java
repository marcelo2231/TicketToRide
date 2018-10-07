package com.emmettito.tickettoride.presenters;

import com.emmettito.models.CommandModels.UserCommands.LogoutRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.communication.proxy.LoginProxy;

import java.util.Observable;
import java.util.Observer;

public class LoginPresenter extends Observable{


    public interface LoginView extends Observer{

        void login();

        void register();
    }

    public Result logout(String username) {
        LogoutRequest request = new LogoutRequest();
        request.setUsername(username);

        LoginProxy proxy = new LoginProxy();

        return proxy.logout(request);
    }
}
