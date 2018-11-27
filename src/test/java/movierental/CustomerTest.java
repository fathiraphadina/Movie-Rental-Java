package movierental;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void test() {
        Customer customer = new Customer("Bob");
        
        customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("GoldenEye", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("ShortNew", Movie.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("LongNew", Movie.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3));
        customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4));
        
        assertEquals("Rental Record for Bob\n" +
                "\tJaws\t2.0\n" +
                "\tGoldenEye\t3.5\n" +
                "\tShortNew\t3.0\n" +
                "\tLongNew\t6.0\n" +
                "\tBambi\t1.5\n" + 
                "\tToy Story\t3.0\n" + 
                "Amount owed is 19.0\n" +
                "You earned 7 frequent renter points", customer.statement());
    }    
    
    @Test
    public void testCustomer() {
        Customer c = new CustomerBuilder().build();
        assertNotNull(c);
    }

    @Test
    public void testAddRental() {
        Customer customer2 = new CustomerBuilder().withName("Sallie").build();
        Movie movie1 = new Movie("Gone with the Wind", Movie.REGULAR);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        customer2.addRental(rental1);
    }

    @Test
    public void testGetName() {
        Customer c = new Customer("David");
        assertEquals("David", c.getName());
    }

    @Test
    public void statementForRegularMovie() {
        Movie movie1 = new Movie("Gone with the Wind", Movie.REGULAR);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "\tGone with the Wind\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.statement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForNewReleaseMovie() {
        Movie movie1 = new Movie("Star Wars", Movie.NEW_RELEASE);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "\tStar Wars\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points";
        String statement = customer2.statement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForChildrensMovie() {
        Movie movie1 = new Movie("Madagascar", Movie.CHILDRENS);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2
                = new CustomerBuilder()
                .withName("Sallie")
                .withRentals(rental1)
                .build();
        String expected = "Rental Record for Sallie\n" +
                "\tMadagascar\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.statement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForManyMovies() {
        Movie movie1 = new Movie("Madagascar", Movie.CHILDRENS);
        Rental rental1 = new Rental(movie1, 6); // 6 day rental
        Movie movie2 = new Movie("Star Wars", Movie.NEW_RELEASE);
        Rental rental2 = new Rental(movie2, 2); // 2 day rental
        Movie movie3 = new Movie("Gone with the Wind", Movie.REGULAR);
        Rental rental3 = new Rental(movie3, 8); // 8 day rental
        Customer customer1
                = new CustomerBuilder()
                .withName("David")
                .withRentals(rental1, rental2, rental3)
                .build();
        String expected = "Rental Record for David\n" +
                "\tMadagascar\t6.0\n" +
                "\tStar Wars\t6.0\n" +
                "\tGone with the Wind\t11.0\n" +
                "Amount owed is 23.0\n" +
                "You earned 4 frequent renter points";
        String statement = customer1.statement();
        assertEquals(expected, statement);
    }

    //TODO make test for price breaks in code.
}
