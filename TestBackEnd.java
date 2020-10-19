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
 * Test class for back end codes
 *
 * @author Deming Xu
 */
public class TestBackEnd {

    private SongDatabase songDatabase;    // test instance

    /**
     * Instantiate the instance before calling each test methods
     */
    @BeforeEach
    public void setup() {
        // initiate a database
        songDatabase = new SongDatabase();

        // clear all the data in the database
        songDatabase.clearDatabase();
        songDatabase.addSong("song0", "artist0", "jazz", 2000, 80, 3000);
        songDatabase.addSong("song1", "artist1", "jazz", 2000, 80, 3000);
        songDatabase.addSong("song2", "artist2", "pop", 2010, 120, 3000);
        songDatabase.addSong("song3", "artist2", "pop", 2011, 120, 3000);
    }

    /**
     * Test getSong method with valid song title
     */
    @Test
    public void testGetSong() {
        // test to get a song object
        Song song = songDatabase.getSong("song3");

        // Check the song object information
        if (!song.getArtist().equals("artist2") ||
                !song.getGenre().equals("pop") ||
                song.getYear() != 2011 ||
                song.getBpm() != 120 ||
                song.getDuration() != 3000) {
            fail("get method fails");
        }
    }

    /**
     * Test getSong method with a non-exist song title
     */
    @Test
    public void testGetNonExistSong() {
        // Get a non-exist song object and see if NoSuchElementException is thrown
        try {
            Song song = songDatabase.getSong("song4");
            fail("getSong method fails");
        } catch (NoSuchElementException ignore) {
        }
    }

    /**
     * Test hasSong method with valid song title
     */
    @Test
    public void testHasSong() {
        // test a contained song object in database
        if (!songDatabase.hasSong("song3")) {
            fail("hasSong method fails");
        }
    }

    /**
     * Test hasSong method with non-exist song title
     */
    @Test
    public void testHasNotSong() {
        // test a non-exist song object
        if (songDatabase.hasSong("song4")) {
            fail("hasSong method fails");
        }
    }

    /**
     * Test addSong method with valid song information
     */
    @Test
    public void testAddSong() {
        // test the addition is completed
        if (!songDatabase.addSong("song4", "artist3", "pop", 2001, 125, 3123)) {
            fail("addSong method fails");
        }

        // test whether the addition is successful
        if (!songDatabase.getSong("song4").getArtist().equals("artist3") ||
                !songDatabase.getSong("song4").getGenre().equals("pop") ||
                songDatabase.getSong("song4").getYear() != 2001 ||
                songDatabase.getSong("song4").getBpm() != 125 ||
                songDatabase.getSong("song4").getDuration() != 3123) {
            fail("get method fails");
        }
    }

    /**
     * Test addSong method with information contains duplicate song title
     */
    @Test
    public void testAddDuplicateSong() {
        // test to add a duplicate element
        if (songDatabase.addSong("song3", "artist3", "pop", 2001, 125, 3123)) {
            fail("addSong method fails");
        }
    }

    /**
     * Test updateArtist method with valid song title and newArtist
     */
    @Test
    public void testUpdateArtist() {
        // test to update the artist of song object
        if (!songDatabase.updateArtist("song1", "new Artist")) {
            fail("updateArtist method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("new Artist") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateArtist method with invalid input, like newArtist = null || newArtist = “”
     */
    @Test
    public void testUpdateArtistWithInvalidInput() {
        // test to update artist with invalid name
        if (songDatabase.updateArtist("song1", "")) {
            fail("updateArtist method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }

        // test to update artist with null
        if (songDatabase.updateArtist("song1", null)) {
            fail("updateArtist method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateArtist method with non exist song title
     */
    @Test
    public void testUpdateArtistWithNotExistSongTitle() {
        // test to update a non exist song object
        if (songDatabase.updateArtist("song4", "")) {
            fail("updateArtist method fails");
        }
    }

    /**
     * Test updateGenre method with valid song title and newArtist
     */
    @Test
    public void testUpdateGenre() {
        // test to update genre of song object
        if (!songDatabase.updateGenre("song1", "new Genre")) {
            fail("updateGenre method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("new Genre") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateGenre method with invalid input, like newArtist = null || newArtist = “”
     */
    @Test
    public void testUpdateGenreWithInvalidInput() {
        // test to update with invalid genre
        if (songDatabase.updateGenre("song1", "")) {
            fail("updateGenre method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }

        // test to update genre with null
        if (songDatabase.updateArtist("song1", null)) {
            fail("updateGenre method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateGenre method with non exist song title
     */
    @Test
    public void testUpdateGenreWithNotExistSongTitle() {
        // test to update a non-exist song object
        if (songDatabase.updateGenre("song4", "")) {
            fail("updateGenre method fails");
        }
    }

    /**
     * Test updateYear method with valid song title and newArtist
     */
    @Test
    public void testUpdateYear() {
        // test to update year of song object
        if (!songDatabase.updateYear("song1", 2020)) {
            fail("updateYear method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2020 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateYear method with invalid input, like newYear <= 0
     */
    @Test
    public void testUpdateYearWithInvalidInput() {
        // test to update with invalid year
        if (songDatabase.updateYear("song1", -2000)) {
            fail("updateYear method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateYear method with non exist song title
     */
    @Test
    public void testUpdateYearWithNotExistSongTitle() {
        // test to update a non-exist song object
        if (songDatabase.updateYear("song4", 2000)) {
            fail("updateYear method fails");
        }
    }

    /**
     * Test updateBpm method with valid song title and newArtist
     */
    @Test
    public void testUpdateBpm() {
        // test to update bpm of song object
        if (!songDatabase.updateBpm("song1", 200)) {
            fail("updateBpm method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 200 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateBpm method with invalid input, like newBpm <= 0
     */
    @Test
    public void testUpdateBpmWithInvalidInput() {
        // test to update with invalid bpm
        if (songDatabase.updateBpm("song1", -2000)) {
            fail("updateBpm method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateBpm method with non exist song title
     */
    @Test
    public void testUpdateBpmWithNotExistSongTitle() {
        // test to update a non-exist song object
        if (songDatabase.updateBpm("song4", 200)) {
            fail("updateBpm method fails");
        }
    }

    /**
     * Test updateDuration method with valid song title and newArtist
     */
    @Test
    public void testUpdateDuration() {
        // test to update duration of song object
        if (!songDatabase.updateDuration("song1", 2000)) {
            fail("updateDuration method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 2000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateDuration method with invalid input, like newDuration <= 0
     */
    @Test
    public void testUpdateDurationWithInvalidInput() {
        // test to update with invalid duration
        if (songDatabase.updateDuration("song1", -2000)) {
            fail("updateDuration method fails");
        }

        // check the target information
        if (!songDatabase.getSong("song1").getArtist().equals("artist1") ||
                !songDatabase.getSong("song1").getGenre().equals("jazz") ||
                songDatabase.getSong("song1").getYear() != 2000 ||
                songDatabase.getSong("song1").getBpm() != 80 ||
                songDatabase.getSong("song1").getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateDuration method with non exist song title
     */
    @Test
    public void testUpdateDurationWithNotExistSongTitle() {
        // test to update a non-exist song object
        if (songDatabase.updateBpm("song4", 2000)) {
            fail("updateDuration method fails");
        }
    }

    /**
     * Test getNumOfSong method if return correct num
     */
    @Test
    public void testGetNumOfSong() {
        // check if number of song is 4
        if (songDatabase.getNumOfSong() != 4) {
            fail("getNumOfSong method fails");
        }
    }

    /**
     * Test getGenreFrequency method if return correct frequency/percentage
     */
    @Test
    public void testGetGenreFrequency() {
        // check if the frequency of pop is 0.5
        if (songDatabase.getGenreFrequency("pop") != 0.5) {
            fail("getGenreFrequency method fails");
        }
    }
}
