import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class PressListener extends MouseAdapter {
    private final ImageEditor frame;
    private final InfoPanel infoPanel;
    private final String func;
    private final KepKezeles kezeles;

    public PressListener(ImageEditor frame, JComponent target, int x, int y, String name, String func, KepKezeles kezeles){
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
        infoPanel.setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(func) {
            case "save":
                kezeles.mentes();
                break;
            case "load":
                kezeles.betoltes();
                frame.setNewImage(true);
                frame.loadImage(kezeles.getKep());
                break;
            case "saveAs":
                kezeles.mentes_maskent();
                frame.setImage(kezeles.getKep());
                break;
            case "backward":
                kezeles.vissza_lepes();
                frame.setImage(kezeles.getKep());
                break;
            case "forward":
                kezeles.elore_lepes();
                frame.setImage(kezeles.getKep());
                break;
            case "delete":
                kezeles.torles();
                break;
        }
    }
}