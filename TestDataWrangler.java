// --== CS400 File Header Information ==--
// Name: Deming Xu
// Email: dxu227@wisc.edu
// Team: CG
// Role: Test Engineer
// TA: Yeping Wang
// Lecturer: Gary Dahl
// Notes to Grader: None

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for data wrangler codes
 *
 * @author Deming Xu
 */
public class TestDataWrangler {

    private Database database;    // test instance

    /**
     * Instantiate the instance before calling each test methods
     */
    @BeforeEach
    public void setup() {
        // initiate a database
        database = new Database();

        // clear all the data in the database
        database.clearDatabase();
        database.addSong(new Song("song0", "artist0", "jazz", 2000, 80, 3000));
        database.addSong(new Song("song1", "artist1", "jazz", 2000, 80, 3000));
        database.addSong(new Song("song2", "artist2", "pop", 2010, 120, 3000));
        database.addSong(new Song("song3", "artist2", "pop", 2011, 120, 3000));
    }

    /**
     * test get method by add a song object, and trying to get the song object from the database
     */
    @Test
    public void testGetSong() {
        // test to get a song object
        Song song = database.getSong("song1");

        // Check the song object information
        if (!song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("get method fails");
        }
    }

    /**
     * test get method by get a non-exist song object
     */
    @Test
    public void testGetNotExistSong() {
        // test to get non exist song object, success if an NoSuchElementException is thrown
        try {
            Song song = database.getSong("song4");
            fail("get method fails");
        } catch (NoSuchElementException ignore) {
        }
    }

    /**
     * test add method by adding a song object, and check whether addition is successful with containsSong()
     */
    @Test
    public void testAddSong() {
        // test to add a song object
        if (!database.addSong(new Song("song4", "artist3", "pop", 2000, 120, 3000))) {
            fail("add method fails");
        }

        // get the song object added and check the info
        if (!database.getSong("song4").getArtist().equals("artist3") ||
                !database.getSong("song4").getGenre().equals("pop") ||
                database.getSong("song4").getYear() != 2000 ||
                database.getSong("song4").getBpm() != 120 ||
                database.getSong("song4").getDuration() != 3000) {
            fail("get method fails");
        }
    }

    /**
     * test add method by adding a duplicate song object
     */
    @Test
    public void testAddDuplicateSong() {
        // test to add a duplicate song object
        if (database.addSong(new Song("song3", "artist3", "pop", 2000, 120, 3000))) {
            fail("add method fails");
        }
    }

    /**
     * test update method by update a song
     */
    @Test
    public void updateSong() {
        // check whether the update is complete or not
        if (!database.updateSong(new Song("song1", "newArtist3", "newpop", 2001, 123, 3001))) {
            fail("updateSong method fails");
        }

        // check whether the information is updated
        if (!database.getSong("song1").getArtist().equals("newArtist3") ||
                !database.getSong("song1").getGenre().equals("newpop") ||
                database.getSong("song1").getYear() != 2001 ||
                database.getSong("song1").getBpm() != 123 ||
                database.getSong("song1").getDuration() != 3001) {
            fail("updateSong method fails");
        }
    }

    /**
     * test update method by update a non-exist song
     */
    @Test
    public void updateNonExistSong() {
        // update with non-exist song object
        if (database.updateSong(new Song("song5", "newArtist3", "newpop", 2001, 123, 3001))) {
            fail("updateSong method fails");
        }
    }

    /**
     * test contain method by passing an exist song title
     */
    @Test
    public void testContainsSong() {
        // test a contained song object
        if (!database.containsSong("song1")) {
            fail("contain method fails");
        }
    }

    /**
     * test contain method by passing an non-exist song title
     */
    @Test
    public void testNotContainsSong() {
        // test the case the song is not contained
        if (database.containsSong("song4")) {
            fail("contain method fails");
        }
    }

    /**
     * test clear method
     */
    @Test
    public void testClearDatabase() {
        // clear the database and see the size == 0
        database.clearDatabase();
        if (database.size() != 0) {
            fail("clear method fails");
        }
    }

    /**
     * test getFrequencyByGenre method
     */
    @Test
    public void testGetFrequencyByGenre() {
        // test the pop genre frequency
        if (database.getFrequencyByGenre("pop") != 0.5) {
            fail("getFrequencyByGenre method fails");
        }
    }

    /**
     * test size method
     */
    @Test
    public void testSize() {
        // test size whether is 4
        if (database.size() != 4) {
            fail("size method fails");
        }
    }
}
