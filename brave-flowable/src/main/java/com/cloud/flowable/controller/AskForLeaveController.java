package com.cloud.flowable.controller;

import com.cloud.core.constant.FlowableConstants;
import com.cloud.core.result.Result;
import com.cloud.flowable.config.BraveDefaultProcessDiagramGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author admin
 * @version 1.0
 * @description: 请假
 * @date 2021/7/2 11:24
 */
@RestController
@RequiredArgsConstructor
public class AskForLeaveController {

    private final TaskService taskService;
    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final HistoryService historyService;
    private final ProcessEngine processEngine;

    /**
     * @param userId
     * @return com.cloud.core.result.Result<java.lang.String>
     * @Author yongchen
     * @Description 创建流程
     * @Date 14:55 2021/7/2
     **/
    @PostMapping("/startLeaveProcess")
    public Result<String> startLeaveProcess(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("leaveTask", userId);
        ProcessInstance leave = runtimeService.startProcessInstanceByKey("Leave", map);
        StringBuilder sb = new StringBuilder();
        sb.append("创建请假流程 processId：" + leave.getId());
        List<Task> list = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        for (Task task : list) {
            sb.append("任务taskId:" + task.getId());
        }
        return Result.success(sb.toString());
    }

    /**
     * @param userId
     * @return com.cloud.core.result.Result<java.util.List < org.flowable.task.api.Task>>
     * @Author yongchen
     * @Description 获取审批列表
     * @Date 14:55 2021/7/2
     **/
    @GetMapping("/getApproveList")
    public Result<String> getApproveList(String userId) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        return Result.success(list.toString());
    }

    /**
     * @param taskId
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 审批通过
     * @Date 15:00 2021/7/2
     **/
    @GetMapping("/approved")
    public Result<Boolean> approved(String taskId, String reason) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return Result.failed("流程不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("checkResult", FlowableConstants.APPROVE_TRUE);
        taskService.complete(taskId, map);
        return Result.success(true);
    }

    /**
     * @param taskId
     * @return com.cloud.core.result.Result<java.lang.Boolean>
     * @Author yongchen
     * @Description 审批不通过
     * @Date 15:02 2021/7/2
     **/
    @GetMapping("/approvalFailed")
    public Result<Boolean> approvalFailed(String taskId, String reason) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return Result.failed("流程不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("checkResult", FlowableConstants.APPROVE_FASLE);
        taskService.complete(taskId, map);
        return Result.success(true);
    }

    @GetMapping("/genProcessDiagram")
    public void genProcessDiagram(HttpServletRequest request, HttpServletResponse response, String processId) throws Exception {
        String psId = null;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        // 流程完成获取历史记录
        if (processInstance == null) {
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();
            psId = historicProcessInstance.getProcessDefinitionId();
        }else {
            psId = processInstance.getProcessDefinitionId();
        }

        //Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        // 使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        //String processInstanceId = task.getProcessInstanceId();
        //List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        /*for (Execution execution : executions) {
            List<String> activeActivityIds = runtimeService.getActiveActivityIds(execution.getId());
            activityIds.addAll(activeActivityIds);
        }*/
        //高亮线条
        List<String> flows = new ArrayList<>();
        // 获取活动的节点
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(processId).orderByHistoricActivityInstanceStartTime().asc().list();
        for (HistoricActivityInstance historicActivityInstance : list) {
            activityIds.add(historicActivityInstance.getActivityId());
            if (StringUtils.equals("sequenceFlow", historicActivityInstance.getActivityType())){
                flows.add(historicActivityInstance.getActivityId());
            }
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(psId);
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        //获取自定义图片生成器
        BraveDefaultProcessDiagramGenerator diagramGenerator = new BraveDefaultProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel,
                "png",
                activityIds,
                flows,
                engconf.getActivityFontName(),
                engconf.getLabelFontName(),
                engconf.getAnnotationFontName(),
                engconf.getClassLoader(),
                1.0,
                true);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = response.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
