import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class HttpURLConnectionDemo {
    public static void main(String[] args) {
        String targetUrl = "https://jsonplaceholder.typicode.com/posts";
        String proxyHost = "proxy.example.com"; // Replace with actual proxy if needed
        int proxyPort = 8080;

        try {
            // 1. Different Request Methods
            System.out.println("----- GET Request -----");
            sendRequest(targetUrl, "GET", null, null);
            
            System.out.println("\n----- POST Request -----");
            String postData = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
            sendRequest(targetUrl, "POST", postData, "application/json");
            
            System.out.println("\n----- PUT Request -----");
            String putData = "{\"id\":1,\"title\":\"updated\",\"body\":\"content\",\"userId\":1}";
            sendRequest(targetUrl + "/1", "PUT", putData, "application/json");
            
            System.out.println("\n----- DELETE Request -----");
            sendRequest(targetUrl + "/1", "DELETE", null, null);

            // 2. Proxy Configuration
            System.out.println("\n----- With Proxy -----");
            sendRequestWithProxy(targetUrl, "GET", proxyHost, proxyPort);

            // 3. Streaming Mode
            System.out.println("\n----- Streaming Mode -----");
            streamResponse(targetUrl);

        } catch (Exception e) {
            System.err.println("Main execution failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void sendRequest(String urlString, String method, String data, String contentType) {
        HttpURLConnection connection = null;
        try {
            URL url = new URI(urlString).toURL();
            connection = (HttpURLConnection) url.openConnection();
            
            // Set request method
            connection.setRequestMethod(method);
            
            // Set headers
            if (contentType != null) {
                connection.setRequestProperty("Content-Type", contentType);
            }
            connection.setRequestProperty("Accept", "application/json");

            // Enable output for POST/PUT
            if (data != null && (method.equals("POST") || method.equals("PUT"))) {
                connection.setDoOutput(true);
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = data.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            // Handle response
            handleResponse(connection);

        } catch (URISyntaxException e) {
            System.err.println("Invalid URI: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
        } catch (ProtocolException e) {
            System.err.println("Protocol error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error during " + method + " request: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static void sendRequestWithProxy(String urlString, String method, String proxyHost, int proxyPort) {
        HttpURLConnection connection = null;
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            URL url = new URI(urlString).toURL();
            connection = (HttpURLConnection) url.openConnection(proxy);
            
            connection.setRequestMethod(method);
            System.out.println("Using proxy: " + proxy);

            handleResponse(connection);

        } catch (URISyntaxException e) {
            System.err.println("Invalid URI: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
        } catch (ProtocolException e) {
            System.err.println("Protocol error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error with proxy: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static void streamResponse(String urlString) {
        HttpURLConnection connection = null;
        try {
            URL url = new URI(urlString).toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Read response in streaming mode
            try (InputStream is = connection.getInputStream();
                 BufferedReader reader = new BufferedReader(
                     new InputStreamReader(is, StandardCharsets.UTF_8))) {
                
                System.out.println("Streaming response...");
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Received chunk: " + line.substring(0, Math.min(50, line.length())) + "...");
                }
            }
        } catch (URISyntaxException e) {
            System.err.println("Invalid URI: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
        } catch (ProtocolException e) {
            System.err.println("Protocol error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error during streaming: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static void handleResponse(HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        System.out.println("Response Code: " + status);
        
        try {
            if (status >= 200 && status < 300) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Response Body: " + 
                        response.toString().substring(0, Math.min(100, response.length())) + "...");
                }
            } else {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Error Response: " + response);
                }
            }
            
            // Print headers
            System.out.println("Response Headers:");
            connection.getHeaderFields().forEach((k, v) -> 
                System.out.println((k != null ? k : "null") + ": " + v));
        } catch (IOException e) {
            System.err.println("Error reading response: " + e.getMessage());
            throw e;
        }
    }
}