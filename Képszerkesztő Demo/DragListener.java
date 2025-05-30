import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DragListener extends MouseAdapter {
    private final ImageEditor frame;
    private final JComponent target;
    private final Point anchorPoint;
    private Point startPoint;
    private final InfoPanel infoPanel;
    private final String func;
    private final KepKezeles kezeles;
    private final KepFormazas hMirroring;
    private final KepFormazas vMirroring;
    private final KepFormazas invert;
    private final KepFormazas sepia;
    private final KepFormazas monochrome;

    public DragListener(ImageEditor frame, JComponent target, int x, int y, String name, String func, KepKezeles kezeles){
        this.frame = frame;
        this.target = target;
        this.infoPanel = new InfoPanel(frame, name, x, y, func);
        this.anchorPoint = new Point(x, y);
        this.func = func;
        this.kezeles = kezeles;
        this.vMirroring = new Tukrozes("vertical");
        this.hMirroring = new Tukrozes("horizontal");
        this.invert = new SzuroInvertalas();
        this.sepia = new SzuroSzepia();
        this.monochrome = new SzuroSzepia();
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
    public void mousePressed(MouseEvent e) {
        infoPanel.setVisible(false);
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e){

        target.setLocation(anchorPoint);

        if (e.getXOnScreen() > 280 && e.getXOnScreen() < 690 && e.getYOnScreen() > 550) {

            switch(func){
                case "vmirror":
                    frame.setImage(vMirroring.vegrehajtas(frame.getImage()));
                    frame.setScaledImage(vMirroring.vegrehajtas(frame.getScaledImage()));
                    frame.setNewImage(true);
                    kezeles.addAllapotok(frame.getImage());
                    kezeles.isMentve = false;
                    kezeles.setKep(frame.getImage());
                    break;
                case "hmirror":
                    frame.setImage(hMirroring.vegrehajtas(frame.getImage()));
                    frame.setScaledImage(hMirroring.vegrehajtas(frame.getScaledImage()));
                    frame.setNewImage(true);
                    kezeles.addAllapotok(frame.getImage());
                    kezeles.isMentve = false;
                    kezeles.setKep(frame.getImage());
                    break;
                case "invert":
                    frame.setImage(invert.vegrehajtas(frame.getImage()));
                    frame.setScaledImage(invert.vegrehajtas(frame.getScaledImage()));
                    frame.setNewImage(true);
                    kezeles.addAllapotok(frame.getImage());
                    kezeles.isMentve = false;
                    kezeles.setKep(frame.getImage());
                    break;
                case "sepia":
                    frame.setImage(sepia.vegrehajtas(frame.getImage()));
                    frame.setScaledImage(sepia.vegrehajtas(frame.getScaledImage()));
                    frame.setNewImage(true);
                    kezeles.addAllapotok(frame.getImage());
                    kezeles.isMentve = false;
                    kezeles.setKep(frame.getImage());
                    break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        System.out.println(e.getXOnScreen());
        if (startPoint == null) {
            return;
        }
        Point current = target.getLocation();
        int x = current.x + e.getX() - startPoint.x;
        int y = current.y + e.getY() - startPoint.y;
        target.setLocation(x, y);
    }
}