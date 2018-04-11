package DAO;

import Domain.Kweet;
import Domain.Profile;
import Domain.ProfileGroup;
import Domain.Role;
import Exceptions.ProfileException;
import iDAO.IProfileDAO;
import iDAO.JPA;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@JPA
public class ProfileDAOJPA implements IProfileDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;
    private CriteriaBuilder cb;
    private CriteriaQuery<ProfileGroup> cpg;
    private CriteriaQuery<Profile> cp;
    private Root<ProfileGroup> profileGroupRoot;
    private Root<Profile> profileRoot;

    @PostConstruct
    public void init() {
        System.out.print("Initializing profiles query");

        cb = em.getCriteriaBuilder();
        setupProfileGroupJPA();
        setupJPA();

        try {
            ProfileGroup profile = new ProfileGroup("PROFILE");
            em.persist(profile);
            em.persist(new ProfileGroup("MODERATOR"));
            ProfileGroup administrator = new ProfileGroup("ADMINISTRATOR");
            em.persist(administrator);

            Profile Jasper = new Profile("noreply@JaspervSon.nl", "JaspervSon", "@JaspervSon", "Jasper van Son", Role.ADMINISTRATOR, null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com");
            Jasper.setPassword(Jasper.getPassword());
            em.persist(Jasper);
            Jasper.setGroup(administrator);
            em.merge(Jasper);

            Profile Stefano = new Profile("noreply@StefanoVerhoeve.nl", "StefanoVerhoeve", "@StefanoVerhoeve", "Stefano Verhoeve", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com");
            Stefano.setPassword(Stefano.getPassword());
            em.persist(Stefano);
            Stefano.setGroup(profile);
            em.merge(Stefano);
            Profile Wazzup = new Profile("noreply@Wazzup.nl", "Wazzup", "@Wazzup", "Wazzup", null, "Wolla", "Tilburg", "lemonparty.org");
            Wazzup.setPassword(Wazzup.getPassword());
            em.persist(Wazzup);
            Wazzup.setGroup(profile);
            em.merge(Wazzup);
        } catch (ProfileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Profile> getProfiles() {
        setupJPA();
        return em.createQuery(cp).getResultList();
    }

    @Override
    public Profile getProfile(int id) {
        //return em.find(Profile.class, id);
        setupJPA();
        return em.createQuery(cp.where(cb.equal(profileRoot.get("ID"), id))).getSingleResult();
    }

    @Override
    public Profile getProfile(String userTag) {
        setupJPA();
        return em.createQuery(cp.where(cb.equal(profileRoot.get("userTag"), userTag))).getSingleResult();
    }

    @Override
    public Profile getProfileByUserName(String userName) {
        setupJPA();
        return em.createQuery(cp.where(cb.equal(profileRoot.get("userName"), userName))).getSingleResult();
    }

    @Override
    public Profile AddProfile(Profile profile) throws ProfileException {

        if (!IsUniqueUserTag(profile.getUserTag())) throw new ProfileException("UserTag is already in use");

        profile = new Profile(profile.getEmail(), profile.getPassword(), profile.getUserTag(), profile.getUserName(), profile.getProfilePicture(), profile.getBio(), profile.getLocation(), profile.getWebsiteURL());
        em.persist(profile);

        return profile;
    }

    @Override
    public Profile EditProfile(Profile profile) throws ProfileException {

        profile = getProfile(profile.getUserTag()).EditProfile(profile);
        profile = em.merge(profile);

        return profile;
    }

    @Override
    public boolean IsUniqueUserTag(String userTag) {
        try {
            setupJPA();
            return (em.createQuery(cp.where(cb.equal(profileRoot.get("userTag"), userTag))).getResultList().isEmpty());
        }
        catch(Exception ex) {
            System.out.print(ex.getMessage());
            return false;
        }
    }

    @Override
    public void FollowProfile(Profile myProfile, Profile otherProfile) {
        myProfile.FollowOther(otherProfile);
        em.merge(myProfile);
        em.merge(otherProfile);
    }

    @Override
    public void setRole(Profile profile, Role role) {
        profile.setRole(role);
        em.merge(profile);
    }

    @Override
    public List<Profile> getFollowing(String userTag) {
        return getProfile(userTag).getFollowing();
        //return em.createQuery(cp.where(profile.in(getProfile(userTag).getFollowing()))).getResultList();
    }

    @Override
    public List<Profile> getFollowers(String userTag) {
        return getProfile(userTag).getFollowers();
        //return em.createQuery(cp.where(profile.in(getProfile(userTag).getFollowing()))).getResultList();
    }

    @Override
    public void removeProfile(Profile profile) {
        em.remove(profile);
    }

    @Override
    public void removeKweet(String userTag, Kweet kweet) {
        Profile profile = getProfile(userTag);
        profile.RemoveKweet(kweet);
        em.merge(profile);
    }

    @Override
    public Profile getProfileByEmail(String email) {
        return em.find(Profile.class, email);
    }

    @Override
    public void Load() {
    }

    @Override
    public void AddKweet(String userTag, Kweet kweet) {
        Profile profile = getProfile(userTag);
        profile.AddKweet(kweet);

        em.merge(profile);
    }

    public void setupJPA() {
        cp = cb.createQuery(Profile.class);
        profileRoot = cp.from(Profile.class);
        cp.select(profileRoot);
    }

    public void setupProfileGroupJPA() {
        cpg = cb.createQuery(ProfileGroup.class);
        profileGroupRoot = cpg.from(ProfileGroup.class);
        cpg.select(profileGroupRoot);
    }
}
