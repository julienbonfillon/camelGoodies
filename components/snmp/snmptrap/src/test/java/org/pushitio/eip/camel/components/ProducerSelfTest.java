package org.pushitio.eip.camel.components;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerSelfTest {
	
	public static void main(String[] args) throws Exception{
		CamelContext context = new DefaultCamelContext();
		try{
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					from("file://../testInput?move=processed&moveFailed=errors")
					.to("snmptrap:udp://192.168.56.2:162?version=V2c&agentOid=1.3.6.1.6.3.10.1.2.3&messageOid=1.3.6.1.6.3.10.1.2.1");
				}
			});
			
			context.start();
			
			String quit = "";
			BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
			while(!quit.equals("q")){
				System.out.println("Type q to stop the context");
				quit = buffer.readLine();
			}
			
			context.stop();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
}
