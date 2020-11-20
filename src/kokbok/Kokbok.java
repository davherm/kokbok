package kokbok;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class Kokbok {
	private int width = 800;
	private int height = 480;
	private ArrayList<Recipe> recipes= new ArrayList<Recipe>();
	JFrame mainwindow;
	JPanel mainpanel;
	Color bg = new Color(100,200,200); //C color for adobe color Complementary
	Color secondbg = new Color(153,255,255); //B color for bg
	Color thirdbg = new Color(49,122,122); //A color for bg
	Color buttonColor = new Color(200,140,100); //E color for bg^^
	
	public Kokbok() {	
		initialize();
	}
	
	public static void main(String[] args) {
		Kokbok app = new Kokbok();
	}
	
	private void initialize() {
		mainwindow = new JFrame(); //window
		mainpanel = new JPanel(); //actual contentpane
		mainwindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //outer padding
		mainpanel.setBackground(bg);
		mainwindow.setContentPane(mainpanel);

		mainwindow.setBounds(0, 0, width, height);
		
		GridLayout grid = new GridLayout(0,3,100,100); //vertical, horizontal, width, height
		mainpanel.setLayout(grid);

		readRecipesFromFile();

		
		
		
		mainwindow.setVisible(true);
	}
	
	//new window frame for a recipe that is clicked from main menu
	public void createRecipeFrame(Recipe recipe) { 
		JFrame window = new JFrame();
		JPanel panel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //outer padding
		panel.setBackground(thirdbg);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //outer padding
		leftPanel.setBackground(bg);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //outer padding
		rightPanel.setBackground(bg);
		
		window.setContentPane(panel);
		window.setBounds(0, 0, width, height);
		
		GridLayout grid = new GridLayout(0,2,10,10); //
		panel.setLayout(grid);
		
		BoxLayout leftBox = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		BoxLayout rightBox = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(leftBox);
		rightPanel.setLayout(rightBox);
		
		panel.add(leftPanel);
		panel.add(rightPanel);
		
		leftPanel.add(new JLabel(recipe.getTitle())).setFont(new Font("Serif",Font.BOLD, 24));
		
		for(int i = 1; i <= recipe.getSteps().size(); i++) {
			JLabel step = new JLabel("<html>" + "step " + i + ": " + recipe.getSteps().get(i-1) + "</html>");
			step.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			leftPanel.add(step);
		}
		for(String ingredient : recipe.getIngredients()) {
			JLabel newIngredient = new JLabel(ingredient);
			newIngredient.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			rightPanel.add(newIngredient);
		}
		
		
		
		window.setVisible(true);
	}
	
	public void readRecipesFromFile() {
		Recipe recipe = new Recipe();
		try {
		      File myObj = new File("recipes.txt");
		      Scanner myReader = new Scanner(myObj, "UTF-8");  
		      
		      String data = myReader.nextLine();
		      while (myReader.hasNextLine()) {
		        if(data.startsWith("title")) {      	
		        	recipe.setTitle(data.substring(6).trim()); //remove first 6 chars, from "title: example " to just " example ", then trim first and last space
		        	data = myReader.nextLine();
		        }
		        else if(data.startsWith("step")){
		        	recipe.addStep(data.substring(5).trim());		        	
		        	data = myReader.nextLine();	        	
		        }
		        else if(data.startsWith("ingredient")){
		        	recipe.addIngredient(data.substring(11).trim());
		        	
		        	data = myReader.nextLine();
		        	if(!myReader.hasNextLine() || data.startsWith("title")) { // check if it's time to save current recipe and start reading a new one
		        		if(!myReader.hasNextLine()) recipe.addIngredient(data.substring(11).trim());
		        		recipes.add(recipe);
		        		recipe = new Recipe();
		        	}
		        	
		        }
		        else System.out.println("something bad was read: " + data);
		        
		      }
		      myReader.close();
		    } 
		
		catch (FileNotFoundException e) 
			{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }		

		
		updateRecipeButtons();
		mainpanel.revalidate();
		mainpanel.repaint();
		
	}
	
	//creates one button for each existing recipe, with listeners to each one
	public void updateRecipeButtons() {
				
		for(Recipe recipe : recipes) {
			JButton recipeButton = new JButton(recipe.getTitle());
			recipeButton.setBackground(buttonColor);
			mainpanel.add(recipeButton);
			
			recipeButton.addActionListener( new ActionListener()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			        createRecipeFrame(recipe);
			    }
			});
		}
	}

}
