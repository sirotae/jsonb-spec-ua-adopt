package jug.ua.jsonb.impl.gson.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Olena_Syrota on 5/31/2015.
 */
public class DateTypeAdapter extends TypeAdapter<Date> {

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value != null) {
            // if time present then write ISO_DATE_TIME else ISO_DATE
            String format = null;
            Calendar cld = Calendar.getInstance();
            cld.setTime(value);
            if (cld.get(Calendar.HOUR)!=0
                    || cld.get(Calendar.MINUTE)!=0
                    || cld.get(Calendar.SECOND)!=0) {
                format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
            } else {
                format = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            out.value(sdf.format(value));
        }else{
            out.nullValue();
        }
    }

    @Override
    public Date read(com.google.gson.stream.JsonReader in) throws IOException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String object = in.nextString();
        if (object == null) {
            return null;
        } else {
            try {
                return sdfDate.parse(object);
            } catch (ParseException e) {
                try {
                    return sdfDateTime.parse(object);
                } catch (ParseException e1) {
                    throw new IllegalStateException(e1);
                }
            }
        }
    }
}
