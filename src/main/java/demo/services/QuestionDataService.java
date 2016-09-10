package demo.services;

import demo.client.*;
import demo.model.Question;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Max Malakhov on 9/7/16.
 */
@Service
public class QuestionDataService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(QuestionDataService.class);

    private static final String PARAM_ORDER_TYPE = "order";
    private static final String PARAM_SORT_TYPE = "sort";
    private static final String PARAM_INTITLE = "intitle";

    /**
     *
     * @return
     * @throws ClientException
     */
    public List<Question> allQuestions() throws ClientException {

        Response response = null;
        try
        {
            response = buildClient(ActionTypeEnum.QUESTIONS, "").get();
        }
        catch (Exception ex)
        {
            LOG.error(ex.getMessage(), ex);
            throw new ClientException(ex.getMessage());
        }

        if(response.getStatusInfo().getStatusCode() != 200)
        {
            LOG.error(response.getStatusInfo().getReasonPhrase());
            throw new ClientException(response.toString());
        }
        return response.readEntity(QuestionResponseBean.class).getQuestions();

    }

    /**
     *
     * @param term
     * @return
     * @throws ClientException
     */
    @Cacheable(cacheNames = "searchResult", key="#term")
    public List<Question> searchQuestions(String term) throws ClientException {

        Response response = null;
        try
        {

            response = buildClient(ActionTypeEnum.SEARCH, term).get();
        }
        catch (Exception ex)
        {
            LOG.error(ex.getMessage(), ex);
            throw new ClientException(ex.getMessage());
        }

        if(response.getStatusInfo().getStatusCode() != 200)
        {
            LOG.error(response.getStatusInfo().getReasonPhrase());
            throw new ClientException(response.toString());
        }
        return response.readEntity(QuestionResponseBean.class).getQuestions();
    }

    private Invocation.Builder buildClient(ActionTypeEnum action, String term)
    {
        return JerseyClient.buildClient(action)
                .queryParam(PARAM_SORT_TYPE, SortTypeEnum.ACTIVITY.getValue())
                .queryParam(PARAM_ORDER_TYPE, OrderTypeEnum.DESC.getValue())
                .queryParam(PARAM_INTITLE, term)
                .request(MediaType.APPLICATION_JSON_TYPE);
    }
}
