import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KepKezeles {
    private BufferedImage kep;
    private String fajlnev;
    private JFrame stage;
    protected List<BufferedImage> allapotok = new ArrayList<>();
    protected boolean isMentve;
    protected int aktualisIndex = 0;
    private final ImageEditor editor;

    public KepKezeles(ImageEditor editor) {
        this.editor = editor;
    }

    public void setStage(JFrame stage) {
        this.stage = stage;
    }

    public void addAllapotok(BufferedImage bemenet) {
        if (aktualisIndex < allapotok.size() - 1) {
            allapotok.subList(aktualisIndex + 1, allapotok.size()).clear();
        }
        allapotok.add(bemenet);
        aktualisIndex = allapotok.size() - 1;
    }

    public void mentesVegrehajtva(BufferedImage legutobbi) {
        this.kep = legutobbi;
        this.allapotok.clear();
        this.allapotok.add(this.kep);
        this.isMentve = true;
        this.aktualisIndex = 0;
    }

    public void betoltes() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Képfájlok", "png", "jpg", "jpeg", "bmp", "gif"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Images"));
        fileChooser.showOpenDialog(stage);
        if (fileChooser.getSelectedFile() != null) {
            File file = fileChooser.getSelectedFile();
            try {
                this.kep = ImageIO.read(file);
                this.fajlnev = file.getAbsolutePath();
                mentesVegrehajtva(this.kep);
                editor.mainImg.setVisible(true);
                System.out.println("kep betoltes sikeres");
            } catch (IOException e) {
                System.out.println("kep betoltes hiba");
            }
        }
    }

    public void mentes() {
        try {
            File file;
            if (this.fajlnev != null) {
                file = new File(this.fajlnev);
                String kiterjesztes = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
                ImageIO.write(getKep(), kiterjesztes, file);
                mentesVegrehajtva(getKep());
                System.out.println("mentes sikeres");
            } else {
                System.out.println("kep meg nincs megnyitva akadas");
            }
        } catch (IOException e) {
            System.out.println("kep mentes hiba");
        }
    }

    public void mentes_maskent() {
        try {
            if (this.fajlnev != null) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "jpg", "jpeg"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("bmp", "bmp"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("gif", "gif"));
                fileChooser.showSaveDialog(stage);
                if (fileChooser.getSelectedFile() != null) {
                    File file = fileChooser.getSelectedFile();
                    String fajlNev = file.getAbsolutePath();
                    String valasztottExt = ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];
                    if (!fajlNev.toLowerCase().endsWith("." + valasztottExt)) {
                        fajlNev += "." + valasztottExt;
                    }
                    File outputFile = new File(fajlNev);
                    ImageIO.write(getKep(), valasztottExt, outputFile);
                    mentesVegrehajtva(getKep());
                    this.fajlnev = file.getAbsolutePath();
                    System.out.println("mentes maskent sikeres");
                }
            } else {
                System.out.println("kep meg nincs megnyitva akadas");
            }
        } catch (IOException e) {
            System.out.println("kep mentes maskent hiba");
        }
    }

    public void bezaras(JFrame parentFrame) {
        if (isMentve || fajlnev == null) {
            System.exit(0);
        }
        String[] options = {"Mentés", "Mentés másként", "Változtatások elvetése", "Mégse"};
        int valasztas = JOptionPane.showOptionDialog(
                parentFrame,
                "A módosítások nincsenek elmentve.",
                "Válassz!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[3] // alapértelmezett: Mégse
        );
        switch (valasztas) {
            case 0:
                mentes();
                System.exit(0);
                break;
            case 1:
                mentes_maskent();
                System.exit(0);
                break;
            case 2:
                System.exit(0);
                break;
            case 3: break;
        }
    }

    public void vissza_lepes() {
        if (this.fajlnev != null && this.allapotok.size() > 1 && aktualisIndex > 0) {
            aktualisIndex--;
            BufferedImage ujKep = getKep();
            if (ujKep != null) {
                editor.setImage(ujKep);
                editor.loadImage(ujKep);
                System.out.println("undo vegrehajtva.");
            }
        } else {
            System.out.println("nem lehet visszalepni.");
        }
    }

    public void elore_lepes() {
        if (this.fajlnev != null && this.allapotok.size() > 1 && aktualisIndex < allapotok.size() - 1) {
            aktualisIndex++;
            BufferedImage ujKep = getKep();
            if (ujKep != null) {
                editor.setImage(ujKep);
                editor.loadImage(ujKep);
                System.out.println("redo vegrehajtva.");
            }
        } else {
            System.out.println("nem lehet elorelepni.");
        }
    }

    public BufferedImage getKep() {
        if (aktualisIndex < 0 || aktualisIndex >= allapotok.size()) {
            return allapotok.getLast();
        }
        return allapotok.get(aktualisIndex);
    }

    public void torles() {
        this.kep = null;
        this.fajlnev = null;
        this.isMentve = true;
        this.allapotok.clear();
        this.aktualisIndex = 0;
        editor.setImage(null);
        editor.setScaledImage(null);
        editor.mainImg.setVisible(false);
    }

    public void setKep(BufferedImage kep) {
        this.kep = kep;
    }
}