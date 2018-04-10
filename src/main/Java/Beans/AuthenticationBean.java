package Beans;

import Domain.Profile;
import Domain.Role;
import Service.ProfileService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class AuthenticationBean implements Serializable {

    @Inject
    private ProfileService profileService;

    private String email;
    private String password;
    private Profile administrator;

    public String authenticateAdmin() {

        // Preload database
        profileService.Load();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(email, password);

            Principal principal = request.getUserPrincipal();
            administrator = profileService.getProfileByEmail(principal.getName());

            //administrator = profileService.AuthenticateAdmin(email, password);
            if (administrator != null && (administrator.getRole() == Role.MODERATOR || administrator.getRole() == Role.ADMINISTRATOR)) return "admin/administrator.xhtml?faces-redirect=true";
            else return "error/invalidAccount.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            System.out.println(e.getStackTrace().toString());
            return "error/invalidAccount.xhtml?faces-redirect=true";
        }
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
