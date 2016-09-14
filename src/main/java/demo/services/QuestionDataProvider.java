package demo.services;

import demo.client.ActionTypeEnum;
import demo.client.ClientException;
import demo.client.JerseyClient;
import demo.client.QuestionResponseBean;
import demo.model.Question;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Max Malakhov on 9/14/16.
 */
@Component
public class QuestionDataProvider
{
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(QuestionDataProvider.class);

    /**
     * Get all question
     *
     * @return list of questions
     * @throws ClientException
     */
    public List<Question> getAll() throws ClientException {

        Response response = null;
        try
        {
            response = JerseyClient.request(ActionTypeEnum.QUESTIONS);
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
     * Get filtered question
     *
     * @param term - filter
     * @return list of questions
     * @throws ClientException
     */
    public List<Question> searchBy(String term) throws ClientException
    {

        Response response = null;
        try
        {
            response = JerseyClient.request(ActionTypeEnum.SEARCH, term);
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
