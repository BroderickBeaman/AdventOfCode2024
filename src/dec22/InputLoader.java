package dec22;

import framework.InputLoaderParent;

import java.util.List;

class InputLoader extends InputLoaderParent {
    static List<SecretNumber> loadSecretNumbers() {
        return loadLines().stream().map(SecretNumber::new).toList();
    }
}
