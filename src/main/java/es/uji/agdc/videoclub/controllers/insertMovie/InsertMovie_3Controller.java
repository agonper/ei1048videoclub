package es.uji.agdc.videoclub.controllers.insertMovie;

import es.uji.agdc.videoclub.controllers.Controller;
import es.uji.agdc.videoclub.controllers.Form;
import es.uji.agdc.videoclub.controllers.RootController;

/**
 * Created by daniel on 5/01/17.
 */
public class InsertMovie_3Controller extends Controller implements Form {
    @Override
    public boolean allFieldsValid() {
        return false;
    }

    @Override
    public String[] getAllData() {
        return new String[0];
    }

    @Override
    public void setAllData(String[] data) {

    }

    @Override
    public void setRootController(RootController controller) {

    }
}
