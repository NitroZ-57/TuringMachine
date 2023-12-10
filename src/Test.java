import java.util.Random;
import java.util.Vector;

public class Test {

    public static void test() throws ExceptionBandeDepassee {

        Random r = new Random();

        // Création des symboles de bande
        SymboleDeBande zero = new SymboleDeBande("0");
        SymboleDeBande un = new SymboleDeBande("1");

        // Création des id d'états
        EtatID q0ID = new EtatID("q0");
        EtatID q1ID = new EtatID("q1");

        // Création du tableau d'états
        TableauEtat tableau = new TableauEtat();

        // Création état q0
        Etat q0 = new Etat(q0ID, tableau);
        q0.addAction(zero, new ActionTeteLecture(zero, true, q0ID));
        q0.addAction(un, new ActionTeteLecture(zero, true, q0ID));
        q0.addAction(SymboleDeBande.BLANC, new ActionTeteLecture(SymboleDeBande.BLANC, true, q1ID));

        // Création état q1
        Etat q1 = new Etat(q1ID, tableau);

        // Création bande
        Vector<SymboleDeBande> entree = new Vector<>();
        for(int i = 0; i < 10; i++) {
            SymboleDeBande s = (r.nextInt(2) == 0 ? zero : un);
            entree.add(s);
        }

        // Création de la machine
        TuringMachine machine = new TuringMachine(entree, q0, q1, tableau);
        machine.lancerMachine();

    }


}
