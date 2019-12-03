package serialisation.dataTimeSerializer.JSON;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;



import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalTime;

public class JsonLocalTimeSerializer extends JsonSerializer<LocalTime> {
    @Override
    public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }


    //   @Override
  //  public void serialize(LocalTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
  //      jgen.writeString(value.toString());
   // }

}
