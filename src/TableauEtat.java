import java.util.HashMap;
import java.util.Map;

public class TableauEtat {

    private Map<EtatID, Etat> tableau = new HashMap<>();

    public TableauEtat() {}
    public void addEtat(EtatID id, Etat etat) {tableau.put(id, etat);}
    public Etat get(EtatID id) {return tableau.get(id);}


}
