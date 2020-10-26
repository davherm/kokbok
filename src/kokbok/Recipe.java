package kokbok;

public class Recipe {

	private String title;
	private String[] steps;
	//private Media picture;
	
	public Recipe() {
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public String[] getSteps() {
		return steps;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public void setSteps(String[] newSteps) {
		steps = newSteps;
	}
}
