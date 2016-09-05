import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TrackerTest {

    private Tracker<String, String> tracker;
    Set<String> returnedSet;

    @Before
    public void setup() {
        tracker = new Tracker<String, String>();
    }

    @Test
    public void testRequestTrackEmpty() {
        returnedSet = tracker.requestTrack("nonexistent-req");
        assertEquals(Collections.emptySet(), returnedSet);
    }

    @Test
    public void testAddReqTrack() {
        tracker.addTrack("req1", asList("src1", "src2", "src3"));

        returnedSet = tracker.requestTrack("src1");
        assertEquals(asSet("req1"), returnedSet);
    }

    @Test
    public void testAddReqDuplicate() {
        tracker.addTrack("req1", asList("src1", "src2", "src3"));
        tracker.addTrack("req1", asList("src1", "src2", "src3"));

        returnedSet = tracker.requestTrack("src1");
        assertEquals(asSet("req1"), returnedSet);
    }

    @Test
    public void testAddReqAdditional() {
        tracker.addTrack("req1", asList("src1", "src2", "src3"));
        tracker.addTrack("req1", asList("srcAddon"));

        returnedSet = tracker.requestTrack("src1");
        assertEquals(asSet("req1"), returnedSet);
        returnedSet = tracker.requestTrack("srcAddon");
        assertEquals(asSet("req1"), returnedSet);
    }

    @Test
    public void testReqReusedSources() {
        tracker.addTrack("req1", asList("src1", "src2", "src3"));
        tracker.addTrack("req2", asList("src2", "src3"));
        tracker.addTrack("req3", asList("src3"));

        returnedSet = tracker.requestTrack("src1");
        assertEquals(asSet("req1"), returnedSet);
        returnedSet = tracker.requestTrack("src2");
        assertEquals(asSet("req1", "req2"), returnedSet);
        returnedSet = tracker.requestTrack("src3");
        assertEquals(asSet("req1", "req2", "req3"), returnedSet);
    }

    private Set<String> asSet(String ... strings) {
        return new HashSet<String>(Arrays.asList(strings));
    }
    
    private List<String> asList(String ... strings) {
        return new LinkedList<String>(Arrays.asList(strings));
    }
}
