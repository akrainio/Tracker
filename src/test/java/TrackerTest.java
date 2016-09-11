
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrackerTest {

    private Tracker<String, String> tracker;
    private DBTracker dbTracker;
    Set<String> returnedSet;

    @Before
    public void setup() {
        tracker = new Tracker<String, String>();
        dbTracker = new DBTracker();
    }

    @After
    public void breakdown() {
        tracker.clear();
    }

    @Test
    public void testRequestTrackEmpty() {
        returnedSet = dbTracker.getRequests("nonexistent-req");
        assertEquals(Collections.emptySet(), returnedSet);
    }

    @Test
    public void testAddReqTrack() {
        dbTracker.trackRequest("req1", asList("src1", "src2", "src3"));

        returnedSet = dbTracker.getRequests("src1");
        assertEquals(asSet("req1"), returnedSet);
    }

    @Test
    public void testAddReqDuplicate() {
        dbTracker.trackRequest("req1", asList("src1", "src2", "src3"));
        dbTracker.trackRequest("req1", asList("src1", "src2", "src3"));

        returnedSet = dbTracker.getRequests("src1");
        assertEquals(asSet("req1"), returnedSet);
    }

    @Test
    public void testAddReqAdditional() {
        dbTracker.trackRequest("req1", asList("src1", "src2", "src3"));
        dbTracker.trackRequest("req1", asList("srcAddon"));

        returnedSet = dbTracker.getRequests("src1");
        assertEquals(asSet("req1"), returnedSet);
        returnedSet = dbTracker.getRequests("srcAddon");
        assertEquals(asSet("req1"), returnedSet);
    }

    @Test
    public void testReqReusedSources() {
        dbTracker.trackRequest("req1", asList("src1", "src2", "src3"));
        dbTracker.trackRequest("req2", asList("src2", "src3"));
        dbTracker.trackRequest("req3", asList("src3"));

        returnedSet = dbTracker.getRequests("src1");
        assertEquals(asSet("req1"), returnedSet);
        returnedSet = dbTracker.getRequests("src2");
        assertEquals(asSet("req1", "req2"), returnedSet);
        returnedSet = dbTracker.getRequests("src3");
        assertEquals(asSet("req1", "req2", "req3"), returnedSet);
    }

    private Set<String> asSet(String ... strings) {
        return new HashSet<String>(Arrays.asList(strings));
    }
    
    private List<String> asList(String ... strings) {
        return new LinkedList<String>(Arrays.asList(strings));
    }
}
