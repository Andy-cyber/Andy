package serialisation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
        try {
            JAXBContext contextObj = JAXBContext.newInstance(object.getClass());
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.marshal(object, new FileOutputStream(file.getName()));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toFile(T object, File file) {
        try {
            JAXBContext contextObj = JAXBContext.newInstance(object.getClass());
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.marshal(object, new FileOutputStream(file.getName()));
        } catch (IOException | JAXBException e) {
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