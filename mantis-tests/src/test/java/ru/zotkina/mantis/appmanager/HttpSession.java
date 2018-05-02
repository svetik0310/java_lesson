package ru.zotkina.mantis.appmanager;

        import org.apache.http.NameValuePair;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.CloseableHttpResponse;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.impl.client.LaxRedirectStrategy;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.util.EntityUtils;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app)
    {
        this.app=app;
        httpclient=HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }


public boolean login(String name,String pass) throws IOException {
        HttpPost post= new HttpPost(app.getProperty("web.baseUrl")+"/login.php");
        List<NameValuePair> params =new  ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username",name));
        params.add(new BasicNameValuePair("password", pass));
        params.add(new BasicNameValuePair("secure_session","on"));
        params.add(new BasicNameValuePair("return","index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response=httpclient.execute(post);
        String body= geTextFrom(response);
        return body.contains(String.format("<span class=\"italic\">%s</span>",name));
    }


    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        }finally {
            response.close();
        }
    }

    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl")+"/index.php");
        CloseableHttpResponse response= httpclient.execute(get);
        String body = geTextFrom(response);
        return  body.contains(String.format("<span class=\"italic\">%s</span>",username));
    }

        /* public boolean login(String username, String password) throws IOException {
        HttpPost post= new HttpPost(app.getProperty("web.baseUrl")+"/my_view_page.php");
        List<NameValuePair> params =new  ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username",username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session","on"));
        params.add(new BasicNameValuePair("return","my_view_page.php"));//"account_page.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response=httpclient.execute(post);
        String body= geTextFrom(response);
        //return body.contains(String.format("<input id=\"username\" name=\"username\" type=\"text\" placeholder=\"Username\"\n" +
          //      "\t\t\t\t\t\t   size=\"32\" maxlength=\"191\" value=\""+username+"\"\n" +
            //    "\t\t\t\t\t\t   class=\"form-control autofocus\">"));
       return body.contains(String.format("<span class=\"input\"><input id=\"username\" type=\"text\" name=\"username\" size=\"32\" maxlength=\"191\" value=\"administrator\" class=\"\" /></span>"));
    }*/

   /* public boolean password(String username, String password) throws IOException {
        HttpPost post= new HttpPost(app.getProperty("web.baseUrl")+"/account_page.php");
        List<NameValuePair> params =new  ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username",username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session","on"));
        params.add(new BasicNameValuePair("return","account_page.php"));//"account_page.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response=httpclient.execute(post);
        String body= geTextFrom(response);
       // return body.contains(String.format("<input id=\"username\" name=\"username\" type=\"text\" placeholder=\"Username\"\n" +
            //    "\t\t\t\t\t\t   size=\"32\" maxlength=\"191\" value=\"\"\n" +
               // "\t\t\t\t\t\t   class=\"form-control autofocus\">"));
       if(body.contains(String.format("<input type=\"hidden\" name=\"username\" value=\"administrator\" />Enter password for 'administrator'")))
       {
           HttpPost post1= new HttpPost(app.getProperty("web.baseUrl")+"/my_view_page.php");
           List<NameValuePair> param1s =new  ArrayList<NameValuePair>();
           param1s.add(new BasicNameValuePair("username",username));
           param1s.add(new BasicNameValuePair("password", password));
           param1s.add(new BasicNameValuePair("secure_session","on"));
           param1s.add(new BasicNameValuePair("return","my_view_page.php"));
           post1.setEntity(new UrlEncodedFormEntity(param1s));
           CloseableHttpResponse resp=httpclient.execute(post1);
           String b=geTextFrom(resp);
           return b.contains(String.format("0"));
       }
       else return false;
    }*/
}
