package ca.neilwhite.cloudfunctiondynamodblambda.lambda.function;

import ca.neilwhite.cloudfunctiondynamodblambda.lambda.request.Request;
import ca.neilwhite.cloudfunctiondynamodblambda.lambda.response.Response;
import ca.neilwhite.cloudfunctiondynamodblambda.service.DummyService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

//import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
//import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
//import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

@Component
public class GetSessions implements Function<Request, Response> {


    private final DummyService dummyService;
    public GetSessions(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @Override
    public Response apply(Request request) {

        return new Response( dummyService.createService(request) );
    }

}
