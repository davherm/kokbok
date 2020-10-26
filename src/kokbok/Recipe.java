package kokbok;

import java.util.ArrayList;

public class Recipe {

	private String title;
	private ArrayList<String> steps;
	//private Media picture;
	
	public Recipe() {
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<String> getSteps() {
		return steps;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public void setSteps(ArrayList<String> newSteps) {
		steps = newSteps;
	}
}
