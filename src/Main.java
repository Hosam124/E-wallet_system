import exception.MaxAttemptsExceededException;
import model.EWalletSystem;
import service.imp.AppServiceImp;


public class Main {
    public static void main(String[] args) {
        try {
            EWalletSystem eWalletSystem = new EWalletSystem();
            new AppServiceImp(eWalletSystem).startProgram();
        }catch (MaxAttemptsExceededException e){
            System.out.println("Session ended: " + e.getMessage());
        }
    }
}