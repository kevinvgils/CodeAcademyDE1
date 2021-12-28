import javafx.application.Application;
import logic.CertificateController;

public class App {
    public static void main(String[] args) throws Exception {
        CertificateController certificate = new CertificateController();

        certificate.addCertificate(3, "Thijs Poepei");
        Application.launch(gui.MasterView.class);
    }
}
