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
	
	public Kokbok() {	
		initialize();
	}
	
	public static void main(String[] args) {
		Kokbok app = new Kokbok();
	}
	
	private void initialize() {
		JFrame window = new JFrame(); //window
		JPanel panel = new JPanel(); //actual contentpane
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //outer padding
		window.setContentPane(panel);

		window.setBounds(0, 0, width, height);
		
		GridLayout grid = new GridLayout(0,3,100,100);
		panel.setLayout(grid);
		
		//create one button for each existing recipe		
		for(Recipe recipe : recipes) {
			JButton recipeButton = new JButton(recipe.getTitle());
			panel.add(recipeButton);
			
			recipeButton.addActionListener( new ActionListener()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			        createRecipeFrame(recipe);
			    }
			});
		}
		
		JButton newRecipe = new JButton("update recipes"); //first button to read recipes from file
		panel.add(newRecipe);
		
		newRecipe.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        readRecipesFromFile();
		    }
		});
		
		
		
		
		window.setVisible(true);
	}
	
	//new window frame for a recipe that is clicked from main menu
	public void createRecipeFrame(Recipe recipe) { 
		JFrame window = new JFrame();
		JPanel panel = new JPanel();
		
		window.setContentPane(panel);
		window.setBounds(0, 0, width, height);
		
		BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(box);
		
		ArrayList<JLabel> steps = new ArrayList<JLabel>();
		for(String step : recipes.get(0).getSteps()) {
			steps.add(new JLabel(step));
		}
		
		for(JLabel step : steps) {
			panel.add(step);
		}
		
		
		
		window.setVisible(true);
	}
	
	public void readRecipesFromFile() {
		Recipe recipe = new Recipe();
		ArrayList<String> steps = new ArrayList<String>();
		try {
		      File myObj = new File("recipes.txt");
		      Scanner myReader = new Scanner(myObj);  
		      
		      if(myReader.hasNextLine()) recipe.setTitle(myReader.nextLine()); //first title
		      
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        if(data.equals("%")) {
		        	//% means there is a new recipe starting next line, so save the current and clear all local variables
		        	recipe.setSteps(steps);
		        	recipes.add(recipe);
		        	
		        	recipe.setTitle(myReader.nextLine()); //will overwrite if title already exists, so no need to clear past values
		        	steps.clear(); //needs to be cleared..
		        }
		        else steps.add(data);
		        System.out.println(data); //doesnt print titles, but thats okay
		      }
		      myReader.close();
		    } 
		catch (FileNotFoundException e) 
			{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}

}
