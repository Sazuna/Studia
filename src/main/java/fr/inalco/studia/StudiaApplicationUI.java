package fr.inalco.studia;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Suivre ce tutoriel : https://blog.jetbrains.com/search/?q=javafx&c=Tutorials
 */
@SpringBootApplication
public class StudiaApplicationUI extends Application {

    private ConfigurableApplicationContext applicationContext;

    
    @Override
    public void init() {
    	applicationContext = new SpringApplicationBuilder(StudiaApplicationUI.class).run();
    }
	@Override
	public void start(Stage primaryStage) {
		applicationContext.publishEvent(new StageReadyEvent(primaryStage));
	}
	
	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();
	}
	
	public class StageReadyEvent extends ApplicationEvent {

		private static final long serialVersionUID = 1L;

		public StageReadyEvent(Stage stage) {
			super(stage);
		}

		public Stage getStage() {
			return (Stage)getSource();
		}
	}

}
