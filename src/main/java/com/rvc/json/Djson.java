package com.rvc.json;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Nurmuhammad
 */
public class Djson {
    ScriptEngine engine;
    ScriptObjectMirror JSON;
    JSObject jsObject;

    private Djson() {
        ScriptEngineManager sem = new ScriptEngineManager();
        engine = sem.getEngineByName("nashorn");

        try {
            JSON = (ScriptObjectMirror) engine.eval("JSON");
        } catch (ScriptException e) {
            throw new DjsonException("Can not get JSON object.", e);
        }
    }

    public String pretty() {
        return String.valueOf(JSON.callMember("stringify", jsObject, null, "\t"));
    }

    public String print(){
        return String.valueOf(JSON.callMember("stringify", jsObject));
    }

    public Djson parse(String json) {
        jsObject = (JSObject) JSON.callMember("parse", json);
        return this;
    }

    public Djson parse(InputStream is) throws IOException {
        return parse(new InputStreamReader(is));
    }

    public Djson parse(Reader reader) throws IOException {
        char[] arr = new char[8 * 1024]; // 8K at a time
        StringBuffer buf = new StringBuffer();
        int numChars;

        while ((numChars = reader.read(arr, 0, arr.length)) > 0) {
            buf.append(arr, 0, numChars);
        }

        return parse(buf.toString());
    }

    public Djson parse(Path path) throws IOException {
        return parse(new String(Files.readAllBytes(path)));
    }

    public Djson parse(File file) throws IOException {
        return parse(new FileReader(file));
    }

    public Djson set(String path, Object value) {
        engine.put("value", value);
        jsObject.eval(path + " = value;");
        return this;
    }

    public <T> T get(String path){
        return (T) jsObject.eval(path);
    }

    public <T> T sort(String path, String function) {
        return (T) jsObject.eval(path + ".sort(function (left, right) {return " + function + ";});");
    }

    public <T> T sort(String function) {
        return sort("this", function);
    }

    public <T> T  filter(String path, String function){
        return (T) jsObject.eval(path + ".filter(function (item) {return " + function + ";});");
    }

    public <T> T filter(String function){
        return filter("this", function);
    }



}
