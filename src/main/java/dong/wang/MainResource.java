package dong.wang;

import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
@RequestMapping("/check")
public class MainResource {
    private static final int attendancePointAddressID = 8952;
    private static final int companyID = 15538;
    private static final int employeeID = 57569;
    private static final String HOST = "http://service.qdtsoft.com";

    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public HttpEntity<String> checkOut(@RequestParam(required = false, defaultValue = "false") Boolean callNew,
                                       @RequestParam(required = false, defaultValue = "false") Boolean test) {
        try {
            if (!test) {
                if (callNew) {
                    return checkOutNew();
                } else {
                    return checkOutOld();
                }
            } else {
                return test();
            }
        } catch (Exception e) {
            return new HttpEntity<>(e.getMessage());
        }
    }


    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public HttpEntity<String> checkIn(@RequestParam(required = false, defaultValue = "false") Boolean callNew,
                                      @RequestParam(required = false, defaultValue = "false") Boolean test) {
        try {
            if (!test) {
                if (callNew) {
                    return checkInNew();
                } else {
                    return checkInOld();
                }
            } else {
                return test();
            }
        } catch (Exception e) {
            return new HttpEntity<>(e.getMessage());
        }
    }

    private HttpEntity<String> checkOutOld() {
        int precision = new SecureRandom().nextInt(30) + 1;
        int distance = new SecureRandom().nextInt(250) + 1;
        String address = Util.getRandomAddress(false) + " (精确到" + precision + ".00米) 距离考勤点" + distance + "米";

        String result = HttpRequest.post(HOST + "/AttendanceService/AttendanceCheckOut")
                .headers(Util.getHeaders())
                .send("{\n" +
                        "\t\"attendanceCheckOutInputValue\": {\n" +
                        "\t\t\"Address\": \"" + address + "\",\n" +
                        "\t\t\"AttendancePointAddressID\": " + attendancePointAddressID + ",\n" +
                        "\t\t\"Description\": \"\",\n" +
                        "\t\t\"Distance\": 1,\n" +
                        "\t\t\"Latitude\": 0,\n" +
                        "\t\t\"Longtitude\": 0,\n" +
                        "\t\t\"Number\": 1,\n" +
                        "\t\t\"CompanyID\": " + companyID + ",\n" +
                        "\t\t\"EmployeeID\": " + employeeID + ",\n" +
                        "\t\t\"OperatorID\": " + employeeID + "\n" +
                        "\t}\n" +
                        "}")
                .body();

        return new HttpEntity<>(result);
    }

    private HttpEntity<String> checkInOld() {
        int precision = new SecureRandom().nextInt(30) + 1;
        int distance = new SecureRandom().nextInt(250) + 1;
        String address = Util.getRandomAddress(false) + " (精确到" + precision + ".00米) 距离考勤点" + distance + "米";

        String result = HttpRequest.post(HOST + "/AttendanceService/AttendanceCheckIn")
                .headers(Util.getHeaders())
                .send("{\n" +
                        "\t\"attendanceCheckInInputValue\": {\n" +
                        "\t\t\"Address\": \"" + address + "\",\n" +
                        "\t\t\"AttendancePointAddressID\": " + attendancePointAddressID + ",\n" +
                        "\t\t\"Description\": \"\",\n" +
                        "\t\t\"Distance\": 1,\n" +
                        "\t\t\"Latitude\": 0,\n" +
                        "\t\t\"Longtitude\": 0,\n" +
                        "\t\t\"Number\": 1,\n" +
                        "\t\t\"CompanyID\": " + companyID + ",\n" +
                        "\t\t\"EmployeeID\": " + employeeID + ",\n" +
                        "\t\t\"OperatorID\": " + employeeID + "\n" +
                        "\t}\n" +
                        "}")
                .body();

        return new HttpEntity<>(result);
    }

    // ==================   新的接口  ========================

    private HttpEntity<String> checkOutNew() {
        Pair<String, String> randomAndToken = Util.getAPIRandomAndToken();
        String address = Util.getRandomAddress(true);
        Pair<String, String> location = Util.getLocation(address);

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
        String address = Util.getRandomAddress(true);
        Pair<String, String> location = Util.getLocation(address);

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
        String address = Util.getRandomAddress(true);
        Pair<String, String> location = Util.getLocation(address);


        String result = "{'randomAndToken':" + randomAndToken + ",'address':" + address + ",'location':" + location + "}";
        return new HttpEntity<>(result);
    }
}
