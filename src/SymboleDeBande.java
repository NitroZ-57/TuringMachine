import java.util.Objects;

public class SymboleDeBande {

    private String symbole;
    public static final SymboleDeBande BLANC = new SymboleDeBande("B");

    public SymboleDeBande(String symbole) {
        this.symbole = symbole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymboleDeBande that = (SymboleDeBande) o;
        return Objects.equals(symbole, that.symbole);
    }

    @Override
    public String toString() {
        return symbole;
    }
}
