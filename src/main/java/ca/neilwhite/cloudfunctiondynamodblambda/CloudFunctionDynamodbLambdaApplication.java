package ca.neilwhite.cloudfunctiondynamodblambda;

import ca.neilwhite.cloudfunctiondynamodblambda.request.Request;
import ca.neilwhite.cloudfunctiondynamodblambda.response.Response;
import ca.neilwhite.cloudfunctiondynamodblambda.response.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.SerializationHint;
import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@SerializationHint(types = {Request.class, Response.class, Session.class})
@SpringBootApplication
public class CloudFunctionDynamodbLambdaApplication {

	Region awsRegion = Region.US_EAST_1;

	public static void main(String[] args) {
		SpringApplication.run(CloudFunctionDynamodbLambdaApplication.class, args);
	}

	/*
	@Bean
	public DynamoDbClient dynamoDbClient() {
		return DynamoDbClient.builder().region(awsRegion).build();
	}

	 */

	@Bean
	public String tableName(){
		return "sessionsRefactored";
	}
}
