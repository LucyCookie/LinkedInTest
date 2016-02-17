package controllers;

import modules.Calculator;
import play.*;
//import play.api.data.Form;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render(""));
    }

    public Result calculate(){
        Calculator calculation= Form.form(Calculator.class).bindFromRequest().get();
        return ok(index.render(String.valueOf(calculation.calculate())));
    }

}
