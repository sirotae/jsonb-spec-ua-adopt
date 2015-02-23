package ua.jug.json.bind.gsonri;

import com.google.gson.Gson;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbException;
import java.io.*;

/**
 * Created by sirotae on 2/22/2015.
 */
public final class JsonbGson implements Jsonb {

    private Gson gson;

    JsonbGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T fromJson(String str, Class<T> type) throws JsonbException {
        return null;
    }

    @Override
    public <T> T fromJson(Reader reader, Class<T> type) throws JsonbException {
        return null;
    }

    @Override
    public <T> T fromJson(InputStream stream, Class<T> type) throws JsonbException {
        return null;
    }

    @Override
    public <T> T fromJson(File file, Class<T> type) throws JsonbException {
        return null;
    }

    @Override
    public String toJson(Object object) throws JsonbException {
        return null;
    }

    @Override
    public void toJson(Object object, File file) throws JsonbException {

    }

    @Override
    public void toJson(Object object, Writer writer) throws JsonbException {

    }

    @Override
    public void toJson(Object object, OutputStream stream) throws JsonbException {

    }
}
