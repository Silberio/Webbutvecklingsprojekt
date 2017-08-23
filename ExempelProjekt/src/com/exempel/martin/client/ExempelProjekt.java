package com.exempel.martin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */


public class ExempelProjekt implements EntryPoint {
  private VerticalPanel mainPanel = new VerticalPanel();
  private HorizontalPanel addPanel = new HorizontalPanel();
  private TextBox operand1TextBox = new TextBox();
  private TextBox operand2TextBox = new TextBox();
  private Button calculateButton = new Button("Calculate");
  private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
  private SuggestBox operatorTextBox=new SuggestBox(oracle);

	
  /**
   * Entry point method.
   */
  public void onModuleLoad() {
	  //Suggests the valid operators
	  oracle.add("*");
	  oracle.add("%");
	  oracle.add("+");
	  oracle.add("-");
	  oracle.add("/");
	 
		
	  	addPanel.add(operand1TextBox);
	    addPanel.add(operatorTextBox);
	    addPanel.add(operand2TextBox);
	    addPanel.add(calculateButton);
	    
    // TODO Assemble Main panel.
	    

	    mainPanel.add(addPanel);
    // TODO Associate the Main panel with the HTML host page.
	    RootPanel.get("calc").add(mainPanel);
    // TODO Move cursor focus to the input box.
	    
	    
	    calculateButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          calculate();
        }});
	    
	
		calculateButton.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					calculate();
				}
			}
		});
	   
	    
  }	

		private void calculate() {
			
			final String operator = operatorTextBox.getText().trim();
			calculateButton.setFocus(true);
			if ((!operator.equals("*") && !operator.equals("+") && !operator.equals("%") && !operator.contentEquals("-") && !operator.contentEquals("/")) || !isInteger(operand1TextBox.getText().trim())|| !isInteger(operand2TextBox.getText().trim())) {
		        Window.alert("You have entered a non valid binary operator or one of the operands is not an integer");
		        
		        return;
		      }
			
			 int operand1=Integer.parseInt(operand1TextBox.getText());
			 int operand2=Integer.parseInt(operand2TextBox.getText());
			 int answer;
			 //Multiplication
			 if(operator.equals("*"))
			 {
				 calculateMultiplication(operand1, operand2);
			 }
			 //Modulo
			 else if (operator.equals("%"))
			 {
				 calculateModulo(operand1, operand2);
			 }
			 //Subtraction
			 else if (operator.contentEquals("-")) {
				 calculateSubtraction(operand1, operand2);
			 }
			 
			 else if (operator.contentEquals("/")) {
				calculateDivision(operand1, operand2); 
			 }
			 //addition
			 else {				 
				 calculateAddition(operand1, operand2);
			 }
			 
			}
		//Methods for individual operators
		public void calculateMultiplication(int operand1, int operand2) {
			int answer=operand1*operand2;
			 Window.alert("The answer is: " + answer);
		}
		
		public void calculateModulo(int operand1, int operand2) {
			 int answer=operand1%operand2;
			 Window.alert("The answer is: " + answer);
		}
		
		public void calculateSubtraction(int operand1, int operand2) {
			 int answer=operand1-operand2;
			 Window.alert("The answer is: " + answer);
		}
		
		public void calculateAddition(int operand1, int operand2) {
			 int answer=operand1+operand2;
			 Window.alert("The answer is: " + answer);
			 
		}	
		
		public void calculateDivision(int operand1, int operand2) {
			double i,j;
			i = operand1;
			j = operand2;
			
			double answer=i/j;
			if(operand1 == 0 || operand2 == 0) {
				Window.alert("Cannot divide by 0 (Zero)");
				return;
			}
			else
				Window.alert("The answer is: " + answer);
		
		}
		
		//Checkes if a String could be seen as an integer
		public boolean isInteger( String input )
		{
		   try
		   {
		      Integer.parseInt( input );
		      return true;
		   }
		   catch(NumberFormatException e)
		   {
		      return false;
		   }
		}
			
		
}