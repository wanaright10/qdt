package dong.wang;

import java.security.SecureRandom;
import java.util.*;

/**
 * Created by Victor Wang.
 * Created on 17/3/5 14:14
 */
public final class Util {
    private static Map<String, String> headers = initHeaders();
    private static final List<String> addressPoolOld = Arrays.asList("四川省成都市武侯区世纪城南路靠近英郡南区");
    private static Map<String, String> randomAndToken = initRandomAndToken();
    private static Map<String, Pair<String, String>> location = initLocation();


    private Util() {
    }

    private static Map<String, Pair<String, String>> initLocation() {
        location = new HashMap<>();

        location.put(addressPoolOld.get(0), new Pair<>("3.0547350253572105E7", "1.0408142196834784E8"));
        location.put(addressPoolOld.get(1), new Pair<>("3.0546442653572105E7", "1.0408100353834784E8"));
        location.put(addressPoolOld.get(2), new Pair<>("3.0545435463572105E7", "1.0408141123834784E8"));
        location.put(addressPoolOld.get(3), new Pair<>("3.0544308143572105E7", "1.0408263431834784E8"));
        return location;
    }

    private static Map<String, String> initRandomAndToken() {
        randomAndToken = new HashMap<>();
        randomAndToken.put("wVTI50", "B9B6CA567A3D9C0FAC693137E7C5F27E");
        randomAndToken.put("eYAtCY", "F71E4805FA3A3023C6B864DD56AD23B8");
        randomAndToken.put("83hyU5", "4039606CC0D2DE48C017A8267C5D01D3");
        randomAndToken.put("2619s3", "62625E620E27034D30A8DB09FA95A71D");
        randomAndToken.put("eYAtCY", "F71E4805FA3A3023C6B864DD56AD23B8");
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
        return 65;
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
