package com.tylerhanavan.gui;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class Field {

	private String label;
	private int lengthMin,lengthMax;
	private FieldType type;
	
	private Label fieldText;
	private TextField fieldForm;
	
	public enum FieldType {
		NUMERICAL("0123456789"),
		ALPHABETICAL("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
		ALPHANUMERICAL("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"),
		ANY(null);
		
		private String acceptableChars;
		
		FieldType(String acceptableChars) {
			this.acceptableChars = acceptableChars;
		}
		
		public boolean accepts(String input) {
			if(acceptableChars == null)
				return true;
			for(char c : input.toCharArray())
				if(acceptableChars.indexOf(c) == -1)
					return false;
			return true;
		}
		
	}
	
	public Field(String label, int lengthMin, int lengthMax, final FieldType type) {
		this(label, lengthMin, lengthMax, type, true);
	}
	
	public Field(String label, int lengthMin, int lengthMax, final FieldType type, boolean render) {
		this.label = label;
		this.lengthMin = lengthMin;
		this.lengthMax = lengthMax;
		this.type = type;
		
		if(render) {

	        Label fieldText = new Label(label);
	        TextField fieldForm = new TextField();
	        
	        fieldForm.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
	
				public void handle(KeyEvent evt) {
					
					if(evt.getCode() == KeyCode.BACK_SPACE)
						return;
					
					if(!accepts(evt.getCharacter()))
						evt.consume();
					
				}
	        	
	        });
	        
	        this.fieldText = fieldText;
	        this.fieldForm = fieldForm;
        
		}
	}
	
	/**
	 * Render the field onto the grid
	 * @param grid The grid
	 * @param loc Position to render in
	 */
	public void construct(GridPane grid, int loc) {
		grid.add(this.fieldText, 0, loc);
		grid.add(this.fieldForm, 1, loc);
	}
	
	/**
	 * Check if the field accepts the incoming character
	 * @param c The typed character
	 * @return
	 */
	public boolean accepts(String c) {
		if(!type.accepts(c))
			return false;
		if(fieldForm.getText().length() + 1 > lengthMax)
			return false;
		return true;
	}
	
	/**
	 * 
	 * @return True if this field has no errors
	 */
	public boolean canSubmit() {
		return getError() == null;
	}
	
	/**
	 * 
	 * @return Any error this field has
	 */
	public String getError() {
		String data = fieldForm.getText();
		if(label.contains("DATE")) {
			if(!DataHelper.isValidDate(data)) {
				return label + ": Invalid date";
			}
		}
		if(data.length() > lengthMax) {
			return label + ": Length too long";
		}
		if(data.length() < lengthMin) {
			return label + ": Length too short";
		}
		return null;
	}
	
	/**
	 * 
	 * @return The field type
	 */
	public FieldType getType() {
		return type;
	}
	
	/**
	 * 
	 * @return The field label/name
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * 
	 * @return The field data
	 */
	public String getData() {
		return fieldForm.getText();
	}
	
}
