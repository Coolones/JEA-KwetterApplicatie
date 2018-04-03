package REST;

import Domain.Kweet;
import Domain.Profile;
import Domain.Trend;
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

@Path("/kweet")
@Stateless
public class KwetterKweetEndpoint {

    @Inject
    KweetService kweetService;

    @Inject
    ProfileService profileService;

    @GET
    @Path("/kweets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweets() {
        return Response.ok(EncloseKweets(kweetService.getKweets())).build();
    }

    @GET
    @Path("/search/{find}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchKweets(@PathParam("find") String find) {
        return Response.ok(EncloseKweets(kweetService.findKweets(find))).build();
    }

    @GET
    @Path("/timeline/{userTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeline(@PathParam("userTag") String userTag) {
        if (!profileService.IsUniqueUserTag(userTag)) {
            return Response.ok(EncloseKweets(kweetService.getTimelineFromUser(userTag))).build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/mentions/{userTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMentions(@PathParam("userTag") String userTag) {
        if (!profileService.IsUniqueUserTag(userTag)) {
            return Response.ok(EncloseKweets(kweetService.getMentionsOfUser(userTag))).build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/kweetsFromUser/{userTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweetsFromUser(@PathParam("userTag") String userTag) {
        if (!profileService.IsUniqueUserTag(userTag)) {
            return Response.ok(EncloseKweets(kweetService.getKweetsFromUser(userTag))).build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/tenKweetsFromUser/{userTag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTenKweetsFromUser(@PathParam("userTag") String userTag) {
        if (!profileService.IsUniqueUserTag(userTag)) {
            return Response.ok(EncloseKweets(kweetService.getTenKweetsFromUser(userTag))).build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/mostPopularTrends")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMostPopularTrends() {
        return Response.ok(EncloseTrends(kweetService.getMostPopularTrends())).build();
    }

    @GET
    @Path("/kweetsByTrend/{trend}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweetsByTrend(@PathParam("trend") String trend) {
        return Response.ok(EncloseKweets(kweetService.getKweetsByTrend("#"+ trend))).build();
    }

    @POST
    @Path("/createKweet")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddKweet(Kweet kweet) {
        try {
            return Response.ok(kweetService.AddKweet(kweet.getOwner().getUserTag(), kweet.getKweet())).build();
        } catch (KweetException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{myID}/appreciateKweet/{kweetID}")
    public Response apreciateKweet(@PathParam("myID") int myID, @PathParam("kweetID") int kweetID) {
        kweetService.AppreciateKweet(myID, kweetID);
        return Response.ok().build();
    }

    @GET
    @Path("/kweetByID/{ID}")
    public Kweet getKweetByID(int ID) {
        return kweetService.getKweetByID(ID);
    }

    @DELETE
    @Path("/{removerID}/{ID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void RemoveKweet(@PathParam("removerID") int removerID, @PathParam("ID") int ID) {
        kweetService.RemoveKweet(removerID, ID);
    }

    private GenericEntity<List<Kweet>> EncloseKweets(List<Kweet> kweets) {
        return new GenericEntity<List<Kweet>>(kweets) {};
    }

    private GenericEntity<List<Trend>> EncloseTrends(List<Trend> trends) {
        return new GenericEntity<List<Trend>>(trends) {};
    }
}
