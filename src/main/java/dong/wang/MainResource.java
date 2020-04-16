package dong.wang;

import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class MainResource {
    private static final int attendancePointAddressID = 8952;
    private static final int companyID = 15538;
    private static final int employeeID = 57569;
    private static final String HOST = "http://service.qdtsoft.com";

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

        address += (" 精确到" + Util.getRandomAccuracy() + "米");

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

        address += (" (精确到" + Util.getRandomAccuracy() + ".00米)");

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

        address += (" (精确到" + Util.getRandomAccuracy() + ".00米)");

        String result = "{'randomAndToken':" + randomAndToken + ",'address':" + address + ",'location':" + location + "}";
        return new HttpEntity<>(result);
    }
}
