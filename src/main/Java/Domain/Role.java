package Domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public enum Role implements Serializable {
    PROFILE,
    MODERATOR,
    ADMINISTRATOR
}
