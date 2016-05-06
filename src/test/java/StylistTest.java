import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {
  
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
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Joe");
    assertEquals(true, testStylist instanceof Stylist);
  }
}
