import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class LecteurMachineTuring {

    public static TuringMachine lireMachine(String filename) {
        File fichier = new File(filename);

        try {
            FileInputStream fileInputStream = new FileInputStream(fichier);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            Map<String, SymboleDeBande> alphabet = getAlphabet(reader.readLine());
            System.out.println("===");

            Vector<SymboleDeBande> bande = getBande(reader.readLine(), alphabet);
            System.out.println("===");

            Map<String, EtatID> etatsID = getEtatID(reader.readLine());
            System.out.println("===");

            TableauEtat tableau = getTableau(reader.readLine(), etatsID, alphabet);
            System.out.println("===");

            EtatID etatDebut = getEtatDebut(reader.readLine(), etatsID);
            System.out.println("===");

            EtatID etatFinal = getEtatAcceptant(reader.readLine(), etatsID);
            System.out.println("===");

            TuringMachine machine = new TuringMachine(bande, etatDebut, etatFinal, tableau);
            return machine;


    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExceptionFormatNonReconnu e) {
            e.printStackTrace();
        }

        return null;
    }


    private static Map<String, SymboleDeBande> getAlphabet(String ligne) throws ExceptionFormatNonReconnu {
        Map<String, SymboleDeBande> alphabet = new HashMap<>();
        String egal[] = ligne.split("=");

        if(!egal[0].equalsIgnoreCase("a"))
            throw new ExceptionFormatNonReconnu("alphabet non reconnu");

        String a = egal[1];
        String valeurs[] = a.split(";");
        for(String val : valeurs) {
            alphabet.put(val, new SymboleDeBande(val));
            System.out.println(val);
        }
        alphabet.put("B", SymboleDeBande.BLANC);

        return alphabet;
    }

    private static Vector<SymboleDeBande> getBande(String ligne, Map<String, SymboleDeBande> alphabet) throws ExceptionFormatNonReconnu {
        Vector<SymboleDeBande> bande = new Vector<>();
        String egal[] = ligne.split("=");

        if(!egal[0].equalsIgnoreCase("b"))
            throw new ExceptionFormatNonReconnu("bande non reconnu");

        String b = egal[1];
        for(char val : b.toCharArray()) {
            bande.add(alphabet.get(val+""));
            System.out.println(val);
        }

        return bande;
    }

    private static Map<String, EtatID> getEtatID(String ligne) throws ExceptionFormatNonReconnu {
        Map<String, EtatID> etats = new HashMap<>();
        String egal[] = ligne.split("=");

        if(!egal[0].equalsIgnoreCase("e"))
            throw new ExceptionFormatNonReconnu("etats non reconnu");

        String e = egal[1];
        String valeurs[] = e.split(";");
        for(String val : valeurs) {
            etats.put(val, new EtatID(val));
            System.out.println(val);
        }

        return etats;
    }

    private static TableauEtat getTableau(String ligne, Map<String, EtatID> etatsID, Map<String, SymboleDeBande> alphabet) throws ExceptionFormatNonReconnu, IOException {
        TableauEtat tab = new TableauEtat();
        String egal[] = ligne.split("=");

        if(!egal[0].equalsIgnoreCase("t"))
            throw new ExceptionFormatNonReconnu("tableau d'etat non reconnu");

        FileInputStream fileInputStream = new FileInputStream(egal[1]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

        // lecture de l'entête des colonnes
        String entete = reader.readLine();
        Map<Integer, SymboleDeBande> colonnes = new HashMap<>();
        String cols[] = entete.split(";");
        for(int i = 0; i < cols.length; i++) {
            String col = cols[i].trim();
            colonnes.put(i, alphabet.get(col));
        }

        // parcours des lignes
        String ligneTab = reader.readLine();
        while(ligneTab != null) {
            String ligneInfos[] = ligneTab.split(";");

            EtatID id = etatsID.get(ligneInfos[0].trim());
            Etat e = new Etat(id, tab);

            // parcours des cellules
            for(int i = 1; i < ligneInfos.length; i++) {
                String actionInfo[] = ligneInfos[i].split(",");
                for(String a : actionInfo) System.out.println(a.trim());
                if(!actionInfo[0].trim().equalsIgnoreCase("-")) {
                    ActionTeteLecture action = new ActionTeteLecture(alphabet.get(actionInfo[0].trim()), actionInfo[1].trim().equalsIgnoreCase("R"), etatsID.get(actionInfo[2].trim()));
                    e.addAction(colonnes.get(i - 1), action);
                }
                System.out.println();
            }
            ligneTab = reader.readLine();
        }


        return tab;
    }

    public static EtatID getEtatDebut(String ligne, Map<String, EtatID> etatsID) throws ExceptionFormatNonReconnu {
        String egal[] = ligne.split("=");

        if(!egal[0].equalsIgnoreCase("s"))
            throw new ExceptionFormatNonReconnu("etat de début non reconnu");

        return etatsID.get(egal[1].trim());
    }

    public static EtatID getEtatAcceptant(String ligne, Map<String, EtatID> etatsID) throws ExceptionFormatNonReconnu {
        String egal[] = ligne.split("=");

        if(!egal[0].equalsIgnoreCase("f"))
            throw new ExceptionFormatNonReconnu("etat de début non reconnu");

        return etatsID.get(egal[1].trim());
    }
}