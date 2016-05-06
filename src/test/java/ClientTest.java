import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExternalResource;
import java.util.Arrays;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client testClient = new Client("Sara", 1);
    assertEquals(true, testClient instanceof Client);
  }

  @Test
  public void getName_instantiatesWithName_String() {
    Client testClient = new Client("Sara", 1);
    assertEquals("Sara", testClient.getClientName());
  }

  @Test
  public void all_ClientListIsEmptyFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreSame() {
    Client firstClient = new Client("Sara", 1);
    Client secondClient = new Client("Sara", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client testClient = new Client("Sara", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client testClient = new Client("Sara", 1);
    testClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(testClient.getClientId(), savedClient.getClientId());
  }

  @Test
  public void find_findClientInDatabase_true() {
    Client testClient = new Client("Sara", 1);
    testClient.save();
    Client savedClient = Client.find(testClient.getClientId());
    assertTrue(testClient.equals(savedClient));
  }

  @Test
  public void update_updatesClientName_true() {
    Client testClient = new Client("Sara", 1);
    testClient.save();
    testClient.update("Betty");
    assertEquals("Betty", Client.find(testClient.getClientId()).getClientName());
  }

  @Test
  public void delete_deletesClientName_true() {
    Client testClient = new Client("Sara", 1);
    testClient.save();
    int testClientId = testClient.getClientId();
    testClient.delete();
    assertEquals(null, Client.find(testClientId));
  }
}
