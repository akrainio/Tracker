import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Collections.*;

public class Tracker<R, S> implements DependencyTracker<R, S> {
    private final Map<S, Set<R>> table = new HashMap<S, Set<R>>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();


    @Override
    public void trackRequest(R req, List<S> sources) {
        for (S source : sources) {
            lock.writeLock().lock();
            try {
                Set<R> got = table.get(source);
                if (got == null) {
                    Set<R> reqSet = new HashSet<R>();
                    reqSet.add(req);
                    table.put(source, reqSet);
                } else {
                    got.add(req);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    @Override
    public Set<R> getRequests(S source) {
        lock.readLock().lock();
        try {
            Set<R> set = table.get(source);
            if (set == null) {
                return Collections.emptySet();
            }
            return new HashSet<R>(set);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void clear() {
        table.clear();
    }
}
