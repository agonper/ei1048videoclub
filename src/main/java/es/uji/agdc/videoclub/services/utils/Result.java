package es.uji.agdc.videoclub.services.utils;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Result {
    public enum Type {
        OK, ERROR
    }

    private final Type type;
    private final String msg;
    private final List<String> fields;

    public Result(Type type) {
        this(type, "");
    }

    public Result(Type type, String msg) {
        this(type, msg, new LinkedList<>());
    }

    private Result(Type type, String msg, List<String> fields) {
        this.type = type;
        this.msg = msg;
        this.fields = fields;
    }

    public boolean isOk() {
        return type.equals(Type.OK);
    }

    public  boolean isError() {
        return !isOk();
    }

    public String getMsg() {
        return msg;
    }

    public String[] getFields() {
        return fields.toArray(new String[fields.size()]);
    }

    public Result addField(String field) {
        fields.add(field);
        return this;
    }

    @Override
    public String toString() {
        if (isOk()) {
            return "Result: OK";
        }
        return "Result: ERROR(" + msg +")" + fields.toString();
    }
}
