package com.emmettito.models.CommandModels;

import com.emmettito.models.CommandModels.UserCommands.*;

public class UserCommand {
    /** Variables **/
    LoginCommandModel loginCommandModel;
    LogoutCommandModel logoutCommandModel;
    RegisterCommandModel registerCommandModel;

    /** Setters **/
    public void setLoginCommandModel(LoginCommandModel loginCommandModel) {
        this.loginCommandModel = loginCommandModel;
    }

    public void setLogoutCommandModel(LogoutCommandModel logoutCommandModel) {
        this.logoutCommandModel = logoutCommandModel;
    }

    public void setRegisterCommandModel(RegisterCommandModel registerCommandModel) {
        this.registerCommandModel = registerCommandModel;
    }
    
    /** Getters **/
    public LoginCommandModel getLoginCommandModel() {
        return loginCommandModel;
    }

    public LogoutCommandModel getLogoutCommandModel() {
        return logoutCommandModel;
    }

    public RegisterCommandModel getRegisterCommandModel() {
        return registerCommandModel;
    }
}
