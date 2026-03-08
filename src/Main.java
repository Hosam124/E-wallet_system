import model.EWalletSystem;
import service.imp.AppServiceImp;


public class Main {
    public static void main(String[] args) {
        EWalletSystem eWalletSystem = new EWalletSystem();
        new AppServiceImp(eWalletSystem).startProgram();
    }
}