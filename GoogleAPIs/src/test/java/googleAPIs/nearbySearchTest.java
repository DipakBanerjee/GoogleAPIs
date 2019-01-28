package googleAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.utility_class;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.logging.log4j.*; //we can get this from the pom.xml dependency

public class nearbySearchTest extends utility_class	{
	/* below we're creating a log object to start logging everything using 'log.'
	LogManager is an API and getLogger is a function inside it which accepts a 'class' argument
	=> LogManager now has knowledge of the class (in the argument) */
	private static Logger log = LogManager.getLogger(nearbySearchTest.class.getName());

	Properties prop = new Properties();	/* Java class. Properties reads the key value and when
										this key is passed it automatically extracts that key value
										and places it in the script */

	@BeforeTest
	public void getGoogleAPIkey() throws IOException	{
		//we need this to tell our script where our env.properties file is: -
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\env.properties");
		//the fis object above knows where the env.properties file is and Properties is ready to load that file: -
		prop.load(fis); //properties object loads this file and extracts key value we need
	}

	@Test
	public void getData()	{

		ArrayList<String> body=given().
				param("location","55.9533,-3.1883").
				param("radius",100).
				param("keyword","Balmoral").
				param("key",prop.getProperty("KEY")).
				// for the moment no headers or cookies
				log().
				all().
				when().
				get("nearbysearch/json"). //i.e. resource
				then().
				assertThat().
				statusCode(200).
				and().
				contentType(ContentType.JSON).
				and().
				body(("results[0].name"),equalTo("The Balmoral")).
				extract().
				path("results[0].photos.width");
		System.out.println(body);
		log.info(body);
		log.info("results[0].name");
	}
}
