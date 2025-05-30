import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class SliderListener extends MouseAdapter {
    private final ImageEditor frame;
    private final InfoPanel infoPanel;
    private final String func;
    private final KepKezeles kezeles;
    private KepFormazas fenyero;
    private KepFormazas kontraszt;

    public SliderListener(ImageEditor frame, JComponent target, int x, int y, String name, String func, KepKezeles kezeles) {
        this.frame = frame;
        this.infoPanel = new InfoPanel(frame, name, x, y, func);
        this.func = func;
        this.kezeles = kezeles;
        target.add(infoPanel);
        target.addMouseListener(this);
        target.addMouseMotionListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e){
        infoPanel.setVisible(true);
    }

    @Override
    public void mouseExited(MouseEvent e){
        if(!infoPanel.isSliderVisible()){
            infoPanel.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getPoint().getY()>100){
            infoPanel.setSliderVisible(!infoPanel.isSliderVisible());
            kezeles.addAllapotok(frame.getImage());
            frame.setNewImage(true);
        }
    }
}
