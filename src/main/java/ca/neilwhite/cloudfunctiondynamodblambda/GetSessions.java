package ca.neilwhite.cloudfunctiondynamodblambda;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GetSessions implements Function<Request, Response> {

    private final String tableName;
    private final DynamoDbClient dynamoDbClient;

    public GetSessions(String tableName, DynamoDbClient dynamoDbClient) {
        this.tableName = tableName;
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public Response apply(Request request) {

        String userId = request.getUserId().toLowerCase();

        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":userId", AttributeValue.builder().s(userId).build());

        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("userId = :userId")
                .expressionAttributeValues(expressionValues).build();


        List<Map<String, AttributeValue>> queryResponse = dynamoDbClient.query(queryRequest).items();

        List<Session> sessions = queryResponse.isEmpty() ? List.of()
                : queryResponse.stream()
                .map(Session::from)
                .collect(Collectors.toList());

        return new Response(sessions);
    }
}
