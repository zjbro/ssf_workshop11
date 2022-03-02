package sg.edu.nus.iss.visaWorkshop11;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// To get opts as list
import java.util.Collections;
import java.util.List;
// To import third party library for logging
import java.util.logging.Logger;
import java.util.logging.Level;

@SpringBootApplication
public class VisaWorkshop11Application {
    // Instantiate the logger
    private static final Logger logger = Logger.getLogger(VisaWorkshop11Application.class.getName());
    // The default fallback port used by the spring boot application
    private static final String DEFAULT_PORT = "3000";
    public static void main(String[] args) {
        logger.log(Level.INFO, "Workshop 11");
        // init the spring app
        SpringApplication app = new SpringApplication(VisaWorkshop11Application.class);
        // Decode the java app args using spring args helper
        DefaultApplicationArguments appArgs = new DefaultApplicationArguments();
        // return the args from the java args as list of strings
        List optsVal = appArgs.getOptionValues("port");
        logger.log(Level.INFO, "optsVal >" + optsVal);
        // var(variable) to hold up the port number to be pass on to the spring boot app
        String portNumber = null;
        // check if the opt arg is null or the first elem is null as
        // before retrieving it from the env variable
        if(optsVal == null || optsVal.get(0) == null){
            // retrieve from the OS env variable
            portNumber = DEFAULT_PORT;
        } else {
            //if both conditions above is not met, get from the args of the app
            portNumber = (String)optsVal.get(0);
        }
        // check if the port number is still null or empty
        if(portNumber != null) {
            //override the spring boot app port number using the default properties from spring boot framework
            app.setDefaultProperties(Collections.singletonMap("server.port",portNumber));
        }
        app.run(args);
    }
}