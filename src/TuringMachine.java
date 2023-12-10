import java.util.Vector;

public class TuringMachine {

    private Bande bande;
    private TeteDeLecture tete;
    private Etat etatCourant;
    private Etat etatAcceptant;
    private TableauEtat tableau;

    public TuringMachine(Vector<SymboleDeBande> bandeEntree, Etat q0, Etat etatAcceptant, TableauEtat tableau) {
        bande = new Bande(bandeEntree);
        etatCourant = q0;
        this.etatAcceptant = etatAcceptant;
        tete = new TeteDeLecture(this);
        this.tableau = tableau;
    }


    public void lancerMachine() throws ExceptionBandeDepassee {
        afficherSansTete();
        while(!etatAcceptant.getNom().equalsIgnoreCase(etatCourant.getNom())) {
            afficher();

            etatCourant.executerAction(bande.get(tete.position()), tete);

        }
        afficherSansTete();
    }


    public void ecrire(int indice, SymboleDeBande ecrire) throws ExceptionBandeDepassee {
        bande.ecrire(indice, ecrire);
    }

    public void setEtat(EtatID etatSuivant) {
        etatCourant = tableau.get(etatSuivant);
    }

    public void afficher() throws ExceptionBandeDepassee {
        System.out.print(etatCourant.getNom()+" : ");
        for(int i = 0; i < tete.position(); i++) {
            System.out.print(bande.get(i));
        }
        System.out.print("_");
        System.out.print(bande.get(tete.position()));
        System.out.print("_");
        for(int i = tete.position()+1; i < bande.size(); i++) {
            System.out.print(bande.get(i));
        }
        System.out.println();
    }

    public void afficherSansTete() throws ExceptionBandeDepassee {
        System.out.print(etatCourant.getNom()+" : ");
        for(int i = 0; i < bande.size(); i++) {
            System.out.print(bande.get(i));
        }
        System.out.println();
    }
}
