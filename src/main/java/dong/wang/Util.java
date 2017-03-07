package dong.wang;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor Wang.
 * Created on 17/3/5 14:14
 */
public final class Util {
    private static Map<String, String> headers = initHeaders();
    private static final List<String> addressPoolOld = Arrays.asList("天华二路201号附近", "天华二路181号附近", "世纪城南路附近", "天府软件园D区D2楼附近");
    private static final List<String> addressPoolNew = Arrays.asList("四川省成都市武侯区天华二路201号", "四川省成都市武侯区天华二路181号", "四川省成都市武侯区世纪城南路", "四川省成都市武侯区天府软件园D区D2楼");
    private static Map<String, String> randomAndToken = initRandomAndToken();
    private static Map<String, Pair<String, String>> location = initLocation();


    private Util() {
    }

    private static Map<String, Pair<String, String>> initLocation() {
        location = new HashMap<>();
        location.put(addressPoolNew.get(0), new Pair<>("3.0542350253572105E7", "1.0407342196834784E8"));
        location.put(addressPoolNew.get(1), new Pair<>("3.0542442653572105E7", "1.0407300353834784E8"));
        location.put(addressPoolNew.get(2), new Pair<>("3.0541435463572105E7", "1.0407341123834784E8"));
        location.put(addressPoolNew.get(3), new Pair<>("3.0540308143572105E7", "1.0407463431834784E8"));
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

    public static String getRandomAddress(boolean isNew) {
        SecureRandom random = new SecureRandom();
        List<String> addressPool;
        if (isNew) {
            addressPool = addressPoolNew;
        } else {
            addressPool = addressPoolOld;
        }
        return addressPool.get(random.nextInt(addressPool.size()));
    }

    public static int getRandomDistance() {
        return new SecureRandom().nextInt(250) + 1;
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
