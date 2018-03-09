package Service;

import DAO.KwetterDAO;

public class KwetterService {

    private KwetterDAO kwetterDAO;

    public KwetterService(KwetterDAO kwetterDAO) {

        this.kwetterDAO = kwetterDAO;
    }
}
