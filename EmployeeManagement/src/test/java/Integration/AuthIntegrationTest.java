@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class, webEnvironment= SpringBootTest.webEnvironment.DEFINED_PORT)
public class AuthIntegrationTest {

    @Test
    public void testLogin() throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/auth");

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("email","dwang@training.ascending.com"));
        nameValuePairs.add(new BasicNameValuePair("password","123456789"));
        String payload = (new JSONArray()).put(nameValuePairs).toString();

        StringEntity entity = new StringEntity(payload);
        enitty.setContentType(ContentType.APPLICATION_JSON.getMineType());
        post.setEntity(entity);
        post.setHeader("Content-type","application/json");

        HttpResponse response = client.execute(post);

        assertEquals(200, response.getStatusLine().getStatusCode);
    }
}