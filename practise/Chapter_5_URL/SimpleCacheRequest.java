// SimpleCacheRequest.java
package practise.Chapter_5_URL;

import java.io.*;
import java.net.*;

public class SimpleCacheRequest extends CacheRequest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Override
    public OutputStream getBody() throws IOException {
        return out;
    }

    @Override
    public void abort() {
        out.reset();
    }

    public byte[] getData() {
        return out.size() == 0 ? null : out.toByteArray();
    }
}