package entiteti;

import entiteti.Reprodukovano;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-30T22:13:58")
@StaticMetamodel(Pesme.class)
public class Pesme_ { 

    public static volatile SingularAttribute<Pesme, String> naziv;
    public static volatile SingularAttribute<Pesme, Integer> id;
    public static volatile SingularAttribute<Pesme, String> url;
    public static volatile CollectionAttribute<Pesme, Reprodukovano> reprodukovanoCollection;

}