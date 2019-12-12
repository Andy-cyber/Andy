package serialisation;

import java.io.File;
import java.util.ArrayList;

public interface Serialisation<T>  {

    void toFile(ArrayList<T> object, File file);
    void toFile(T object, File file);
    ArrayList<T> fromFile(File file) throws Exception;
}