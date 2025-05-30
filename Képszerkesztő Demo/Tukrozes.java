import java.awt.image.BufferedImage;

public class Tukrozes implements KepFormazas {

    private String irany;  // két értéket kaphat: "horizontal" vagy "vertical"

    public Tukrozes(String irany) {
        this.irany = irany;
    }

    @Override
    public BufferedImage vegrehajtas(BufferedImage bemenet) {
        int szelesseg = bemenet.getWidth();
        int magassag = bemenet.getHeight();

        BufferedImage eredmeny = new BufferedImage(szelesseg, magassag, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < szelesseg; x++) {
            for (int y = 0; y < magassag; y++) {

                int eredetiPixel = bemenet.getRGB(x, y);

                if (irany.equalsIgnoreCase("horizontal")) {
                    // vizszintes tukrozes
                    eredmeny.setRGB(szelesseg - 1 - x, y, eredetiPixel);
                } else if (irany.equalsIgnoreCase("vertical")) {
                    // fuggoleges tukrozes
                    eredmeny.setRGB(x, magassag - 1 - y, eredetiPixel);
                }
            }
        }
        return eredmeny;
    }
}
