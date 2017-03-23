package com.rvc;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Nurmuhammad
 */
public class Json {

    ScriptEngine engine;
    ScriptObjectMirror JSON;

    public Json() {
        ScriptEngineManager sem = new ScriptEngineManager();
        engine = sem.getEngineByName("nashorn");

        try {
            JSON = (ScriptObjectMirror) engine.eval("JSON");
        } catch (ScriptException e) {
            throw new JsonException("Can not get JSON object.", e);
        }

    }


}
