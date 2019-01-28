package googleAPIs;

import io.restassured.http.ContentType;
import resources.utility_class;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.logging.log4j.*; //the wildcard imports ALL files in ALL packages

public class textSearchTest extends utility_class	{

	private static Logger log = LogManager.getLogger(nearbySearchTest.class.getName());
	Properties prop = new Properties();

	@BeforeTest
	public void getGoogleAPIkey() throws IOException	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\env.properties");
		//FileInputStream fis = new FileInputStream("C:\\Users\\rbanerjee\\eclipse\\projectsFolder\\googleAPIs\\src\\main\\java\\resources\\env.properties");
		prop.load(fis);
	}

	@Test
	public void getData()	{

		String body=given().
				param("query","London Eye").
				param("key",prop.getProperty("KEY")).
				log().
				all().
				when().
				get("textsearch/json").
				then().
				assertThat().
				statusCode(200).
				and().
				contentType(ContentType.JSON).
				and().
				body(("results[0].formatted_address"),equalTo("London SE1 7PB, United Kingdom")).
				extract().
				path("results[0].name");

		System.out.println(body);
		log.info(body);
	}
}
