import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;

public class InfoPanel extends JPanel {
    private final ImageIcon img = new ImageIcon("images/Brown.png");
    private final JLabel label = new JLabel();
    private JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 70, 0);
    private KepFormazas fenyeroszint;
    private KepFormazas kontraszt;
    private BufferedImage filtered;
    private BufferedImage scaledFiltered;

    public InfoPanel(ImageEditor frame, String name, int x, int y, String func) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(1, 10)));
        setOpaque(false);
        setVisible(false);
        label.setText(name);
        label.setSize(100, 100);
        label.setVisible(true);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.decode("#F1F188"));
        label.setOpaque(false);
        label.setLayout(null);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
        slider.setVisible(false);
        slider.setOpaque(false);
        slider.setMaximumSize(new Dimension(80, 50));
        slider.setSize(new Dimension(80, 50));
        if (func.equals("contrast")) {
            slider.setMinimum(0);
            slider.setMaximum(100);
            slider.setValue(50);
        } else if (func.equals("light")) {
            slider.setMinimum(-50);
            slider.setMaximum(50);
            slider.setValue(0);
        }
        filtered = frame.getImage();
        scaledFiltered = frame.getScaledImage();
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(slider.getValue());
                if (frame.isNewImage()){
                    filtered = frame.getImage();
                    scaledFiltered = frame.getScaledImage();
                }
                if (func.equals("light")) {
                    fenyeroszint = new Fenyeroszint(slider.getValue());
                    frame.setImage(fenyeroszint.vegrehajtas(filtered));
                    frame.setScaledImage(fenyeroszint.vegrehajtas(scaledFiltered));
                } else if (func.equals("contrast")) {
                    kontraszt = new KontrasztAllitas((double) slider.getValue() /50);
                    frame.setImage(kontraszt.vegrehajtas(filtered));
                    frame.setScaledImage(kontraszt.vegrehajtas(scaledFiltered));
                }
            }
        });
        add(slider);
    }

    public boolean isSliderVisible() {
        return slider.isVisible();
    }

    public void setSliderVisible(boolean visible){
        slider.setVisible(visible);
    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        img.paintIcon(this, g, 0, 0);
    }
}
