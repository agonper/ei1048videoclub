package es.uji.agdc.videoclub.services.utils;

/**
 * Created by Alberto on 03/12/2016.
 */
public class ResultBuilder {
    public static Result ok() {
        return new Result(Result.Type.OK);
    }

    public static Result error(String msg) {
        return new Result(Result.Type.ERROR, msg);
    }

    public static Result error(String msg, String ...fields) {
        Result result = new Result(Result.Type.ERROR, msg);
        for (String field : fields) {
            result.addField(field);
        }
        return result;
    }
}
