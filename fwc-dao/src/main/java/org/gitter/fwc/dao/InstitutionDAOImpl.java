package org.gitter.fwc.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.gitter.fwc.ckan.CkanResponseEntity;
import org.gitter.fwc.exception.DataAccessException;

import com.fasterxml.jackson.databind.ObjectMapper;

@Stateless
public class InstitutionDAOImpl implements InstitutionDAO {
    private static final Logger logger = Logger.getLogger(InstitutionDAOImpl.class);

    private static final String CKAN_DATA_API_URL = "https://inventory.data.gov/api/";

    private static final String CKAN_RESOURCE_ID = "38625c3d-5388-4c16-a30f-d105432553a4";
    private static final String SEARCH_SERVICE = "action/datastore_search";

    private static final String QUERY_SERVICE = "action/datastore_search_sql";

    @Override
    public List<Map<String, String>> findByKeyword(String keyword) {
        String url = buildSearchUrl(keyword);
        HttpEntity entity = doGet(url);
        return processEntity(entity);
    }

    /*
     * The clunky query service doesn't seem to work correctly. I kept getting
     * errors that the column I was querying didn't exist. Perhaps I'm missing
     * something, but my url was formatted correctly according to the
     * documentation.
     */
    @SuppressWarnings("unused")
    private String buildQueryUrl(String whereClause) {
        try {
            return new StringBuilder()
                    .append(CKAN_DATA_API_URL)
                    .append(QUERY_SERVICE)
                    .append("?sql=")
                    .append(URLEncoder.encode("select * from \"", "UTF-8"))
                    .append(CKAN_RESOURCE_ID)
                    .append(URLEncoder.encode("\" ", "UTF-8"))
                    .append(URLEncoder.encode(whereClause, "UTF-8"))
                    .toString();

        } catch (UnsupportedEncodingException e) {
            logger.error("Unable to build the search URL", e);
            throw new DataAccessException("Unable to build the search URL", e);
        }
    }

    private String buildSearchUrl(String searchTerm) {
        try {
            return new StringBuilder()
                    .append(CKAN_DATA_API_URL)
                    .append(SEARCH_SERVICE)
                    .append("?resource_id=")
                    .append(CKAN_RESOURCE_ID)
                    .append("&q=")
                    .append(URLEncoder.encode(searchTerm, "UTF-8"))
                    .toString();
        } catch (UnsupportedEncodingException e) {
            logger.error("Unable to build the search URL", e);
            throw new DataAccessException("Unable to build the search URL", e);
        }
    }

    private HttpEntity doGet(String url) {
        HttpEntity entity = null;
        logger.debug("Attempting to fetch resource at: " + url);

        try {
            HttpResponse response = Request.Get(url).connectTimeout(2000).socketTimeout(2000)
                    .execute()
                    .returnResponse();
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new DataAccessException("Error retrieving data from CKAN services: "
                        + response.getStatusLine().getReasonPhrase());
            }

            entity = response.getEntity();
        } catch (IllegalArgumentException | IOException e) {
            logger.error("Unable to request data from CKAN services", e);
            throw new DataAccessException("Unable to request data from CKAN services", e);
        }

        return entity;
    }

    private List<Map<String, String>> processEntity(HttpEntity entity) {
        try {
            byte[] bytes = EntityUtils.toByteArray(entity);
            logger.debug("Result: " + new String(bytes, "UTF-8"));

            CkanResponseEntity ckanEntity = new ObjectMapper().readValue(bytes,
                    CkanResponseEntity.class);
            return ckanEntity.getResult().getRecords();
        } catch (ParseException | IOException e) {
            logger.error("Unable to parse result", e);
            throw new DataAccessException("Unable to parse result", e);
        }
    }
}
