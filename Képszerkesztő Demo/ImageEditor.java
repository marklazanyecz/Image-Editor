import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageEditor extends JFrame {
    protected JPanel mainImg;
    private BufferedImage image;
    private BufferedImage scaledImage;
    AffineTransform at = new AffineTransform();
    AffineTransformOp scaleOp;
    private boolean newImage = true;
    private final KepKezeles kezeles = new KepKezeles(this);

    public ImageEditor() {
        setTitle("NemTom Képszerkesztő /legjobb/");
        setSize(1000, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        ImageIcon bg = new ImageIcon("images/BG.png");
        setContentPane(new JLabel(bg));
        image = null;
        scaledImage = null;
        this.mainImg = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledImage, 0, 0, this);
            }
        };
        this.mainImg.setBounds(500, 250, 10, 10);
        this.mainImg.setVisible(false);
        add(this.mainImg);
        addPanel("images/Save.png", 8, 395, "<html><p style = 'text-align: center'>Mentés</html>",
                "press", "save");
        addPanel("images/Delete.png", 130, 390, "<html><p style = 'text-align: center'>Törlés" +
                "</html>", "press", "delete");
        addPanel("images/Backward.png", 282, 405, "<html><p style = 'text-align: center'>Visszalépés" +
                "</html>", "press", "backward");
        addPanel("images/Forward.png", 618, 405, "<html><p style = 'text-align: center'>Előrelépés</html>",
                "press", "forward");
        addPanel("images/Light.png", 825, 390, "<html><p style = 'text-align: center'>Fényerő" +
                "<br>Állítás<br></html>", "slider", "light");
        addPanel("images/Contrast.png", 915, 395, "<html><p style = 'text-align: center'>Kontraszt" +
                "<br>Állítás<br></html>", "slider", "contrast");
        addPanel("images/New.png", 0, 220, "<html><p style = 'text-align: center'>Kép" +
                "<br>Betöltése</html>", "press", "load");
        addPanel("images/SaveAs.png", 75, 225,"<html><p style = 'text-align: center'>Mentés" +
                "<br>Másként</html>", "press", "saveAs");
        addPanel("images/Sepia.png", 900, 260, "<html><p style = 'text-align: center'>Szépia" +
                "<br>Filter</html>", "drag", "sepia");
        addPanel("images/Invert.png", 820, 140, "<html><p style = 'text-align: center'>Invertálás" +
                "<br>Filter</html>", "drag", "invert");
        addPanel("images/HMirror.png", 820, 20, "<html><p style = 'text-align: center'>Vízszintes" +
                "<br>Tükrözés</p></html>", "drag", "hmirror");
        addPanel("images/VMirror.png", 900, 20, "<html><p style = 'text-align: center'>Függőleges" +
                "<br>Tükrözés</html>", "drag", "vmirror");

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                kezeles.bezaras(ImageEditor.this);
            }
        });
    }

    public void loadImage(BufferedImage bemenet) {
        scaledImage = null;
        mainImg.repaint();
        image = bemenet;
        at=new AffineTransform();
        if (image.getHeight() > image.getWidth()) {
            at.scale((double) 260/image.getHeight(), (double) 260/image.getHeight());
        } else {
            if (image.getHeight()*(430.0/image.getWidth()) > 260) {
                at.scale((double) 260/image.getHeight(), (double) 260/image.getHeight());
            } else {
                at.scale((double) 430/image.getWidth(), (double) 430/image.getWidth());
            }
        }
        scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        scaledImage = scaleOp.filter(image, scaledImage);
        this.mainImg.setBounds(500-(scaledImage.getWidth()/2), 250-(scaledImage.getHeight()/2), scaledImage.getWidth(), scaledImage.getHeight());
        mainImg.repaint();
    }

    public void addPanel(String image, int x, int y, String name, String type, String func) {
        JPanel panel = new JPanel() {
            final ImageIcon img = new ImageIcon(image);


            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                img.paintIcon(this, g, 0, 0);
            }
        };
        panel.setBounds(x, y, 100, 150);
        panel.setOpaque(false);
        add(panel);
        switch (type){
            case "drag":
                new DragListener(this, panel, x, y, name, func, kezeles);
                break;
            case "slider":
                new SliderListener(this, panel, x, y, name, func, kezeles);
                break;
            case "press":
                new PressListener(this, panel, x, y, name, func, kezeles);
        }

    }

    public void setScaledImage(BufferedImage scaledImage) {
        this.scaledImage = scaledImage;
        mainImg.repaint();
    }

    public BufferedImage getScaledImage() {
        return scaledImage;

    }

    public void setImage(BufferedImage img) {
        this.image = img;
        mainImg.repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setNewImage(boolean newImage) {
        this.newImage = newImage;
    }

    public boolean isNewImage() {
        if (newImage) {
            newImage = false;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageEditor::new);
    }
}