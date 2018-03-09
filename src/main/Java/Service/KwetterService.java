package Service;

import DAO.KweetDAO;
import DAO.ProfileDAO;

public class KwetterService {

    private ProfileDAO profileDAO;
    private KweetDAO kweetDAO;

    public KwetterService() {

        this.profileDAO = new ProfileDAO();
        this.kweetDAO = new KweetDAO();
    }


}
