package teamf.controller.methods;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Jerre
 * Date: 13/03/13
 * Time: 1:45
 * To change this template use File | Settings | File Templates.
 */
public class JsonDateDeserializer implements JsonDeserializer<Date> {
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String s = json.getAsJsonPrimitive().getAsString();

        SimpleDateFormat format = new SimpleDateFormat("dd MMMMM yyyy - HH:mm:ss", Locale.ENGLISH);
        Date d = null;
        try {

             d = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return d;
    }
}