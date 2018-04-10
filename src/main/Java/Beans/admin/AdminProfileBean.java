package Beans.admin;

import Domain.Profile;
import Domain.Role;
import Exceptions.ProfileException;
import Service.ProfileService;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class AdminProfileBean implements Serializable {

    @Inject
    private ProfileService profileService;

    private List<Role> roleDropDown = Arrays.asList(Role.values());

    private List<Profile> profiles;

    private Profile selectedProfile;
    private String selectedRole;

    private String filter;

    public List<Profile> getProfiles() {
        profiles = profileService.getProfiles();

        if (filter != null && filter.length() > 0) {
            List<Profile> filtered = new ArrayList<>();
            for (Profile profile : profiles) {
                if (profile.SearchProfile(filter.toLowerCase())) {
                    filtered.add(profile);
                }
            }
            return filtered;
        }
        else return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public void valueChanged(ValueChangeEvent event) {
        try {
            selectedRole = event.getNewValue().toString();
            if (selectedProfile != null) profileService.setRole(1, selectedProfile.getUserTag(), selectedRole);
        } catch (ProfileException e) {
            e.printStackTrace();
        }
    }

    public void selectProfile(SelectEvent event) {
        selectedProfile = (Profile) event.getObject();
    }

    public List<Role> getRoleDropDown() {
        return roleDropDown;
    }

    public void setRoleDropDown(List<Role> roleDropDown) {
        this.roleDropDown = roleDropDown;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
