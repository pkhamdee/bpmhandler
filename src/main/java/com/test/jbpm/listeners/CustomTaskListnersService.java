package com.test.jbpm.listeners;

import com.test.jms.JmsClient;
import org.kie.api.task.TaskEvent;
import org.kie.api.task.TaskLifeCycleEventListener;

import org.kie.api.runtime.manager.RuntimeManager;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomTaskListnersService  implements TaskLifeCycleEventListener {

    public RuntimeManager runtimeManager = null;

    private static final Logger logger = LoggerFactory.getLogger(CustomTaskListnersService.class);

    public CustomTaskListnersService(){}
    public CustomTaskListnersService(RuntimeManager runtimeManager)
    {
        this.runtimeManager = runtimeManager;
    }

    public void afterTaskActivatedEvent(TaskEvent taskEvent) {
        logger.info("afterTaskActivatedEvent");
    }

    public void afterTaskAddedEvent(TaskEvent taskStart) {
        logger.info(":::: afterTaskAddedEvent ::::::::::::");
        RuntimeEngine engine = runtimeManager.getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        WorkflowProcessInstance processInstance = (WorkflowProcessInstance) ksession.getProcessInstance(taskStart.getTask().getTaskData().getProcessInstanceId());


    }

    public void afterTaskClaimedEvent(TaskEvent arg0)  {
        logger.info("afterTaskClaimedEvent");

        //test exception here.
        //RuntimeEngine engine = null;
        //engine.getKieSession();
    }

    public void afterTaskCompletedEvent(TaskEvent taskEvent) {
        logger.info("afterTaskCompletedEvent");
    }

    public void afterTaskDelegatedEvent(TaskEvent arg0) {
        logger.info("afterTaskDelegatedEvent");
    }

    public void afterTaskExitedEvent(TaskEvent taskEvent) {
        logger.info("afterTaskExitedEvent");
    }

    public void afterTaskFailedEvent(TaskEvent taskEvent) {
        logger.info("afterTaskFailedEvent");
    }

    public void afterTaskForwardedEvent(TaskEvent arg0) {
        logger.info("afterTaskForwardedEvent");
    }

    public void afterTaskNominatedEvent(TaskEvent arg0) {
        logger.info("afterTaskNominatedEvent");
    }

    public void afterTaskReleasedEvent(TaskEvent arg0) {
        logger.info("afterTaskReleasedEvent");
    }

    public void afterTaskResumedEvent(TaskEvent arg0) {
        logger.info("afterTaskResumedEvent");
    }

    public void afterTaskSkippedEvent(TaskEvent arg0) {
        logger.info("afterTaskSkippedEvent");
    }

    public void afterTaskStartedEvent(TaskEvent arg0) {
        logger.info("afterTaskStartedEvent");
    }

    public void afterTaskStoppedEvent(TaskEvent arg0) {
        logger.info("afterTaskStoppedEvent");
    }

    public void afterTaskSuspendedEvent(TaskEvent arg0) {
        logger.info("afterTaskSuspendedEvent");
    }

    public void beforeTaskActivatedEvent(TaskEvent arg0) {
        logger.info("beforeTaskActivatedEvent");
    }

    public void beforeTaskAddedEvent(TaskEvent arg0) {
        logger.info("beforeTaskAddedEvent");
    }

    public void beforeTaskClaimedEvent(TaskEvent arg0) {
        logger.info("beforeTaskClaimedEvent");
    }

    public void beforeTaskCompletedEvent(TaskEvent taskCompleteEvent) {
        logger.info(":::: beforeTaskCompletedEvent ::::::::::::");
        RuntimeEngine engine = runtimeManager.getRuntimeEngine(null);

        //get ksession
        KieSession ksession = engine.getKieSession();

        logger.info("Task Id " + taskCompleteEvent.getTask().getId());
        logger.info("Task Name " + taskCompleteEvent.getTask().getName());
        logger.info("Task Description " + taskCompleteEvent.getTask().getDescription());

        logger.info("ActualOwner by: "+ taskCompleteEvent.getTask().getTaskData().getActualOwner().getId());
        WorkflowProcessInstance processInstance = (WorkflowProcessInstance) ksession.getProcessInstance(taskCompleteEvent.getTask().getTaskData().getProcessInstanceId());

        String jmsMessage =
                "<message>"+
                "<taskId>"+taskCompleteEvent.getTask().getId()+"</taskId>"+
                "<taskName>"+taskCompleteEvent.getTask().getName()+"</taskName>"+
                "<taskDescription>"+taskCompleteEvent.getTask().getDescription()+"</taskDescription>"+
                "<owner>"+taskCompleteEvent.getTask().getTaskData().getActualOwner().getId()+"</owner>"+
                "</message>";

        JmsClient.sendJms(jmsMessage);

    }

    public void beforeTaskDelegatedEvent(TaskEvent taskDelegatedEvent) {
        logger.info(":::: beforeTaskDelegatedEvent ::::::::::::");
        RuntimeEngine engine = runtimeManager.getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        //System.out.println("ActualOwner by: "+ taskDelegatedEvent.getTask().getTaskData().getActualOwner().getId());
        WorkflowProcessInstance processInstance = (WorkflowProcessInstance) ksession.getProcessInstance(taskDelegatedEvent.getTask().getTaskData().getProcessInstanceId());
    }

    public void beforeTaskExitedEvent(TaskEvent arg0) {
        logger.info("beforeTaskExitedEvent");
    }

    public void beforeTaskFailedEvent(TaskEvent arg0) {
        logger.info("beforeTaskFailedEvent");
    }

    public void beforeTaskForwardedEvent(TaskEvent arg0) {
        logger.info("beforeTaskForwardedEvent");
    }

    public void beforeTaskNominatedEvent(TaskEvent arg0) {
        logger.info("beforeTaskNominatedEvent");
    }

    public void beforeTaskReleasedEvent(TaskEvent arg0) {
        logger.info("beforeTaskReleasedEvent");
    }

    public void beforeTaskResumedEvent(TaskEvent arg0) {
        logger.info("beforeTaskResumedEvent");
    }

    public void beforeTaskSkippedEvent(TaskEvent arg0) {
        logger.info("beforeTaskSkippedEvent");
    }

    public void beforeTaskStartedEvent(TaskEvent startevent) {
        logger.info(":::: beforeTaskStartedEvent ::::::::::::");
        RuntimeEngine engine = runtimeManager.getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        logger.info("ActualOwner by: "+ startevent.getTask().getTaskData().getActualOwner().getId());
        WorkflowProcessInstance processInstance = (WorkflowProcessInstance) ksession.getProcessInstance(startevent.getTask().getTaskData().getProcessInstanceId());
    }

    public void beforeTaskStoppedEvent(TaskEvent arg0) {
        logger.info("beforeTaskStoppedEvent");
    }

    public void beforeTaskSuspendedEvent(TaskEvent arg0) {
        logger.info("beforeTaskSuspendedEvent");
    }
}