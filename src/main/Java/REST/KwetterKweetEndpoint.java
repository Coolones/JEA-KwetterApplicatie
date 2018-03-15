package REST;

import Domain.Kweet;
import Domain.Profile;
import Exceptions.KweetException;
import Service.KweetService;
import Service.ProfileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Kweet")
@Stateless
public class KwetterKweetEndpoint {

    @Inject
    ProfileService profileService;

    @Inject
    KweetService kweetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweets() {
        GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetService.getKweets()) {};

        return Response.ok(kweets).build();
    }

    @GET
    @Path("/kweetsFromUser/{userTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweetsFromUser(@PathParam("userTag") String userTag) {
        if (!profileService.IsUniqueUserTag(userTag)) {
            GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetService.getKweetsFromUser(userTag)) {};

            return Response.ok(kweets).build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/tenKweetsFromUser/{userTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTenKweetsFromUser(@PathParam("userTag") String userTag) {
        if (!profileService.IsUniqueUserTag(userTag)) {
            GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetService.getTenKweetsFromUser(userTag)) {};

            return Response.ok(kweets).build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/mostPopularTrends")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMostPopularTrends() {
        GenericEntity<List<String>> trends = new GenericEntity<List<String>>(kweetService.getMostPopularTrends()) {};

        return Response.ok(trends).build();
    }

    @GET
    @Path("/kweetsByTrend/{trend}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kweet> getKweetsByTrend(@PathParam("trend") String trend) {
        return kweetService.getKweetsByTrend(trend);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddKweet(Kweet kweet) {
        try {
            return Response.ok(kweetService.AddKweet(kweet.getOwnerTag(), kweet.getKweet())).build();
        } catch (KweetException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }

    @GET
    @Path("/kweetByID/{ID}")
    public Kweet getKweetByID(int ID) {
        return kweetService.getKweetByID(ID);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void RemoveKweet(Kweet kweet) {
        kweetService.RemoveKweet(kweet);
    }
}
