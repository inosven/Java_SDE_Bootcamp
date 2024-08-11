package Integration;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.example.ApplicationBootstrap;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthIntegrationTest {

    @Test
    public void testLogin() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/auth");

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("email","dwang@training.ascending.com"));
        nameValuePairs.add(new BasicNameValuePair("password","123456789"));
        String payload = (new JSONArray()).put(nameValuePairs).toString();

        StringEntity entity = new StringEntity(payload);
        entity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        post.setEntity(entity);
        post.setHeader("Content-type","application/json");

        HttpResponse response = client.execute(post);

        assertEquals(200, response.getStatusLine().getStatusCode());
    }
}