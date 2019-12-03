package serialisation.dataTimeSerializer.JSON;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;


import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class JsonLocalDateSerializer extends JsonSerializer<LocalDate> {
//    @Override
//    public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
//        jgen.writeString(value.toString());
//    }
    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}