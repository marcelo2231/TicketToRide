package com.emmettito.tickettorideserver.communication.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultHandler  implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String uri = httpExchange.getRequestURI().toString();
        String filePathStr = uri;
        if (uri.equals("/")) { filePathStr = "lib/web/index.html"; }
        else{ filePathStr = "data/web" + filePathStr; }

        Path filePath = FileSystems.getDefault().getPath(filePathStr);
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        Files.copy(filePath, httpExchange.getResponseBody());
        httpExchange.getResponseBody().close();
    }
}
