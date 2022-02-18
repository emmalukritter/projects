package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;

import views.html.*;

public class Application extends Controller {

    public static Result html() {
        response().setContentType("text/html");
        return ok("<h1>Hello World!</h1>");
    }
    public static Result sesion() {
        String user = session("connected");
        if(user != null) {
            return ok("Hello " + user);
        } else {
            return unauthorized("Oops, you are not connected");
        }
    }
    public static Result fls() {
        String message = flash("success");
        if(message == null) {
            message = "Welcome!";
        }
        return ok(message);
    }

    public static Result save() {
        flash("success", "The item has been created");
        return redirect("/home");
    }



    static Form<Task> taskForm = Form.form(Task.class);
    public static Result index() {
        return redirect(routes.Application.tasks());
    }

    public static Result tasks() {

        return ok(
                views.html.index.render(Task.all(), taskForm)
        );
    }

    public static Result newTask() {

        Form<Task> filledForm = taskForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    views.html.index.render(Task.all(), filledForm)
            );
        } else {
            Task.create(filledForm.get());
            return redirect(routes.Application.tasks());
        }
    }

    public static Result deleteTask(Long id) {

        Task.delete(id);
        return redirect(routes.Application.tasks());
    }
}
