public class Main {

    public static void main(String[] args) throws ExceptionBandeDepassee {
        //Test.test();
        TuringMachine machine = LecteurMachineTuring.lireMachine("input/input2.txt");
        machine.lancerMachine();
    }
}
