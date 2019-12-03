package serialisation;

import java.io.*;
import java.util.ArrayList;

public class XmlSerializeImpl<T> implements Serialisation<T> {
    private Class<T> clases;

    public Class<T> getClases() {
        return clases;
    }

    public void setClases(Class<T> clases) {
        this.clases = clases;
    }

    public XmlSerializeImpl(Class<T> clases) {
        this.clases = clases;
    }

    @Override
    public void toFile(ArrayList<T> object, File file) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            int strObj = 0;
            writer.write(strObj);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<T> fromFile(File file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}