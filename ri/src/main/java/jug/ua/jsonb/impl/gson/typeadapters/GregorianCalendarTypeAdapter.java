package jug.ua.jsonb.impl.gson.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Olena_Syrota on 5/31/2015.
 */
public class GregorianCalendarTypeAdapter extends TypeAdapter<GregorianCalendar> {

    @Override
    public void write(JsonWriter out, GregorianCalendar cld) throws IOException {
        if (cld != null) {
            // if time present then write ISO_DATE_TIME else ISO_DATE
            String format = null;
            if (cld.get(Calendar.HOUR)!=0
                    || cld.get(Calendar.MINUTE)!=0
                    || cld.get(Calendar.SECOND)!=0) {
                format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
            } else {
                format = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            out.value(sdf.format(cld.getTime()));
        }else{
            out.nullValue();
        }
    }

    @Override
    public GregorianCalendar read(com.google.gson.stream.JsonReader in) throws IOException {
        GregorianCalendar cld = (GregorianCalendar) GregorianCalendar.getInstance();
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String object = in.nextString();
        if (object == null) {
            return null;
        } else {
            try {
                Date date = sdfDate.parse(object);
                cld.setTime(date);
                return cld;
            } catch (ParseException e) {
                try {
                    Date datetime = sdfDateTime.parse(object);
                    cld.setTime(datetime);
                    return cld;
                } catch (ParseException e1) {
                    throw new IllegalStateException(e1);
                }
            }
        }
    }
}
