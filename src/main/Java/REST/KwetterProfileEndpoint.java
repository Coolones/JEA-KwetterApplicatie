package REST;

import Domain.Models.ProfileModel;
import Domain.Profile;
import Exceptions.ProfileException;
import Service.ProfileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        return Response.ok(Enclose(profileService.getProfiles())).build();
    }

    @GET
    @Path("/profileByUserTag/{userTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileByUserTag(@PathParam("userTag") String userTag) {
        Profile profile =  profileService.getProfile(userTag);

        return Response.ok(profile).build();
    }

    /*@GET
    @Path("/profileByUserName/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileByUserName(@QueryParam("userName") String userName) {
        Profile profile = profileService.getProfileByUserName(userName);

        return Response.ok(profile).build();
    }*/

    @GET
    @Path("/{email}/logIn/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(@PathParam("email") String email, @PathParam("password") String password) {
        Profile profile = profileService.Authenticate(email, password);
        if (profile != null) {
            return Response.ok(profile).build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/createProfile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProfile(ProfileModel profile) {
        try {
            return Response.ok(profileService.AddProfile(new Profile(profile))).build();
        } catch (ProfileException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/editProfile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProfile(Profile profile) {
        try {
            profileService.EditProfile(profile);
            return Response.ok().build();
        } catch (ProfileException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{myTag}/follow/{followingTag}")
    public Response followUser(@PathParam("myTag") String myTag, @PathParam("followingTag") String followingTag) {
        profileService.FollowProfile(myTag, followingTag);
        return Response.ok(Enclose(profileService.getFollowing(myTag))).build();
    }

    @GET
    @Path("/getFollowing/{myTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowing(@PathParam("myTag") String myTag) {
        return Response.ok(Enclose(profileService.getFollowing(myTag))).build();
    }

    @GET
    @Path("/getFollowers/{myTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@PathParam("myTag") String myTag) {
        return Response.ok(Enclose(profileService.getFollowers(myTag))).build();
    }

    @PUT
    @Path("/{changerEmail}/changeRole/{userTag}/{role}")
    public Response changeRole(@PathParam("changerEmail") String changerEmail, @PathParam("userTag") String userTag, @PathParam("role") String role) {
        try {
            profileService.setRole(changerEmail, userTag, role);
            return Response.ok("Role changed").build();
        } catch (ProfileException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeProfile(@PathParam("id") int id) {
        profileService.removeProfile(id);
        return Response.ok("Profile removed").build();
    }

    private GenericEntity<List<Profile>> Enclose(List<Profile> profiles) {
        return new GenericEntity<List<Profile>>(profiles) {};
    }
}
