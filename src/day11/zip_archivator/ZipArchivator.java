package day11.zip_archivator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipArchivator{

    public static void main(String[] args) throws IOException{
        StringBuilder cmd = new StringBuilder();
        int in;
        while ((in = System.in.read()) != '\n') {
            cmd.append((char) in);
        }
        String wholeCmd = cmd.toString();

        try {
            String command = wholeCmd.split(" ")[0];
            String path = wholeCmd.split(" ")[1];

            if (command.equals("zip")) {
                zip(path);
            } else if (command.equals("unzip")) {
                unzip(path);
            } else {
                System.err.println("Wrong Command");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong number of arguments");
        }
    }

    public static void zip(String path) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path));
        zipper(path, zos);
        zos.close();
    }

    public static void unzip(String path) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(path));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            if (entry.getName().equals(".DS_Store")) {
                continue;
            }
            File f = new File(entry.getName());

            if (!entry.isDirectory()) {
                java.io.FileWriter writer = new java.io.FileWriter(entry.getName());
                int ch;
                while ((ch = zis.read()) != -1) {
                    writer.write(ch);
                }
                writer.close();
            } else {
                f.mkdir();
            }
            zis.closeEntry();
        }
        zis.getNextEntry();
    }

    public static void zipper(String path, ZipOutputStream zos) throws IOException {
        File target = new File(path);
        if (target.getName().equals(".DS_Store")) {
            return;
        }
        if (target.isDirectory()) {
            zos.putNextEntry(new ZipEntry(path + "/"));
            zos.closeEntry();
            File[] files = target.listFiles();
            for (File file : files) {
                zipper(file.getPath(), zos);
            }
        } else {
            zos.putNextEntry(new ZipEntry(path));
            FileInputStream fis = new FileInputStream(path);
            int ch;
            while ((ch = fis.read()) != -1) {
                zos.write(ch);
            }
            fis.close();
            zos.closeEntry();
        }
    }
}
