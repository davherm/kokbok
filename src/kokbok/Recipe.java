package kokbok;

import java.util.ArrayList;

public class Recipe {

	private String title;
	private ArrayList<String> steps;
	private ArrayList<String> ingredients;
	//private Media picture;
	
	public Recipe() {
		steps = new ArrayList<String>();
		ingredients = new ArrayList<String>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<String> getSteps() {
		return steps;
	}
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public void setSteps(ArrayList<String> newSteps) {
		steps = newSteps;
	}
	
	public void addStep(String newStep) {
		steps.add(newStep);
	}
	
	public void setIngredients(ArrayList<String> newIngredient) {
		ingredients = newIngredient;
	}
	
	public void addIngredient(String newIngredient) {
		ingredients.add(newIngredient);
	}
}
