package serialisation.dataTimeSerializer.XML;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.sun.xml.internal.bind.v2.runtime.MarshallerImpl;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class XmlDataSerialisation implements Converter {

    public void marshal(Object source, FileWriter writer, Marshaller context) throws IOException, JAXBException {
        LocalDate zdt = (LocalDate) source;
        context.marshal(zdt, writer);
    }

    public Object unmarshal(FileReader reader, UnmarshallingContext context) throws IOException {
        int tempChar;
        StringBuilder finalStr = new StringBuilder();
        while((tempChar = reader.read()) > 0){
            finalStr.append((char) tempChar);
        }
        return LocalDate.parse(finalStr.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public boolean canConvert(Class type) {
        return type.equals(LocalDate.class);
    }

    @Override
    public Object convert(Object o) {
        return null;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}