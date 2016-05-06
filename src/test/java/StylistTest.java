import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExternalResource;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Joe");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void Stylist_instantiatesWithStylistName_String() {
    Stylist testStylist = new Stylist("Joe");
    assertEquals("Joe", testStylist.getStylistName());
  }

  @Test
  public void all_StylistListEmptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreSame() {
    Stylist firstStylist = new Stylist("Joe");
    Stylist secondStylist = new Stylist("Joe");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist testStylist = new Stylist("Joe");
    testStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(testStylist.getStylistId(), savedStylist.getStylistId());
  }

  @Test
  public void find_findStylistInDatabase_true() {
    Stylist testStylist = new Stylist("Joe");
    testStylist.save();
    Stylist savedStylist = Stylist.find(testStylist.getStylistId());
    assertTrue(testStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
    Stylist testStylist = new Stylist("Joe");
    testStylist.save();
    Client firstClient = new Client("Sara", testStylist.getStylistId());
    firstClient.save();
    Client secondClient = new Client("Betty", testStylist.getStylistId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
  }

  @Test
  public void update_updatesStylistName_true() {
    Stylist testStylist = new Stylist("Joe");
    testStylist.save();
    testStylist.update("Joey");
    assertEquals("Joey", Stylist.find(testStylist.getStylistId()).getStylistName());
  }

  @Test
  public void delete_deletesStylistName_true() {
    Stylist testStylist = new Stylist("Joe");
    testStylist.save();
    int testStylistId = testStylist.getStylistId();
    testStylist.delete();
    assertEquals(null, Stylist.find(testStylistId));
  }
}
