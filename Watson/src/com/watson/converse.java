package com.watson;


import java.io.*;
import java.util.Scanner;

import com.ibm.watson.developer_cloud.conversation.v1.*;
import com.ibm.watson.developer_cloud.conversation.v1.model.*;



public class converse {

	public static void main(String args[])throws IOException{
		
		Scanner sc=new Scanner(System.in);
		 String workspaceId = "YOUR WORKSPACE ID";
	String str="open",response1;
	
	do {
		
		ConversationService service = new ConversationService("2017-02-03");
		 service.setUsernameAndPassword("YOUR SERVICE CREDENTIAL USERNAME", "PASSWORD");
		 System.out.println("Start chat");
		 str=sc.nextLine();
		 MessageRequest newMessage = new MessageRequest.Builder().inputText(str).build();
		 MessageResponse response = service.message(workspaceId, newMessage).execute();
		 response1=response.getText().toString();
		System.out.println(response1.replace("[","").replace("]",""));
		//System.out.println(response1);
	}while(!(str.contains("bye")));
	sc.close();
	 }
}