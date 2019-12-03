package serialisation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonSerializeImpl<T> implements Serialisation<T> {

    private Class<T> clases;
    public Class<T> getClases() {
        return clases;
    }
    public void setClases(Class<T> clases) {
        this.clases = clases;
    }

    public JsonSerializeImpl(Class<T> clases) {
        this.clases = clases;
    }

    @Override
    public void toFile(ArrayList<T> object, File file){
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileWriter fileWriter = new FileWriter(file);
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<T> fromFile(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, this.clases);
        return mapper.readValue(file, collectionType);
    }
}