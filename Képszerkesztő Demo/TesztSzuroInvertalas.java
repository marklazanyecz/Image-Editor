import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TesztSzuroInvertalas {
    public static void main(String[] args) throws Exception {
        BufferedImage bemenet = ImageIO.read(new File("images/oop_tg screen.png"));
        KepFormazas szuro = new SzuroInvertalas();
        BufferedImage kimenet = szuro.vegrehajtas(bemenet);
        ImageIO.write(kimenet, "png", new File("invertalas_kimenet.png"));
    }
}
