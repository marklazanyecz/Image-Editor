import java.awt.Color;
import java.awt.image.BufferedImage;

public class SzuroSzepia implements KepFormazas {

    @Override
    public BufferedImage vegrehajtas(BufferedImage bemenet) {
        int szelesseg = bemenet.getWidth();
        int magassag = bemenet.getHeight();

        BufferedImage eredmeny = new BufferedImage(szelesseg, magassag, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < szelesseg; x++) {
            for (int y = 0; y < magassag; y++) {
                Color eredeti = new Color(bemenet.getRGB(x, y));

                int r = eredeti.getRed();
                int g = eredeti.getGreen();
                int b = eredeti.getBlue();

                // szepia alakitasi kepletek
                int ujR = clamp((int)(0.393 * r + 0.769 * g + 0.189 * b));
                int ujG = clamp((int)(0.349 * r + 0.686 * g + 0.168 * b));
                int ujB = clamp((int)(0.272 * r + 0.534 * g + 0.131 * b));

                Color szepia = new Color(ujR, ujG, ujB);
                eredmeny.setRGB(x, y, szepia.getRGB());
            }
        }

        return eredmeny;
    }

    // segedfuggveny a 0-255 tartomany biztositasara
    private int clamp(int ertek) {
        return Math.min(255, Math.max(0, ertek));
    }
}