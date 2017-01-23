package introsde.storage.rest.resources;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import introsde.storage.rest.model.Recipe;
import introsde.storage.rest.model.RecipeNutritionFacts;


/***
 * The resource class that implements our service endpoints for the Recipe.
 * 
 * @author alan
 *
 */

//@Stateless
//@LocalBean
@Path("/recipe/")
public class RecipeResource {
	@Context UriInfo uriInfo;	// allows to insert contextual objects (uriInfo) into the class
	@Context Request request;	// allows to insert contextual objects (request) into the class
	
	DocumentBuilder docBuilder;
	WebTarget webTarget;
	ObjectMapper mapper = new ObjectMapper();
	
	// Definition of some useful constants
	final String baseUrl = "http://recipe-adapter-service-ar.herokuapp.com/rest/yummly";
	
	public RecipeResource() {
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		webTarget = ClientBuilder.newClient(
				new ClientConfig()).target(UriBuilder.fromUri(baseUrl).build()
		);
	}
	
	/***
	 * A method that returns a random recipe from the external adapter service
	 * @return a random recipe
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{id}")
	public RecipeNutritionFacts getRecipe(@PathParam("id") String id) {
		RecipeNutritionFacts recipe = null;
		
		Response response = webTarget.path(id).request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		int statusCode = response.getStatus();
		
		if (statusCode==200) {
			try {
				JsonNode root = mapper.readTree(response.readEntity(String.class));
				
				recipe = new RecipeNutritionFacts();
				recipe.setId(root.path("id").asText());
				recipe.setName(root.path("name").asText());
				recipe.setImage(root.path("image").asText());
				recipe.setWebsiteUrl(root.path("websiteUrl").asText());
				recipe.setProteins(root.path("proteins").asDouble());
				recipe.setCarbohydrates(root.path("carbohydrates").asDouble());
				recipe.setLipids(root.path("lipids").asDouble());
				recipe.setSaturatedLipids(root.path("saturatedLipids").asDouble());
				recipe.setCalories(root.path("calories").asDouble());
				recipe.setSodium(root.path("sodium").asDouble());
				recipe.setPotassium(root.path("potassium").asDouble());
				recipe.setCalcium(root.path("calcium").asDouble());
				recipe.setStarch(root.path("starch").asDouble());
				recipe.setFiber(root.path("fiber").asDouble());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new WebApplicationException(statusCode);
		}
		
		return recipe;
	}
	
	/***
	 * A method that returns a random recipe from the external adapter service
	 * @return a random recipe
	 */
	/*
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public RecipeNutritionFacts getRandomRecipe() {
		RecipeNutritionFacts recipe = null;
		
		webTarget += "/random ...";
		
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		int statusCode = response.getStatus();
		
		if (statusCode==200) {
			try {
				JsonNode root = mapper.readTree(response.readEntity(String.class));
				
				recipe = new RecipeNutritionFacts();
				recipe.setId(root.path("id").asText());
				recipe.setName(root.path("name").asText());
				recipe.setImage(root.path("image").asText());
				recipe.setWebsiteUrl(root.path("websiteUrl").asText());
				recipe.setProteins(root.path("proteins").asDouble());
				recipe.setCarbohydrates(root.path("carbohydrates").asDouble());
				recipe.setLipids(root.path("lipids").asDouble());
				recipe.setSaturatedLipids(root.path("saturatedLipids").asDouble());
				recipe.setCalories(root.path("calories").asDouble());
				recipe.setSodium(root.path("sodium").asDouble());
				recipe.setPotassium(root.path("potassium").asDouble());
				recipe.setCalcium(root.path("calcium").asDouble());
				recipe.setStarch(root.path("starch").asDouble());
				recipe.setFiber(root.path("fiber").asDouble());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new WebApplicationException(statusCode);
		}
		
		return recipe;
	}
	*/
}