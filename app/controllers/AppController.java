package controllers;

import models.User;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by abdul-rahman on 15/05/16.
 */
public class AppController extends Controller {

    @Inject
    FormFactory formFactory ;


    public  Result addUser(){
        Logger.info("begininng of method");



        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        if (dynamicForm.data().size() == 0) {
            return badRequest("Expceting some data");
        } else {
            User user = User.find.where().eq("email", dynamicForm.get("email")).findUnique();
            if(user==null){User newUser = new User();
                newUser.setName(dynamicForm.get("name"));
                newUser.setEmail(dynamicForm.get("email"));
                newUser.setPassword(dynamicForm.get("password"));
                newUser.save();

                return ok("saved");}
            else return badRequest();



        }
    }



    public Result login(){
        DynamicForm form = formFactory.form().bindFromRequest();
        if (form.data().size()==0){
            return badRequest();
        }
        else {
            User user = User.find.where().eq("email",form.get("email")).findUnique();
            String passwordCorrect = user.getPassword();
            if(passwordCorrect.equals(form.get("password"))){
                Map<String ,String> response=new HashMap<>();
                response.put("status","true");
                response.put("id", String.valueOf(user.getId()));
                return ok(Json.toJson(response));

            }
            else{
                Map<String ,String> response=new HashMap<>();
                response.put("status","false");

                return ok(Json.toJson(response));
            }
        }
    }



}
