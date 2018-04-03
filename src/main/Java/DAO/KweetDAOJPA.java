package DAO;

import Domain.Kweet;
import Domain.Profile;
import Domain.Trend;
import Exceptions.KweetException;
import iDAO.IKweetDAO;
import iDAO.IProfileDAO;
import iDAO.JPA;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@JPA
public class KweetDAOJPA implements IKweetDAO {

    @Inject
    @JPA
    private IProfileDAO profileDAO;

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;
    private CriteriaBuilder cb;
    private CriteriaQuery<Kweet> ck;
    private CriteriaQuery<Trend> ct;
    private Root<Kweet> kweetRoot;
    private Root<Trend> trendRoot;

    @PostConstruct
    public void init() {
        System.out.print("Initializing profiles query");

        cb = em.getCriteriaBuilder();
        setupKweetJPA();
        setupTrendJPA();
    }

    @Override
    public List<Kweet> getKweets() {
        setupKweetJPA();
        return em.createQuery(ck).getResultList();
    }

    @Override
    public List<Kweet> getKweetsFromUser(String ownerTag) {
        return profileDAO.getProfile(ownerTag).getKweets();
        //return em.createQuery(ck.where(cb.equal(kweetRoot.get("owner").get("userTag"), ownerTag))).getResultList();
        //return em.createQuery(ck.where(cb.equal(kweetRoot.get("owner"), profileDAO.getProfile(ownerTag)))).getResultList();
    }

    @Override
    public List<Kweet> getTenKweetsFromUser(String ownerTag) {
        setupKweetJPA();
        return em.createQuery(ck.where(cb.equal(kweetRoot.get("owner"), profileDAO.getProfile(ownerTag)))).setMaxResults(10).getResultList();
    }

    @Override
    public List<Trend> getTrends() {
        setupTrendJPA();
        return em.createQuery(ct).getResultList();
    }

    @Override
    public Trend getTrendByTag(String trendTag) {
        setupTrendJPA();
        try {
            Trend trend = em.createQuery(ct.where(cb.equal(trendRoot.get("trend"), trendTag))).getSingleResult();
            return trend;
        } catch (NoResultException ex) {
            return null;
        }
}

    @Override
    public List<Trend> getMostPopularTrends() {
        setupTrendJPA();
        return em.createQuery(ct.orderBy(cb.desc(cb.size(trendRoot.get("kweets"))))).setMaxResults(10).getResultList();
    }

    @Override
    public List<Kweet> getKweetsByTrend(Trend trend) {
        return getTrendByTag(trend.getTrend()).getKweets();
    }

    @Override
    public Kweet AddKweet(Profile owner, String message, List<Profile> mentions, List<Trend> trends) throws IllegalArgumentException, KweetException {

        if (!profileDAO.IsUniqueUserTag(owner.getUserTag())) {

            Kweet kweet = new Kweet(message, mentions);
            em.persist(kweet);

            for (Trend trend : trends) {
                Trend JPAtrend = getTrendByTag(trend.getTrend());
                JPAtrend.AddKweet(kweet);
                em.merge(JPAtrend);
            }

            Profile profile = profileDAO.getProfile(owner.getUserTag());
            profile.AddKweet(kweet);
            em.merge(profile);
            //profileDAO.AddKweet(owner.getUserTag(), kweet);

            return kweet;
        }

        return null;
    }

    @Override
    public Trend AddTrend(String name) {
        Trend trend = new Trend(name);

        em.persist(trend);

        return trend;
    }

    @Override
    public Kweet getKweetByID(int ID) {
        return em.find(Kweet.class, ID);
    }

    @Override
    public void RemoveKweet(Kweet kweet) {
        em.remove(kweet);
    }

    @Override
    public void AppreciateKweet(Kweet kweet, Profile profile) {
        kweet.AppreciateKweet(profile);
        em.merge(kweet);
    }

    @Override
    public void setupKweetJPA() {
        ck = cb.createQuery(Kweet.class);
        kweetRoot = ck.from(Kweet.class);
        ck.select(kweetRoot);
    }

    @Override
    public void setupTrendJPA() {
        ct = cb.createQuery(Trend.class);
        trendRoot = ct.from(Trend.class);
        ct.select(trendRoot);
    }
}
