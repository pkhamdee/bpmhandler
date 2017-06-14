package com.test.jbpm.workitemhandlers;

import org.drools.core.process.instance.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorkItemHandler implements WorkItemHandler {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorkItemHandler.class);

    public void abortWorkItem(WorkItem wi, WorkItemManager wim) {
        logger.info("item aborted..");
    }

    public void executeWorkItem(WorkItem wi, WorkItemManager wim) {

        logger.info("ProcessInstanceId : " + wi.getProcessInstanceId());
        logger.info("Param 1" + wi.getParameter("param1"));
        logger.info("Hello World!");

        wim.completeWorkItem(wi.getId(), null);
    }

}
