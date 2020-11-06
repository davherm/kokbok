package kokbok;

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
	private int height = 600;
	private ArrayList<Recipe> recipes= new ArrayList<Recipe>();
	JFrame mainwindow;
	JPanel mainpanel;
	
	public Kokbok() {	
		initialize();
	}
	
	public static void main(String[] args) {
		Kokbok app = new Kokbok();
	}
	
	private void initialize() {
		mainwindow = new JFrame(); //window
		mainpanel = new JPanel(); //actual contentpane
		mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //outer padding
		mainwindow.setContentPane(mainpanel);

		mainwindow.setBounds(0, 0, width, height);
		
		GridLayout grid = new GridLayout(0,3,100,100);
		mainpanel.setLayout(grid);
		
		
		
		JButton newRecipe = new JButton("update recipes"); //first button to read recipes from file
		mainpanel.add(newRecipe);
		
		newRecipe.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        readRecipesFromFile();
		    }
		});
		
		
		
		
		mainwindow.setVisible(true);
	}
	
	//new window frame for a recipe that is clicked from main menu
	public void createRecipeFrame(Recipe recipe) { 
		JFrame window = new JFrame();
		JPanel panel = new JPanel();
		
		window.setContentPane(panel);
		window.setBounds(0, 0, width, height);
		
		BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(box);
		
		for(String step : recipe.getSteps()) {
			panel.add(new JLabel(step));
		}
		for(String ingredient : recipe.getIngredients()) {
			panel.add(new JLabel(ingredient));
		}
		
		
		
		window.setVisible(true);
	}
	
	public void readRecipesFromFile() {
		Recipe recipe = new Recipe();
		try {
		      File myObj = new File("recipes.txt");
		      Scanner myReader = new Scanner(myObj);  
		      
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
		        else System.out.println("something bad was read");
		        
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
