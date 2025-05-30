import java.awt.Color;
import java.awt.image.BufferedImage;

public class Fenyeroszint implements KepFormazas {

    private int erosseg;  // hozzaadott fenyero ertek
                          //fontos megjegyezni, hogy - erteket is kaphat (ilyenkor sotetebb)

    public Fenyeroszint(int erosseg) {
        this.erosseg = erosseg;
    }

    @Override
    public BufferedImage vegrehajtas(BufferedImage bemenet) {
        int szelesseg = bemenet.getWidth();
        int magassag = bemenet.getHeight();

        BufferedImage eredmeny = new BufferedImage(szelesseg, magassag, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < szelesseg; x++) {
            for (int y = 0; y < magassag; y++) {
                Color eredeti = new Color(bemenet.getRGB(x, y));

                int r = clamp(eredeti.getRed() + erosseg);
                int g = clamp(eredeti.getGreen() + erosseg);
                int b = clamp(eredeti.getBlue() + erosseg);

                Color ujSzin = new Color(r, g, b);
                eredmeny.setRGB(x, y, ujSzin.getRGB());
            }
        }

        return eredmeny;
    }

    // biztositja, hogy az RGB ertekek 0-255 kozott maradjanak
    private int clamp(int ertek) {
        return Math.max(0, Math.min(255, ertek));
    }
}
