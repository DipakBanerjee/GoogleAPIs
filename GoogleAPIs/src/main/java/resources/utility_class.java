package resources;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;

public class utility_class	{

	@BeforeClass
	public static void setUp()	{
		baseURI = "https://maps.googleapis.com";
		basePath = "maps/api/place";
	}
	
	//instead of basePath above we could do: -
	public static String getBasePath()	{ //static, so we can directly access this method in out test class
		String bp = "maps/api/place/nearbysearch/json";
		return bp;
	}
	//-import this package.class i.e. googleAPIs.utility_class
	//-get(resources.utility_class.getBasePath()).
	 
}