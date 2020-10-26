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
		/*for() {
			JButton[] buttons = new..
		}*/
		JButton recept1 = new JButton("recept1");
		JButton recept2 = new JButton("recept2");
		JButton recept3 = new JButton("recept3");
		JButton recept4 = new JButton("recept4");
		
		panel.add(recept1);
		panel.add(recept2);
		panel.add(recept3);
		panel.add(recept4);
		
		recept1.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        createRecipeFrame();
		    }
		});

		
		
		
		window.setVisible(true);
	}
	
	public void createRecipeFrame() {
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
