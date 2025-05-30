import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TesztKontrasztAllitas {
    public static void main(String[] args) throws Exception {
        BufferedImage bemenet = ImageIO.read(new File("images/oop_tg screen.png"));

        KepFormazas nagyobbKontraszt = new KontrasztAllitas(1.5);
        KepFormazas kisebbKontraszt = new KontrasztAllitas(0.7);

        BufferedImage nagyobbKimenet = nagyobbKontraszt.vegrehajtas(bemenet);
        BufferedImage kisebbKimenet = kisebbKontraszt.vegrehajtas(bemenet);

        ImageIO.write(nagyobbKimenet, "png", new File("nagyobbkontraszt_kimenet.png"));
        ImageIO.write(kisebbKimenet, "png", new File("kisebbkontraszt_kimenet.png"));
    }
}
