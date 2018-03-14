package REST;

import Domain.Profile;
import Exceptions.ProfileException;
import Service.ProfileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("/profile")
@Stateless
public class KwetterProfileEndpoint {

    @Inject
    ProfileService profileService;

    @GET
    @Path("/profiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfiles() {
        GenericEntity<List<Profile>> profiles = new GenericEntity<List<Profile>>(profileService.getProfiles()) {};

        return Response.ok(profiles).build();
    }

    @GET
    @Path("/profileByUserTag/{userTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileByUserTag(@PathParam("userTag") String userTag) {
        Profile profile =  profileService.getProfile(userTag);

        return Response.ok(profile).build();
    }

    @GET
    @Path("/profileByUserName/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileByUserName(@PathParam("userName") String userName) {
        Profile profile = profileService.getProfileByUserName(userName);

        return Response.ok(profile).build();
    }

    @POST
    @Path("/createProfile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Profile createProfile(Profile profile) throws ProfileException {
        return profileService.AddProfile(profile.getUserTag(), profile.getUserName(), profile.getProfilePicture(), profile.getBio(), profile.getLocation(), profile.getWebsiteURL());
    }


}
