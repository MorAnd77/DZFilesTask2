import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress save1 = new GameProgress(2, 1, 3, 4.5);
        GameProgress save2 = new GameProgress(1, 2, 3, 5.5);
        GameProgress save3 = new GameProgress(3, 3, 4, 6.5);

        File dir = new File("E://Games/savegames");

        try (FileOutputStream fos = new FileOutputStream(saveGame("E://Games/savegames/save1.dat"));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream(saveGame("E://Games/savegames/save2.dat"));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream(saveGame("E://Games/savegames/save3.dat"));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        zipFiles("E://Games/savegames/zip.zip", dir);

    }

    private static File saveGame(String pathFile) {
        File file = new File(pathFile);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void zipFiles(String name, File dir) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(name))) {
            for (File item : dir.listFiles()) {
                try (FileInputStream fis = new FileInputStream(item)) {
                    ZipEntry entry = new ZipEntry(item.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                item.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

