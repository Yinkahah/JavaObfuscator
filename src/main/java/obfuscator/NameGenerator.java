package obfuscator;

import java.util.UUID;

public class NameGenerator {

    public static String generate(String prefix) {
        return prefix + "_" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6);
    }
}
