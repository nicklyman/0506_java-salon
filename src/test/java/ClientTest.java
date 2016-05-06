import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      String deleteClientsQuery = "DELETE FROM clients *;";
      con.createQuery(deleteStylistsQuery).executeUpdate();
      con.createQuery(deleteClientsQuery).executeUpdate();
    }
  }

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
}
