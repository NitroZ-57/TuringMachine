import java.util.HashMap;
import java.util.Map;

public class Etat {

    private EtatID id;
    private Map<SymboleDeBande, ActionTeteLecture> actions = new HashMap<SymboleDeBande, ActionTeteLecture>();

    public Etat(EtatID id, TableauEtat tableau) {this.id = id; tableau.addEtat(id, this);}
    public Etat addAction(SymboleDeBande symbole, ActionTeteLecture action) {actions.put(symbole, action); return this;}

    public void executerAction(SymboleDeBande symboleDeBande, TeteDeLecture tete) throws ExceptionBandeDepassee {
        actions.get(symboleDeBande).executerAction(tete);
    }

    public String getNom() {
        return id.getNom();
    }
}
