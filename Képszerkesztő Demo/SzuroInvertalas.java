import java.awt.Color;
import java.awt.image.BufferedImage;

public class SzuroInvertalas implements KepFormazas {

    @Override
    public BufferedImage vegrehajtas(BufferedImage bemenet) {
        int szelesseg = bemenet.getWidth();  // kep szelessege
        int magassag = bemenet.getHeight();  // kep magassaga

        BufferedImage eredmeny = new BufferedImage(szelesseg, magassag, BufferedImage.TYPE_INT_RGB); // uj kep letrehozasa

        for (int x = 0; x < szelesseg; x++) {
            for (int y = 0; y < magassag; y++) {
                Color eredeti = new Color(bemenet.getRGB(x, y));  // eredeti pixel szin (RGB bontas)

                int r = 255 - eredeti.getRed();     // piros komponens invertalasa
                int g = 255 - eredeti.getGreen();   // zold komponens invertalasa
                int b = 255 - eredeti.getBlue();    // kek komponens invertalasa

                Color invertalt = new Color(r, g, b);  // uj szin letrehozasa az invertalt ertekekbol

                eredmeny.setRGB(x, y, invertalt.getRGB());  // uj kepbe visszairas
            }
        }

        return eredmeny;
    }
}
