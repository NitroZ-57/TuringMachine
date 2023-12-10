public class ActionTeteLecture {

    private SymboleDeBande ecrire;
    private boolean droite;
    private EtatID etatSuivant;

    public ActionTeteLecture(SymboleDeBande aEcrire, boolean allerADroite, EtatID etatSuivant) {
        this.ecrire = aEcrire;
        this.droite = allerADroite;
        this.etatSuivant = etatSuivant;
    }

    public void executerAction(TeteDeLecture tete) throws ExceptionBandeDepassee {
        tete.ecrire(ecrire);
        if(droite) tete.deplacerADroite();
        else tete.deplacerAGauche();
        tete.etatCourant(etatSuivant);

    }


}
