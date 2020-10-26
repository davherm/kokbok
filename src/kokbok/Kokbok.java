package kokbok;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Kokbok {
	private int width = 800;
	private int height = 600;
	private ArrayList<Recipe> recipes= new ArrayList<Recipe>();
	
	public Kokbok() {
		
		Recipe recipe = createRecipe();
		recipes.add(recipe);
		recipes.add(recipe);
		recipes.add(recipe);
		
		
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
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		
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
		
		
		
		
		window.setVisible(true);
	}
	
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
	
	public Recipe createRecipe() {
		Recipe recipe = new Recipe();
		recipe.setTitle("example");
		
		String[] steps = new String[] {"step 1", "step 2", "step 3"};
		recipe.setSteps(steps);
		
		return recipe;
	}

}
