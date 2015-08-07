package javax.zxiu.comic.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Zhuo Xiu on 07/08/15.
 */
public class JSUtils {

    static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    static File jsfile = new File(JSUtils.class.getClassLoader().getResource("unpack.js").getPath());

    public static String unpack(String code) {
//        System.out.println("jsfile=" + jsfile + " " + jsfile.exists());
        Object result=null;
        try {
            engine.eval(new FileReader(jsfile));
            Invocable invocable = (Invocable) engine;
            result = invocable.invokeFunction("unpack", code);

            engine.eval((String) result);
            result = invocable.invokeFunction("dm5imagefun");
//            for (Object r:(Object[])result){
//                System.out.println(r);
//            }
        } catch (ScriptException | FileNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }


        return ((ScriptObjectMirror) result).getSlot(0)+"";
    }
}
