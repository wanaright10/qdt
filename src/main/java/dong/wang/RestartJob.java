//package dong.wang;
//
//import org.apache.log4j.Logger;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//import static org.quartz.JobBuilder.newJob;
//import static org.quartz.TriggerBuilder.newTrigger;
//
///**
// * Created on 12/5/16.
// */
//@Component
//public class RestartJob implements Job {
//    private static final String TOKEN = "token tp4b850p8b5a2u2kddndu0cjdhqw96lvhsrc9k5w";
//    private RestTemplate restTemplate = new RestTemplate();
//    private SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//    private Scheduler scheduler;
//
//    private Logger logger = Logger.getLogger(RestartJob.class);
//
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        logger.info("============ 执行 重启任务 开始 ==================");
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", TOKEN);
//
//        ResponseEntity<Apps> responseEntity = restTemplate.exchange("https://openapi.daocloud.io/v1/apps",
//                HttpMethod.GET,
//                new HttpEntity<>(httpHeaders),
//                Apps.class);
//
//        String qdtId = responseEntity.getBody()
//                .getApp()
//                .stream()
//                .filter(appInfo -> appInfo.getName().equals("qdt"))
//                .findFirst()
//                .orElse(new AppInfo("c869c4fd-af1f-4bf4-846b-2692a939c31d", "qdt"))
//                .getId();
//
//        ResponseEntity<Void> voidResponseEntity = restTemplate.exchange("https://openapi.daocloud.io/v1/apps/{app_id}/actions/restart",
//                HttpMethod.POST,
//                new HttpEntity<>(httpHeaders),
//                Void.class,
//                qdtId);
//
//        voidResponseEntity.getStatusCode();// ...
//
//        try {
//            scheduler.shutdown();
//        } catch (Exception e) {
//        }
//
//        logger.info("============ 执行 重启任务 结束 ==================");
//    }
//
//    @PostConstruct
//    public void startJob() throws Exception {
//        logger.info("================== 开始注入 重启任务 =======================");
//        JobDetail job = newJob(RestartJob.class)
//                .withIdentity("job1", "group1")
//                .build();
//
//        Trigger trigger = newTrigger()
//                .withIdentity("trigger1", "group1")
//                .startAt(Date.from(LocalDateTime.now().plusHours(10).atZone(ZoneId.systemDefault()).toInstant()))
//                .build();
//
//        scheduler = schedulerFactory.getScheduler();
//        scheduler.scheduleJob(job, trigger);
//        scheduler.start();
//    }
//}
