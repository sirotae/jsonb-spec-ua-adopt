package jug.ua.meetup;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.owlike.genson.Context;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olena_Syrota on 4/18/2015.
 *
 * scenario:
 *  custom ser/deser for point to be a JSON strings "5,8" rather than objects like {"x":5,"y":8}.
 */
public class Case7CustomSerializer {

    //GSON
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Point.class, new GsonPointAdapter ())
            .create();

    //JACKSON
    private ObjectMapper jackson = new ObjectMapper();

    //GENSON
    private Genson genson = new GensonBuilder()
            .withConverters(new GensonPersonConverter())
            .create();


    @Test
    public void serializePoint() throws JsonProcessingException {
        Point val = new Point(5,5);
        String exp = "\"5,5\"";

        String gsonRes = gson.toJson(val);
        assertEquals(exp, gsonRes);

        String jacksonRes = jackson.writeValueAsString(val);
        assertEquals(exp, jacksonRes);

        String gensonRes = genson.serialize(val);
        assertEquals(exp, gensonRes);

    }

    @Test
    public void deserializePoint() throws IOException {
        String val = "\"5,5\"";
        Point exp = new Point(5,5);

        Point gsonRes = gson.fromJson(val, Point.class);
        assertEquals(exp, gsonRes);

        Point jacksonRes = jackson.readValue(val, Point.class);
        assertEquals(exp, jacksonRes);

        Point gensonRes = genson.deserialize(val, Point.class);
        assertEquals(exp, gensonRes);

    }

    @JsonSerialize(using = JacksonPointSerializer.class)
    @JsonDeserialize(using=JacksonPointDeserializer.class)
    public static class Point {
        private int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;

            return true;
        }
    }

    public static class GsonPointAdapter extends TypeAdapter<Point> {
        public Point read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            String xy = reader.nextString();
            String[] parts = xy.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            return new Point(x, y);
        }

        public void write(JsonWriter writer, Point value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            String xy = value.getX() + "," + value.getY();
            writer.value(xy);
        }
    }


    public static class JacksonPointSerializer extends JsonSerializer<Point> {
        @Override
        public void serialize(Point value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException, JsonProcessingException {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getX());
            sb.append(",");
            sb.append(value.getY());
            jgen.writeString(sb.toString());
        }
    }

    public static class JacksonPointDeserializer extends JsonDeserializer<Point> {

        @Override
        public Point deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            String xy = jp.getValueAsString();
            String[] parts = xy.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            return new Point(x, y);
        }
    }

    public static class GensonPersonConverter implements com.owlike.genson.Converter<Point> {

        public void serialize(Point p, com.owlike.genson.stream.ObjectWriter writer, Context ctx) throws Exception {
            StringBuffer sb = new StringBuffer();
            sb.append(p.getX());
            sb.append(",");
            sb.append(p.getY());

            writer.writeString(sb.toString());
        }

        public Point deserialize(com.owlike.genson.stream.ObjectReader reader, Context ctx) throws Exception {
            String xy = reader.valueAsString();
            String[] parts = xy.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            return new Point(x, y);
        }
    }

}