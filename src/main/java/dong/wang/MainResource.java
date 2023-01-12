package dong.wang;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class MainResource {
    private static int attendancePointAddressID = 28739;
    private static final int companyID = 15538;
    private static final int employeeID = 57569;
    private static final String HOST = "http://service.qdtsoft.com";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public HttpEntity<String> login() {
        Pair<String, String> randomAndToken = Util.getAPIRandomAndToken();

        String result = HttpRequest.post(HOST + "/VerificationService/LoginAuthorization")
                .headers(Util.getHeaders())
                .send("{\n" +
                        "    \"input\": {\n" +
                        "        \"AppList\": \"\",\n" +
                        "        \"ClientVersion\": \"5.1.8\",\n" +
                        "        \"CompanyName\": \"通用\",\n" +
                        "        \"PhoneModel\": \"SM-G930L\",\n" +
                        "        \"SystemEdition\": \"7.1.2\",\n" +
                        "        \"TelNumber\": \"18328482775\",\n" +
                        "        \"TelSnNumber\": \"305FB7A2-9CFE-4C91-AFA9-6B45B011580A\",\n" +
                        "        \"CompanyID\": " + companyID + ",\n" +
                        "        \"DBName\": \"HH_DNTX\",\n" +
                        "        \"EmployeeID\": " + employeeID + ",\n" +
                        "        \"GraspETypeID\": \"00000\",\n" +
                        "        \"MenuID\": 0,\n" +
                        "        \"OperatorID\": " + employeeID + ",\n" +
                        "        \"Random\": \"" + randomAndToken.getKey() + "\",\n" +
                        "        \"RequestTelSnNumber\": \"305FB7A2-9CFE-4C91-AFA9-6B45B011580A\",\n" +
                        "        \"RequestVersion\": 50106,\n" +
                        "        \"Token\": \"" + randomAndToken.getValue() + "\"\n" +
                        "    }\n" +
                        "}")
                .body();

        attendancePointAddressID = JSONObject.parseObject(result).getJSONObject("AttendancePoint").getJSONArray("AttendancePointAddresses").getJSONObject(0).getIntValue("ID");
        return new HttpEntity<>("重新登陆成功");
    }

    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public HttpEntity<String> checkOut(@RequestParam(required = false, defaultValue = "false") Boolean test) {
        try {
            if (!test) {
                return checkOutNew();
            } else {
                return test();
            }
        } catch (Exception e) {
            return new HttpEntity<>(e.getMessage());
        }
    }


    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public HttpEntity<String> checkIn(@RequestParam(required = false, defaultValue = "false") Boolean test) {
        try {
            if (!test) {
                return checkInNew();
            } else {
                return test();
            }
        } catch (Exception e) {
            return new HttpEntity<>(e.getMessage());
        }
    }

    private HttpEntity<String> checkOutNew() {
        Pair<String, String> randomAndToken = Util.getAPIRandomAndToken();
        String address = Util.getRandomAddress();
        Pair<String, String> location = Util.getLocation(address);

        address += ("精确到" + Util.getRandomAccuracy() + "米");

        String result = HttpRequest.post(HOST + "/AttendanceService/AttendanceCheckOutWithPhotoKeys")
                .headers(Util.getHeaders())
                .send("{\n" +
                        "    \"input\": {\n" +
                        "        \"SpaceUsage\": 0.0,\n" +
                        "        \"Address\": \"" + address + "\",\n" +
                        "        \"AttendancePointAddressID\": " + attendancePointAddressID + ",\n" +
                        "        \"Description\": \"\",\n" +
                        "        \"Distance\": " + Util.getRandomDistance() + ",\n" +
                        "        \"Latitude\": " + location.getKey() + ",\n" +
                        "        \"Longtitude\": " + location.getValue() + ",\n" +
                        "        \"Number\": 1,\n" +
                        "        \"CompanyID\": " + companyID + ",\n" +
                        "        \"EmployeeID\": " + employeeID + ",\n" +
                        "        \"MenuID\": 0,\n" +
                        "        \"OperatorID\": " + employeeID + ",\n" +
                        "        \"Random\": \"" + randomAndToken.getKey() + "\",\n" +
                        "        \"Token\": \"" + randomAndToken.getValue() + "\"\n" +
                        "    }\n" +
                        "}")
                .body();

        return new HttpEntity<>(result);
    }

    private HttpEntity<String> checkInNew() {
        Pair<String, String> randomAndToken = Util.getAPIRandomAndToken();
        String address = Util.getRandomAddress();
        Pair<String, String> location = Util.getLocation(address);

        address += ("精确到" + Util.getRandomAccuracy() + "米");

        String result = HttpRequest.post(HOST + "/AttendanceService/AttendanceCheckInWithPhotoKeys")
                .headers(Util.getHeaders())
                .send("{\n" +
                        "    \"input\": {\n" +
                        "        \"SpaceUsage\": 0.0,\n" +
                        "        \"Address\": \"" + address + "\",\n" +
                        "        \"AttendancePointAddressID\": " + attendancePointAddressID + ",\n" +
                        "        \"Description\": \"\",\n" +
                        "        \"Distance\": " + Util.getRandomDistance() + ",\n" +
                        "        \"Latitude\": " + location.getKey() + ",\n" +
                        "        \"Longtitude\": " + location.getValue() + ",\n" +
                        "        \"Number\": 1,\n" +
                        "        \"CompanyID\": " + companyID + ",\n" +
                        "        \"EmployeeID\": " + employeeID + ",\n" +
                        "        \"MenuID\": 0,\n" +
                        "        \"OperatorID\": " + employeeID + ",\n" +
                        "        \"Random\": \"" + randomAndToken.getKey() + "\",\n" +
                        "        \"Token\": \"" + randomAndToken.getValue() + "\"\n" +
                        "    }\n" +
                        "}")
                .body();

        return new HttpEntity<>(result);
    }

    private HttpEntity<String> test() {
        Pair<String, String> randomAndToken = Util.getAPIRandomAndToken();
        String address = Util.getRandomAddress();
        Pair<String, String> location = Util.getLocation(address);

        address += ("精确到" + Util.getRandomAccuracy() + "米");

        String result = "{'randomAndToken':" + randomAndToken + ",'address':" + address + ",'location':" + location + ",'distance':" + Util.getRandomDistance() + "}";
        return new HttpEntity<>(result);
    }
}
