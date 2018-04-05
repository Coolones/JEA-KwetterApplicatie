package Beans;

import Domain.Profile;
import Service.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class AuthenticationBean implements Serializable {

    @Inject
    private ProfileService profileService;

    private String email;
    private String password;
    private Profile administrator;

    public void authenticateAdmin() {
        administrator =  profileService.AuthenticateAdmin(email, password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getAdministrator() {
        return administrator;
    }
}
