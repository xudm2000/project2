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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for front end
 *
 * @author Deming Xu
 */
public class TestFrontEnd {

    /**
     * clear the Main before calling each test methods
     */
    @BeforeEach
    public void setup() {
        Main.clear();
        Main.addSong("song0", "artist0", "jazz", "2000", "80", "3000");
        Main.addSong("song1", "artist1", "jazz", "2000", "80", "3000");
        Main.addSong("song2", "artist2", "pop", "2010", "120", "3000");
        Main.addSong("song3", "artist2", "pop", "2011", "120", "3000");
    }

    /**
     * Test getSong method with valid song title
     */
    @Test
    public void testGetSong() {
        // test to get a song object
        Song song = Main.getSong("song3");

        // Check the song object information
        if (song == null ||
                !song.getArtist().equals("artist2") ||
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
        // Get a non-exist song object
        if (Main.getSong("song4") != null) {
            fail("getSong method fails");
        }
    }

    /**
     * Test contains method with valid song title
     */
    @Test
    public void testContains() {
        // test a contained song object in database
        if (!Main.contains("song3")) {
            fail("hasSong method fails");
        }
    }

    /**
     * Test contains method with non-exist song title
     */
    @Test
    public void testContainsNotSong() {
        // test a non-exist song object
        if (Main.contains("song4")) {
            fail("hasSong method fails");
        }
    }

    /**
     * Test addSong method with valid song information
     */
    @Test
    public void testAddSong() {
        // test the addition is completed
        if (!Main.addSong("song4", "artist3", "pop", "2001", "125", "3123")) {
            fail("addSong method fails");
        }

        // test whether the addition is successful
        Song song = Main.getSong("song4");
        if (song == null ||
                !song.getArtist().equals("artist3") ||
                !song.getGenre().equals("pop") ||
                song.getYear() != 2001 ||
                song.getBpm() != 125 ||
                song.getDuration() != 3123) {
            fail("get method fails");
        }
    }

    /**
     * Test addSong method with information contains duplicate song title
     */
    @Test
    public void testAddDuplicateSong() {
        // test to add a duplicate element
        if (Main.addSong("song3", "artist3", "pop", "2001", "125", "3123")) {
            fail("addSong method fails");
        }
    }

    /**
     * Test addSong method with invalid input
     */
    @Test
    public void testAddInvalidSong() {
        // test to add a duplicate element
        if (Main.addSong("song3", "artist3", "pop", "-2001", "-125", "-3123")) {
            fail("addSong method fails");
        }
    }

    /**
     * Test updateArtist method with valid song title and newArtist
     */
    @Test
    public void testUpdateArtist() {
        // test to update the artist of song object
        if (!Main.updateArtist("song1", "new Artist")) {
            fail("updateArtist method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("new Artist") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateArtist method with invalid input, like newArtist = null || newArtist = “”
     */
    @Test
    public void testUpdateArtistWithInvalidInput() {
        // test to update artist with invalid name
        if (Main.updateArtist("song1", "")) {
            fail("updateArtist method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }

        // test to update artist with null
        if (Main.updateArtist("song1", null)) {
            fail("updateArtist method fails");
        }

        // check the target information
        song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }

        // test to update artist with null
        if (Main.updateArtist("song1", null)) {
            fail("updateArtist method fails");
        }

        // check the target information
        song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateArtist method with non exist song title
     */
    @Test
    public void testUpdateArtistWithNotExistSongTitle() {
        // test to update a non exist song object
        if (Main.updateArtist("song4", "")) {
            fail("updateArtist method fails");
        }
    }

    /**
     * Test updateGenre method with valid song title and newArtist
     */
    @Test
    public void testUpdateGenre() {
        // test to update genre of song object
        if (!Main.updateGenre("song1", "new Genre")) {
            fail("updateGenre method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("new Genre") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateGenre method with invalid input, like newArtist = null || newArtist = “”
     */
    @Test
    public void testUpdateGenreWithInvalidInput() {
        // test to update with invalid genre
        if (Main.updateGenre("song1", "")) {
            fail("updateGenre method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }

        // test to update genre with null
        if (Main.updateArtist("song1", null)) {
            fail("updateGenre method fails");
        }

        // check the target information
        song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateGenre method with non exist song title
     */
    @Test
    public void testUpdateGenreWithNotExistSongTitle() {
        // test to update a non-exist song object
        if (Main.updateGenre("song4", "")) {
            fail("updateGenre method fails");
        }
    }

    /**
     * Test updateYear method with valid song title and newArtist
     */
    @Test
    public void testUpdateYear() {
        // test to update year of song object
        if (!Main.updateYear("song1", "2020")) {
            fail("updateYear method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2020 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateYear method with invalid input, like newYear <= 0, or non-digit
     */
    @Test
    public void testUpdateYearWithInvalidInput() {
        // test to update with invalid year
        if (Main.updateYear("song1", "-2000")) {
            fail("updateYear method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }

        // test to update with invalid year
        if (Main.updateYear("song1", "aaaa")) {
            fail("updateYear method fails");
        }

        // check the target information
        song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateYear method with non exist song title
     */
    @Test
    public void testUpdateYearWithNotExistSongTitle() {
        // test to update a non-exist song object
        if (Main.updateYear("song4", "2000")) {
            fail("updateYear method fails");
        }
    }

    /**
     * Test updateBpm method with valid song title and newArtist
     */
    @Test
    public void testUpdateBpm() {
        // test to update bpm of song object
        if (!Main.updateBpm("song1", "200")) {
            fail("updateBpm method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 200 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateBpm method with invalid input, like newBpm <= 0, or non-digit
     */
    @Test
    public void testUpdateBpmWithInvalidInput() {
        // test to update with invalid bpm
        if (Main.updateBpm("song1", "-2000")) {
            fail("updateBpm method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }

        // test to update with invalid bpm
        if (Main.updateBpm("song1", "aaaa")) {
            fail("updateBpm method fails");
        }

        // check the target information
        song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateBpm method with non exist song title
     */
    @Test
    public void testUpdateBpmWithNotExistSongTitle() {
        // test to update a non-exist song object
        if (Main.updateBpm("song4", "200")) {
            fail("updateBpm method fails");
        }
    }

    /**
     * Test updateDuration method with valid song title and newArtist
     */
    @Test
    public void testUpdateDuration() {
        // test to update duration of song object
        if (!Main.updateDuration("song1", "2000")) {
            fail("updateDuration method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 2000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateDuration method with invalid input, like newDuration <= 0, or non-digit
     */
    @Test
    public void testUpdateDurationWithInvalidInput() {
        // test to update with invalid duration
        if (Main.updateDuration("song1", "-2000")) {
            fail("updateDuration method fails");
        }

        // check the target information
        Song song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }

        // test to update with invalid duration
        if (Main.updateDuration("song1", "aaaa")) {
            fail("updateDuration method fails");
        }

        // check the target information
        song = Main.getSong("song1");
        if (song == null ||
                !song.getArtist().equals("artist1") ||
                !song.getGenre().equals("jazz") ||
                song.getYear() != 2000 ||
                song.getBpm() != 80 ||
                song.getDuration() != 3000) {
            fail("updateSong method fails");
        }
    }

    /**
     * Test updateDuration method with non exist song title
     */
    @Test
    public void testUpdateDurationWithNotExistSongTitle() {
        // test to update a non-exist song object
        if (Main.updateBpm("song4", "2000")) {
            fail("updateDuration method fails");
        }
    }

    /**
     * Test getGenreFrequency method if return correct percentage "%"
     */
    @Test
    public void testGetGenreFrequency() {
        // check if the frequency of pop is 0.5
        if (Main.getGenreFrequencyInPercent("pop").equals("0.5%")) {
            fail("getGenreFrequency method fails");
        }
    }
}
