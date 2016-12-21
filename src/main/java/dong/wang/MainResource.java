package dong.wang;

import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/check")
public class MainResource {
    private static final int attendancePointAddressID = 8952;
    private static final int companyID = 15538;
    private static final int employeeID = 57569;
    private static final List<String> addressPool = Arrays.asList("天华二路201号附近", "天华二路181号附近", "世纪城南路附近", "天府软件园D区D2楼附近");

    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public HttpEntity<String> checkOut() {
        int precision = new SecureRandom().nextInt(30) + 1;
        int distance = new SecureRandom().nextInt(250) + 1;
        String address = randomAddress() + " (精确到" + precision + ".00米) 距离考勤点" + distance + "米";

        String result = HttpRequest.post("http://service.qdtsoft.com/AttendanceService/AttendanceCheckOut")
                .header("Content-Type", "application/json")
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

    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public HttpEntity<String> checkIn() {
        int precision = new SecureRandom().nextInt(30) + 1;
        int distance = new SecureRandom().nextInt(250) + 1;
        String address = randomAddress() + " (精确到" + precision + ".00米) 距离考勤点" + distance + "米";

        String result = HttpRequest.post("http://service.qdtsoft.com/AttendanceService/AttendanceCheckIn")
                .header("Content-Type", "application/json")
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

    private String randomAddress() {
        return addressPool.get(new SecureRandom().nextInt(addressPool.size()));
    }
}
