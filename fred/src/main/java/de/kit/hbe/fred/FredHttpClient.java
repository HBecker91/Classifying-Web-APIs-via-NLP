
package de.kit.hbe.fred;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig.Builder;
import org.asynchttpclient.Param;
import org.asynchttpclient.Response;

/**
 * Hello world!
 *
 */
public class FredHttpClient
{
  // Öffentliche Variable für die Base URL
  public static final String FRED_BASE_URI = "http://wit.istc.cnr.it/stlab-tools/fred";

  public static void main(String[] args)
  {
    try
    {
      // Hier wird der REST client initialisiert und instanziiert
      Builder builder = new DefaultAsyncHttpClientConfig.Builder();
      AsyncHttpClientConfig config = builder.build();
      AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(config);

      // URL url = new URL(FRED_BASE_URI); Only if needed
      Response response = null;

      // Das sind alle Parameter der REST-Schnittstelle. Diese kannst du nach belieben anpassen. Falls "" geschrieben wird, wird der Default-Wert verwendet.
      List<Param> queryParamList = new ArrayList<>();
      queryParamList.add(new Param("text", "Hallo ich bin ein Text"));
      queryParamList.add(new Param("prefix", "fred:"));
      queryParamList.add(new Param("namespace", "")); // Default: http://www.ontologydesignpatterns.org/ont/fred/domain.owl#
      queryParamList.add(new Param("wsd", "")); // Default: false
      queryParamList.add(new Param("wfd", "")); // Default: false
      queryParamList.add(new Param("wfd_profile", "b"));
      queryParamList.add(new Param("tense", "")); // Default: false
      queryParamList.add(new Param("roles", "")); // Default: false
      queryParamList.add(new Param("textannotation", "earmark ")); // earmark or nif
      queryParamList.add(new Param("semantic-subgraph", "")); // Default: false

      // Der GET Aufruf wird ausgeführt und in response finden sich alle Informationen
      response = asyncHttpClient.prepareGet(FRED_BASE_URI).setHeader(HttpHeaders.CONTENT_TYPE, "application/rdf+json")
          .setQueryParams(queryParamList).execute().get();

      // Hier erfolgt die Ausgabe des Bodys in der Kommandozeile, siehe Console in Eclipse
      System.out.println(response.getResponseBody());

      asyncHttpClient.close();

      System.exit(0);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
