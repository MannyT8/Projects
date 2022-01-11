package com.revature.project1.main;

import io.javalin.Javalin;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.revature.project1.controller.RequestController;
import com.revature.project1.controller.UserController;
import com.revature.project1.util.ConnectionFactory;

public class App {
		
    public static void main( String[] args ) {
    	
    	final Logger logger = Logger.getLogger(App.class.getName());

    	Connection conn=ConnectionFactory.getConnection(); if (conn != null) {
   		  //System.out.println("Connection successful"); 
  		logger.info("Connection successful");
} else {
   		  System.out.println("Connection failed"); }
    	
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
			config.addStaticFiles("/public");
		}).start(9091);
		
        app.get("/", ctx -> ctx.redirect("/signin.html"));
        app.post("/signin.html", UserController.signIn);
        
        app.post("/employee.html", ctx -> {
        	String selection = ctx.formParam("selection");
        	if (selection.equals("Submit a Request")) {
        		ctx.redirect("request_form.html");
        	} else if (selection.equals("View My Requests")) {
        		ctx.redirect("my_requests.html");
        	} else {
        		ctx.redirect("signin.html");
        	}
        });
        //app.post("/request_form.html", RequestController.add);
        app.post("/submitted.html", RequestController.add);
        
        app.post("/my_requests.html", RequestController.findMine);
       // app.get("/my_requests.html", RequestController.findMine);

        app.post("/manager.html", RequestController.findAll);
        app.post("/update_form.html", RequestController.update);
        //app.post("/my_info.html", UserController.viewInfo);
        app.post("UpdateEInfo/change_userName.html", UserController.updateUserName);
        app.post("UpdateEInfo/change_password.html", UserController.updatePassword);
        app.post("/manager_view_one_employee.html", RequestController.viewOneEmployee);
        app.get("/MVOE_requests", RequestController.viewOneEmployee);

    	app.get("/employees", UserController.getAllEmployees);
    	app.get("/requests", RequestController.findMine);
        app.get("/viewInfo", UserController.viewInfo);

    	//app.get("/requests", RequestController.findAll);
        //app.post("/pending_requests", RequestController.findAll);
        
        app.get("/pending_requests", RequestController.findPending);
        app.get("/resolved_requests", RequestController.findResolved);
        app.get("/all_requests", RequestController.findAll);


    }
}
    
