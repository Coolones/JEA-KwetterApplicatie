package REST;

import Domain.Profile;
import Service.ProfileService;
import iDAO.IProfileDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/profile")
@Stateless
public class KwetterProfileEndpoint {

    @Inject
    ProfileService profileService;

    @GET
    public List<Profile> getProfiles() {
        return profileService.getProfiles();
    }

    @GET
    @Path("/profileByUserTag")
    public Profile getProfileByUserTag(@QueryParam("userTag") String profile) {
        return profileService.getProfile(profile);
    };
}
