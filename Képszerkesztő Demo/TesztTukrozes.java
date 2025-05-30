import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TesztTukrozes {
    public static void main(String[] args) throws Exception {
        BufferedImage bemenet = ImageIO.read(new File("images/oop_tg screen.png"));
        KepFormazas tukor = new Tukrozes("horizontal");
        BufferedImage kimenet = tukor.vegrehajtas(bemenet);
        ImageIO.write(kimenet, "png", new File("vizszintes_tukrozes_kimenet.png"));
        tukor = new Tukrozes("vertical");
        kimenet = tukor.vegrehajtas(bemenet);
        ImageIO.write(kimenet, "png", new File("fuggoleges_tukrozes_kimenet.png"));
    }
}

