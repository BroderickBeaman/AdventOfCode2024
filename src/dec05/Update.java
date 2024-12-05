package dec05;

import java.util.List;

record Update(List<Long> operations) {

    long middle() {
        return operations().get(operations.size() / 2);
    }

}
