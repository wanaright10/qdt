package dong.wang;

import java.security.SecureRandom;
import java.util.*;

/**
 * Created by Victor Wang.
 * Created on 17/3/5 14:14
 */
public final class Util {
    private static Map<String, String> headers = initHeaders();
    private static final List<String> addressPoolOld = Collections.singletonList("四川省成都市武侯区天府大道中段靠近天府软件园C区");
    private static Map<String, String> randomAndToken = initRandomAndToken();
    private static Map<String, Pair<String, String>> location = initLocation();


    private Util() {
    }

    private static Map<String, Pair<String, String>> initLocation() {
        location = new HashMap<>();

        location.put(addressPoolOld.get(0), new Pair<>("3.0547350253572105E7", "1.0408142196834784E8"));
        return location;
    }

    private static Map<String, String> initRandomAndToken() {
        randomAndToken = new HashMap<>();
        randomAndToken.put("k5fHif", "AE973620F45085658EEE828E9EEB6E74");
        randomAndToken.put("0GZ4Wk", "12A7330185C2A1D155D0093A9FDD9AB7");
        randomAndToken.put("9RmZIA", "7C069874F69F726503EB7EF0AD870424");
        randomAndToken.put("z64bAa", "061BAF614AD82E3E4EEB60AB361ECC96");
        randomAndToken.put("pszpRc", "D35DC139C1A1C28CBF1326BCCFCD7923");
        randomAndToken.put("XwsRLX", "66F5426A5A54C2E9BC86089AAE183F40");
        return randomAndToken;
    }

    private static Map<String, String> initHeaders() {
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 6.0.1; MI 4LTE MIUI/V8.2.1.0.MXDCNDL)");
        headers.put("Host", "service.qdtsoft.com");
        return headers;
    }

    public static Map<String, String> getHeaders() {
        if (headers == null) {
            headers = initHeaders();
        }
        return headers;
    }

    public static String getRandomAddress() {
        SecureRandom random = new SecureRandom();
        return addressPoolOld.get(random.nextInt(addressPoolOld.size()));
    }

    public static int getRandomDistance() {
        return 200 + new SecureRandom().nextInt(200);
    }
    public static int getRandomAccuracy() {
        return 40 + new SecureRandom().nextInt(40);
    }

    public static Pair<String, String> getAPIRandomAndToken() {
        if (randomAndToken == null) {
            randomAndToken = initRandomAndToken();
        }

        SecureRandom random = new SecureRandom();
        List<String> keys = new LinkedList<>(randomAndToken.keySet());
        String key = keys.get(random.nextInt(keys.size()));

        return new Pair<>(key, randomAndToken.get(key));
    }

    public static Pair<String, String> getLocation(String address) {
        if (location == null) {
            location = initLocation();
        }

        return location.get(address);
    }
}
