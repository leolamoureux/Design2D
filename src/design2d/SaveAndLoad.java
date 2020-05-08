package design2d;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoad implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    PlayerDrawing player = new PlayerDrawing();

    protected void saveData() {
        ObjectOutputStream out = null;
        try {
            final FileOutputStream fileOut = new FileOutputStream("player.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.flush();
            System.out.println("Data are saved");
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void loadData() {
        ObjectInputStream in = null;
        try {
            final FileInputStream fileIn = new FileInputStream("player.ser");
            in = new ObjectInputStream(fileIn);
            player = (PlayerDrawing) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Load data");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
