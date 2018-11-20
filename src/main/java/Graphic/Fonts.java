package Graphic;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Fonts {
    private static ArrayList<Fonts> fontList = new ArrayList<>();
    private static String fontPath;

    public Fonts(String fontPath){
        final String currentPath = System.getProperty("user.dir");
        String filePath = currentPath + "\\fonts\\";
        Fonts.fontPath = filePath + fontPath;
        registerFont();
    }

    public void registerFont(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addFont(Fonts font){
        fontList.add(font);
    }
}
