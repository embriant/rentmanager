package epf;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.utils.Clients;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientsTest {
    private Clients Clients = new Clients();
    @Test
    public void isLegal_should_return_true_when_age_is_over_18(){
        Client legalClient = new Client (1,"John","Doe","john.doe@ensta.fr", LocalDate.of(2001, 1, 8));
        assertTrue(Clients.isLegal(legalClient));
    }
    @Test
    public void isLegal_should_return_false_when_age_is_under_18(){
        Client legalClient = new Client (1,"John","Doe","john.doe@ensta.fr", LocalDate.of(2008, 1, 8));
        assertFalse(Clients.isLegal(legalClient));
    }
    @Test
    public void namesLengthIsOK_should_return_true_when_name_length_is_over_3(){
        Client legalClient = new Client (1,"John","Doe","john.doe@ensta.fr", LocalDate.of(2001, 1, 8));
        assertTrue(Clients.namesLengthOK(legalClient));
    }
    @Test
    public void namesLengthIsOK_should_return_false_when_name_length_is_under_3(){
        Client illegalClient = new Client (1,"Jo","Doe","john.doe@ensta.fr", LocalDate.of(2001, 1, 8));
        assertFalse(Clients.namesLengthOK(illegalClient));
    }
    @Test
    public void namesLengthIsOK_should_return_false_when_surname_length_is_under_3(){
        Client illegalClient = new Client (1,"John","Do","john.doe@ensta.fr", LocalDate.of(2001, 1, 8));
        assertFalse(Clients.namesLengthOK(illegalClient));
    }


}
