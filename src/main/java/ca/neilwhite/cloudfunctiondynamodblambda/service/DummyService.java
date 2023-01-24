package ca.neilwhite.cloudfunctiondynamodblambda.service;

import ca.neilwhite.cloudfunctiondynamodblambda.lambda.request.Request;
import ca.neilwhite.cloudfunctiondynamodblambda.lambda.response.Session;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

@Component
public class DummyService {


    public List<Session> createService(Request request) {
        String userId = request.getUserId().toLowerCase();
        Session session = new Session( userId, new Date().getTime());
        session.setResponse( callApi() );
        List<Session> sessions = List.of( session  );

        return sessions;

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
