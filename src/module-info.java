module check {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.web;
	requires java.desktop;
	
	opens Ant_Algo to javafx.graphics, javafx.fxml;
	opens Ant_Pack to javafx.graphics, javafx.fxml;
	opens Ant_Pack2 to javafx.graphics, javafx.fxml;
	opens Ant_Pack3 to javafx.graphics, javafx.fxml;
	opens Ant_Recco to javafx.graphics, javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
	opens Run to javafx.graphics, javafx.fxml;
	opens RunBackend to javafx.graphics, javafx.fxml;
	opens RunFrontend to javafx.graphics, javafx.fxml;
}
