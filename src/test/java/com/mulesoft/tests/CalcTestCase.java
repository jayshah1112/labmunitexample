package com.mulesoft.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mule.DefaultMuleMessage;
//import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class CalcTestCase extends FunctionalTestCase {
	
	@Override
	protected String getConfigFile() {
			return "src/main/app/junit_info.xml";
	}
	
	@Test
	public void testSquareFlow() throws Exception{
		MuleClient muleClient=muleContext.getClient();
		
		DefaultMuleMessage  message= new DefaultMuleMessage(new Integer(5), muleContext);
		
		muleClient.dispatch("vm://squareq", message);
		System.out.println("Dispatched....");
		
		
		MuleMessage responseMessage=muleClient.request("vm://respq", 10000);
		
		Object payload=responseMessage.getPayload();
		
		assertEquals(-25, payload);
		
	/*	MuleMessage replyMessage=muleClient.send("vm://squareq", message);
		
		*/
		
	}

	
	@Test
	public void testNegateFlow() throws Exception{
		MuleClient muleClient=muleContext.getClient();
		
		DefaultMuleMessage  message= new DefaultMuleMessage(new Integer(5), muleContext);
		
		muleClient.dispatch("vm://negateq", message);
		System.out.println("Dispatched....");
		
		
		MuleMessage responseMessage=muleClient.request("vm://respq", 10000);
		
		Object payload=responseMessage.getPayload();
		
		assertEquals(-5, payload);
		
	/*	MuleMessage replyMessage=muleClient.send("vm://negateq", message);
		
		*/
		
	}
	
	
	
}
