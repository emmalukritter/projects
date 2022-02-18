package controllers;
import models.*;
import static play.data.Form.form;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import play.db.*;



public class Application extends Controller {


    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }


    public static Result showAddActorForm() {

        Form<Actor> theform = form(Actor.class).bindFromRequest();

        return ok( addactorform.render(theform, Actor.findAll(),"") );
    }


    public static Result addActor() {
        Form<Actor> theform = form(Actor.class).bindFromRequest();


        Actor actor = theform.get();



        if ( actor.id == null )

        {

            // it's a new object

            actor.save();

            Logger.debug( actor.toString() );

            return ok( addactorform.render(theform, Actor.findAll(), "success") );

        }

        else

        {

            // it's an old object

            actor.update();

            Logger.debug( actor.toString() );

            return ok( addactorform.render(theform, Actor.findAll(), "updated") );

        }

    }


    public static Result deleteActor(Long _id) {

        Form<Actor> theform = form(Actor.class).bindFromRequest();

        Actor actor = Actor.findById(_id);



        if ( actor == null)

        {

            return badRequest( addactorform.render(theform.fill(new Actor()), Actor.findAll(), "Actor not found" ) );

        }

        else

        {

            actor.delete();

            return ok( addactorform.render(theform.fill(new Actor()), Actor.findAll(), "Actor deleted" ) );

        }



    }



    public static Result editActor(Long _id) {

        Form<Actor> theform = form(Actor.class).bindFromRequest();

        Actor actor = Actor.findById(_id);



        if ( actor == null)

        {

            return badRequest( addactorform.render(theform.fill(new Actor()), Actor.findAll(), "Actor not found" ) );

        }

        else

        {

            return ok( addactorform.render(theform.fill(actor), Actor.findAll(), "" ) );

        }

    }




}
