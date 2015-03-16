package jug.ua.jsonb.impl.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import javax.json.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import java.io.*;
import java.nio.file.Files;

/**
 * Wrapper over Gson class to provide JSON-B compatible implementations
 * of {@link javax.json.bind.Jsonb} interface
 *
 * @author Oleg Tsal-Tsalko
 */
public class GsonJsonbWrapper implements Jsonb{

    private Gson gson;

    private static GsonBuilder newBuilderFromConfig (JsonbConfig config) {
        GsonBuilder builder = new GsonBuilder();

        Boolean pretty = (Boolean) config.getProperty(JsonbConfig.JSONB_TOJSON_FORMATTING);
        if (pretty!=null && Boolean.TRUE.equals(pretty)){
            builder.setPrettyPrinting();
        }
        return builder;
    }


    public GsonJsonbWrapper(JsonbConfig config) {
        /*
        In nutshel GsonBuilder itself should be modified in order to incorporate JsonbConfig,
        however in POC purposes we will do simple but not less ugly remapping
         */
        GsonBuilder builder = null;
        if (config!=null)
            builder = newBuilderFromConfig (config);
        else
            builder = new GsonBuilder();
        gson = builder.create();
    }

    @Override
    public <T> T fromJson(String str, Class<T> type) throws JsonbException {
        try {
            if (JsonValue.class.isAssignableFrom(type)){
                JsonReader jsonReader = Json.createReader(new StringReader(str));
                return (T)jsonReader.read();
            }else {
                return gson.fromJson(str, type);
            }
        } catch (JsonSyntaxException | IllegalArgumentException ex){
            throw new JsonbException("JSON not compatible with specified type:", ex);
        }
    }

    @Override
    public <T> T fromJson(Reader reader, Class<T> type) throws JsonbException {
        try{
            return gson.fromJson(reader, type);
        } catch (JsonIOException io){
            throw new JsonbException("Error occurred while reading the file:", io);
        } catch (JsonSyntaxException | IllegalArgumentException ex){
            throw new JsonbException("JSON not compatible with specified type:", ex);
        }
    }

    @Override
    public <T> T fromJson(InputStream stream, Class<T> type) throws JsonbException {
        return fromJson(new InputStreamReader(stream), type);
    }

    @Override
    public <T> T fromJson(File file, Class<T> type) throws JsonbException {
        try{
            return fromJson(Files.newBufferedReader(file.toPath()), type);
        } catch (IOException e) {
            throw new JsonbException("Error occurred while opening the file:", e);
        }
    }

    @Override
    public String toJson(Object object) throws JsonbException {
        return gson.toJson(object);
    }

    @Override
    public void toJson(Object object, File file) throws JsonbException {
        try {
            gson.toJson(object, Files.newBufferedWriter(file.toPath()));
        } catch (IOException e) {
            throw new JsonbException("Error occurred while opening the file:", e);
        }
    }

    @Override
    public void toJson(Object object, Writer writer) throws JsonbException {
        gson.toJson(object, writer);
    }

    @Override
    public void toJson(Object object, OutputStream stream) throws JsonbException {
        gson.toJson(object, new OutputStreamWriter(stream));
    }

    public static void main(String[] args){
        System.out.println(JsonStructure.class.isAssignableFrom(JsonObject.class));
    }

}
