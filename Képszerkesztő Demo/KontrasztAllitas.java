import java.awt.Color;
import java.awt.image.BufferedImage;

public class KontrasztAllitas implements KepFormazas {

    private double faktor;  // kontraszt allitasi szorzo

    public KontrasztAllitas(double faktor) {
        this.faktor = faktor;
    }

    @Override
    public BufferedImage vegrehajtas(BufferedImage bemenet) {
        int szelesseg = bemenet.getWidth();
        int magassag = bemenet.getHeight();

        BufferedImage eredmeny = new BufferedImage(szelesseg, magassag, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < szelesseg; x++) {
            for (int y = 0; y < magassag; y++) {
                Color eredeti = new Color(bemenet.getRGB(x, y));

                int r = clamp((int)((eredeti.getRed() - 128) * faktor + 128));
                int g = clamp((int)((eredeti.getGreen() - 128) * faktor + 128));
                int b = clamp((int)((eredeti.getBlue() - 128) * faktor + 128));

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
