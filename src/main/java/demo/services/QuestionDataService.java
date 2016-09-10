package demo.services;

import demo.client.*;
import demo.model.Question;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
            response = request(ActionTypeEnum.QUESTIONS, "");
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

        return parse(response).getQuestions();
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
            response = request(ActionTypeEnum.SEARCH, term);
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

        return parse(response).getQuestions();
    }

    private Response request(ActionTypeEnum action, String term)
    {
        return JerseyClient.buildClient(action)
                .queryParam(PARAM_SORT_TYPE, SortTypeEnum.ACTIVITY.getValue())
                .queryParam(PARAM_ORDER_TYPE, OrderTypeEnum.DESC.getValue())
                .queryParam(PARAM_INTITLE, term)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
    }

    private QuestionResponseBean parse(Response response) throws ClientException
    {
        try
        {
            return response.readEntity(QuestionResponseBean.class);
        }
        catch (Exception ex)
        {
            LOG.error(ex.getMessage(), ex);
            throw new ClientException(ex.getMessage());
        }
    }
}
