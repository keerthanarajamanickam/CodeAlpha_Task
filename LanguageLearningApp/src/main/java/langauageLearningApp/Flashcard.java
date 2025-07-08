package langauageLearningApp;

public class Flashcard {
	
	    private int id;
	    private String word;
	    private String translation;
	    private String category;

	    public Flashcard(String word, String translation, String category) {
	        this.word = word;
	        this.translation = translation;
	        this.category = category;
	    }

	    public String getWord() { 
	    	return word; 
	    }
	    public String getTranslation() { 
	    	return translation; 
	    }
	    public String getCategory() {
	    	return category;
	    }

}
