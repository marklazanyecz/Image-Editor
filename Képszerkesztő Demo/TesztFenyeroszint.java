import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TesztFenyeroszint {
    public static void main(String[] args) throws Exception {
        BufferedImage bemenet = ImageIO.read(new File("images/oop_tg screen.png"));
        KepFormazas vilagos = new Fenyeroszint(30);  // vilagositas
        KepFormazas sotet = new Fenyeroszint(-40);  // sotetites

        // szuro alkalmazasa
        BufferedImage kimenetSotet = sotet.vegrehajtas(bemenet);
        BufferedImage kimenetVilagos = vilagos.vegrehajtas(bemenet);

        // kimeneti kep fajlba mentese
        ImageIO.write(kimenetSotet, "png", new File("sotet_kimenet.png"));
        ImageIO.write(kimenetVilagos, "png", new File("vilagos_kimenet.png"));
    }
}
