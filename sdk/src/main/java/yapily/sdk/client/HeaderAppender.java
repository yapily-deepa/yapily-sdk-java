package yapily.sdk.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicHeader;

public class HeaderAppender {

    private List<Header> headers = new ArrayList<>();

    public void addHeader(String name, String value) {
        headers.add(new BasicHeader(name, value));
    }

    public void append(HttpRequestBase httpRequest) {
        for (Header header : headers) {
            httpRequest.setHeader(header);
        }
    }

}
