package utilities.enviroment;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources({"file:enviromentConfig/${server}.properties"})
public interface EnviromentConfig extends Config {
	
	@Key("App.Url")
	String appUrl();
	
	@Key("App.User")
	String appUserName();
	
	@Key("App.Pass")
	String appPassword();
	
	String dbUrl();

}
