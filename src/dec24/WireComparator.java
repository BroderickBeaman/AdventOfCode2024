package dec24;

import java.util.Comparator;
import java.util.Map;

class WireComparator implements Comparator<Map.Entry<String, Boolean>> {
    @Override
    public int compare(Map.Entry<String, Boolean> o1, Map.Entry<String, Boolean> o2) {
        return o1.getKey().compareTo(o2.getKey());
    }
}
