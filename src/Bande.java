import java.util.Vector;

public class Bande {

    private Vector<SymboleDeBande> bande;

    public Bande(Vector<SymboleDeBande> bandeEntree) {
        bande = bandeEntree;
    }

    public SymboleDeBande get(int indice) throws ExceptionBandeDepassee {
        if(indice < 0)
            throw new ExceptionBandeDepassee("Depassement de la bande à gauche");
        else if(indice >= bande.size())
            bande.add(SymboleDeBande.BLANC);

        return bande.get(indice);
    }

    public void ecrire(int indice, SymboleDeBande symbole) throws ExceptionBandeDepassee {
        if(indice < 0 || indice > bande.size())
            throw new ExceptionBandeDepassee("Depassement de la bande pour écriture");
        bande.set(indice, symbole);

    }

    public int size() {
        return bande.size();
    }
}
