package serialisation.dataTimeSerializer.XML;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import org.testng.Converter;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class XmlTimeSerialisation<HierarchicalStreamWriter, MarshallingContext, HierarchicalStreamReader> extends Converter {


    public void marshal(Object source, FileWriter writer, Marshaller context) throws JAXBException {
        LocalTime zdt = (LocalTime) source;
        context.marshal(zdt, writer);
    }


    public Object unmarshal(FileReader reader, UnmarshallingContext context) throws IOException {
        int tempChar;
        StringBuilder finalStr = new StringBuilder();
        while((tempChar = reader.read()) > 0){
            finalStr.append((char) tempChar);
        }
        return LocalDate.parse(finalStr.toString(), DateTimeFormatter.ofPattern("HH:mm"));
    }


    public boolean canConvert(Class type) {
        return type.equals(LocalTime.class);
    }
}