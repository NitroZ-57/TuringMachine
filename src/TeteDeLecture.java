public class TeteDeLecture {

    private int indice;
    private TuringMachine machine;

    public TeteDeLecture(TuringMachine machine) {
        this.machine = machine;
        indice = 0;
    }


    public void ecrire(SymboleDeBande ecrire) throws ExceptionBandeDepassee {
        machine.ecrire(indice, ecrire);
    }

    public void deplacerADroite() {
        indice++;
    }

    public void deplacerAGauche() {
        indice--;
    }

    public int position() {return indice;}

    public void etatCourant(EtatID etatSuivant) {
        machine.setEtat(etatSuivant);
    }
}
