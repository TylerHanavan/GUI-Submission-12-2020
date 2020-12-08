package com.tylerhanavan.gui;

import java.util.ArrayList;
import java.util.List;

import com.tylerhanavan.gui.Field.FieldType;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	private List<Field> fields;
	
	private Text status;

	/**
	 * JavaFX starts here for rendering
	 */
    @Override
    public void start(Stage stage) {
    	fields = new ArrayList<Field>();
    	
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Enter Entry Details");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        addField(new Field("ID", 7, 7, FieldType.NUMERICAL), grid);
        addField(new Field("NUMBER", 1, 10, FieldType.NUMERICAL), grid);
        addField(new Field("COMMENT", 0, 42, FieldType.ANY), grid);
        addField(new Field("START_DATE", 8, 8, FieldType.NUMERICAL), grid);
        addField(new Field("END_DATE", 8, 8, FieldType.NUMERICAL), grid);
        
        Button submit = new Button("Submit");
        
        grid.add(submit, 1, fields.size() + 1);
        
        submit.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent evt) {
				trySubmission();
			}
        	
        });
        
        Text status = new Text("");
        
        this.status = status;
        
        grid.add(status, 1, fields.size() + 2);

        Scene scene = new Scene(grid, 550, 275);
        stage.setScene(scene);
        stage.setTitle("New Entry");
        stage.show();
    }
    
    /**
     * Add a field to the grid
     * @param field The field
     * @param grid The grid
     */
    public void addField(Field field, GridPane grid) {
    	fields.add(field);
    	field.construct(grid, fields.size());
    }
    
    /**
     * Set the status message to be the color red and populate it with a new message
     * @param msg The message
     */
    public void setErrorStatus(String msg) {
    	status.setFill(Color.RED);
    	setStatusMessage(msg);
    }
    
    /**
     * Set the status message to be the color green and populate it with a new message
     * @param msg The message
     */
    public void setSuccessStatus(String msg) {
    	status.setFill(Color.GREEN);
    	setStatusMessage(msg);
    }
    
    /**
     * Set the status message to a new message
     * @param msg The message
     */
    private void setStatusMessage(String msg) {
    	status.setText(msg);
    }
    
    /**
     * Attempt to submit and render an error in the status field if unable to
     * @return
     */
    private boolean trySubmission() {
    	boolean b = true;
    	for(Field field : fields) {
			if(!field.canSubmit()) {
				b = false;
				setErrorStatus(field.getError());
			}
		}
    	if(b) {
    		
    		Submission submission = new Submission(fields, this);
    		
    		submission.submit();
    		
    		//setSuccessStatus(submit(id, number));
    	}
    	return b;
    }

    /**
     * Start here 
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}