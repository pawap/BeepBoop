package beepBoop.ui;


public class RTBot2UI extends MenuUI {

	@Override
	protected void defineLabels() {
		buttonLabels = new String[] 
				{"New Script", "Edit Script", "Import Script", "Show Log", "Back"};
	}

	@Override
	protected void reactToClick(String buttonLabel) {
		
		switch(buttonLabel) {
		case ("New Script"): 
            System.out.println("new");
			break;
		case ("Edit Script"): 
			System.out.println("edit");
			break;
		case ("Import Script"): 
			System.out.println("Import Script");
		case ("Show Log"): 
			System.out.println("Show Log");
			break;
		case ("Back"): 
			System.out.println("Back");
			break;

		}

	}

}
