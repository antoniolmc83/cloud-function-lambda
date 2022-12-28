package ca.neilwhite.cloudfunctiondynamodblambda.function;

import ca.neilwhite.cloudfunctiondynamodblambda.request.Request;
import ca.neilwhite.cloudfunctiondynamodblambda.response.Response;
import ca.neilwhite.cloudfunctiondynamodblambda.response.Session;
import org.springframework.stereotype.Component;
//import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
//import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
//import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class GetSessions implements Function<Request, Response> {

    private final String tableName;
    //private final DynamoDbClient dynamoDbClient;

    public GetSessions(String tableName /*, DynamoDbClient dynamoDbClient*/) {
        this.tableName = tableName;
        //this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public Response apply(Request request) {

        String userId = request.getUserId().toLowerCase();

        //Map<String, AttributeValue> expressionValues = new HashMap<>();
        //expressionValues.put(":userId", AttributeValue.builder().s(userId).build());

        /*QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("userId = :userId")
                .expressionAttributeValues(expressionValues).build();
        */

        //List<Map<String, AttributeValue>> queryResponse = dynamoDbClient.query(queryRequest).items();

        /*List<Session> sessions = queryResponse.isEmpty() ? List.of()
                : queryResponse.stream()
                .map(Session::from)
                .collect(Collectors.toList());

         */

        Session session = new Session( userId, new Date().getTime());
        session.setResponse( callApi() );
        List<Session> sessions = List.of( session  );


        return new Response(sessions);
    }

    private String callApi()  {

        String resp = null;

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("https://dummyjson.com/products/1"))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString() );
            resp = response.body();

        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            resp = e.getMessage();
        }



        return resp;
    }
}
