import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TesztSzuroSzepia {
    public static void main(String[] args) throws Exception {
        BufferedImage bemenet = ImageIO.read(new File("images/oop_tg screen.png"));
        KepFormazas szuro = new SzuroSzepia();
        BufferedImage kimenet = szuro.vegrehajtas(bemenet);
        ImageIO.write(kimenet, "png", new File("szepia_kimenet.png"));
    }
}
