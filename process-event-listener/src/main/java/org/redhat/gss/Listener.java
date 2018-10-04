package org.redhat.gss;


import org.jbpm.process.workitem.email.EmailWorkItemHandler;
import org.kie.api.event.process.*;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;

import java.util.HashMap;
import java.util.Map;


public class Listener implements ProcessEventListener {

	public void beforeProcessStarted(ProcessStartedEvent event) {
		// TODO Auto-generated method stub

	}

	public void afterProcessStarted(ProcessStartedEvent event) {
		System.out.print("Process Started from Process event listener demo"+event.getProcessInstance().getProcessId());
	}

	public void beforeProcessCompleted(ProcessCompletedEvent event) {
		// TODO Auto-generated method stub

	}

	public void afterProcessCompleted(ProcessCompletedEvent event) {
		// TODO Auto-generated method stub

	}

	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
		// TODO Auto-generated method stub

	}

	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		// TODO Auto-generated method stub

	}

	public void beforeNodeLeft(ProcessNodeLeftEvent event) {
		// TODO Auto-generated method stub

	}

	public void afterNodeLeft(ProcessNodeLeftEvent event) {
		// TODO Auto-generated method stub

	}

	public void beforeVariableChanged(ProcessVariableChangedEvent event) {
		// TODO Auto-generated method stub

	}

	public void afterVariableChanged(ProcessVariableChangedEvent event) {
		// TODO Auto-generated method stub

	}

	public void beforeSLAViolated(SLAViolatedEvent event) {
		System.out.print("SLA violated");

	}

	public void afterSLAViolated(SLAViolatedEvent event)  {


		KieSession ksession = event.getKieRuntime().getKieBase().newKieSession();
		Map<String,Object> slaViolationMap = new HashMap<String, Object>();
		slaViolationMap.put("escalationId","SLA Violated for process : "+ event.getProcessInstance().getProcessName() +"with instance Id"
				+event.getProcessInstance().getId() + "\n \n Please take timely action of the instance!!");

        ksession.getWorkItemManager().registerWorkItemHandler(
                "Email",
                new EmailWorkItemHandler(null,"-1",null,null,"true")
        );
		ProcessInstance processInstance
				= ksession.startProcess("Mortgage_Process.SLAHandlerWkflow",slaViolationMap);


		System.out.print("Process Started");

		;

	}




}