package jug.ua.jsonb.impl.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import jug.ua.jsonb.impl.gson.typeadapters.GregorianCalendarTypeAdapter;
import jug.ua.jsonb.impl.gson.typeadapters.DateTypeAdapter;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

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

        Optional<Object> pretty = config.getProperty(JsonbConfig.JSONB_FORMATTING);
        if (pretty.isPresent()){
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

        gson = builder
               .registerTypeAdapterFactory(this::customTypeAdapterForOptional)
               .registerTypeAdapter(Date.class, new DateTypeAdapter())
               .registerTypeHierarchyAdapter(Calendar.class, new GregorianCalendarTypeAdapter())
               .create();
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
    public <T> T fromJson(String str, Type type) throws JsonbException {
        try {
            if (JsonValue.class.isAssignableFrom(TypeToken.get(type).getRawType())){
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
    public <T> T fromJson(Readable readable, Class<T> type) throws JsonbException {
        try{
            if (readable instanceof Reader) {
                return gson.fromJson((Reader) readable, type);
            }else{
                throw new UnsupportedOperationException();
            }
        } catch (JsonIOException io){
            throw new JsonbException("Error occurred while reading the file:", io);
        } catch (JsonSyntaxException | IllegalArgumentException ex){
            throw new JsonbException("JSON not compatible with specified type:", ex);
        }
    }

    @Override
    public <T> T fromJson(Readable readable, Type runtimeType) throws JsonbException {
        try{
            if (readable instanceof Reader) {
                return gson.fromJson((Reader)readable, runtimeType);
            }else{
                throw new UnsupportedOperationException();
            }
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
    public <T> T fromJson(InputStream stream, Type runtimeType) throws JsonbException {
        return fromJson(new InputStreamReader(stream), runtimeType);
    }

    @Override
    public String toJson(Object object) throws JsonbException {
        if (object instanceof JsonValue){
            return object.toString();
        }else{
            return gson.toJson(object);
        }
    }

    @Override
    public String toJson(Object object, Type type) throws JsonbException {
        if (object instanceof JsonValue){
            return object.toString();
        }else{
            return gson.toJson(object, type);
        }
    }

    @Override
    public void toJson(Object object, Appendable appendable) throws JsonbException {
        gson.toJson(object, appendable);
    }

    @Override
    public void toJson(Object object, Type runtimeType, Appendable appendable) throws JsonbException {
        gson.toJson(object, runtimeType, appendable);
    }

    @Override
    public void toJson(Object object, OutputStream stream) throws JsonbException {
        gson.toJson(object, new OutputStreamWriter(stream));
    }

    @Override
    public void toJson(Object object, Type runtimeType, OutputStream stream) throws JsonbException {
        gson.toJson(object, runtimeType, new OutputStreamWriter(stream));
    }

    private <T> TypeAdapter<T> customTypeAdapterForOptional(Gson gson, TypeToken<T> type) {
        if (type.getRawType() == Optional.class){
            return new OptionalTypeAdapter(gson);
        }else if (type.getRawType() == OptionalInt.class){
            return (TypeAdapter<T>)new OptionalIntTypeAdapter(gson);
        }else if (type.getRawType() == OptionalLong.class){
            return (TypeAdapter<T>)new OptionalLongTypeAdapter(gson);
        }else if (type.getRawType() == OptionalDouble.class){
            return (TypeAdapter<T>)new OptionalDoubleTypeAdapter(gson);
        }else{
            return null;
        }
    }

    static class OptionalTypeAdapter<T> extends TypeAdapter<Optional<T>>{

        private Gson gson;

        OptionalTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override
        public void write(JsonWriter out, Optional<T> value) throws IOException {
            if (value != null && value.isPresent()) {
                TypeAdapter<T> typeAdapter = gson.getAdapter(new TypeToken<T>() {});
                typeAdapter.write(out, value.get());
            }else{
                out.nullValue();
            }
        }

        @Override
        public Optional<T> read(com.google.gson.stream.JsonReader in) throws IOException {
            TypeAdapter<T> typeAdapter = gson.getAdapter(new TypeToken<T>() {});
            T object = typeAdapter.read(in);
            if (object == null) {
                return Optional.empty();
            } else {
                return Optional.of(object);
            }
        }
    }

    static class OptionalIntTypeAdapter extends TypeAdapter<OptionalInt>{

        private Gson gson;

        OptionalIntTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override
        public void write(JsonWriter out, OptionalInt value) throws IOException {
            if (value != null && value.isPresent()) {
                gson.getAdapter(int.class).write(out, value.getAsInt());
            }else{
                out.nullValue();
            }
        }

        @Override
        public OptionalInt read(com.google.gson.stream.JsonReader in) throws IOException {
            TypeAdapter<Integer> typeAdapter = gson.getAdapter(int.class);
            Integer object = typeAdapter.read(in);
            if (object == null) {
                return OptionalInt.empty();
            } else {
                return OptionalInt.of(object);
            }
        }
    }

    static class OptionalLongTypeAdapter extends TypeAdapter<OptionalLong>{

        private Gson gson;

        OptionalLongTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override
        public void write(JsonWriter out, OptionalLong value) throws IOException {
            if (value != null && value.isPresent()) {
                gson.getAdapter(long.class).write(out, value.getAsLong());
            }else{
                out.nullValue();
            }
        }

        @Override
        public OptionalLong read(com.google.gson.stream.JsonReader in) throws IOException {
            TypeAdapter<Long> typeAdapter = gson.getAdapter(long.class);
            Long object = typeAdapter.read(in);
            if (object == null) {
                return OptionalLong.empty();
            } else {
                return OptionalLong.of(object);
            }
        }
    }

    static class OptionalDoubleTypeAdapter extends TypeAdapter<OptionalDouble>{

        private Gson gson;

        OptionalDoubleTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override
        public void write(JsonWriter out, OptionalDouble value) throws IOException {
            if (value != null && value.isPresent()) {
                gson.getAdapter(double.class).write(out, value.getAsDouble());
            }else{
                out.nullValue();
            }
        }

        @Override
        public OptionalDouble read(com.google.gson.stream.JsonReader in) throws IOException {
            TypeAdapter<Double> typeAdapter = gson.getAdapter(double.class);
            Double object = typeAdapter.read(in);
            if (object == null) {
                return OptionalDouble.empty();
            } else {
                return OptionalDouble.of(object);
            }
        }
    }

}
