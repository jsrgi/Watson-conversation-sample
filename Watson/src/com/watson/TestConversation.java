package com.watson;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class TestConversation {

public static void main(String[] args) {
BufferedReader br = null;
MessageResponse response = null;
Map context = new HashMap();
try {
br = new BufferedReader(new InputStreamReader(System.in));
TimeZone timeZone = TimeZone.getDefault();
System.out.println("Enter your Name: ");
String userName = br.readLine();
// Add userName to context to be used by Conversation.
context.put("userName", userName);
context.put("currentTime", Calendar.getInstance(timeZone).getTime().toString());
context.put("timezone", timeZone.getID());

while (true) {
System.out.print(userName + " : ");
String input = br.readLine();
response = conversationAPI(input, context);
System.out.println("Watson Response: " + response.getText().get(0));
context = response.getContext();
System.out.println("———–");
}
} catch (IOException e) {
e.printStackTrace();
}
}
public static MessageResponse conversationAPI(String input, Map context) {
ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
// Credentials of Workspace of Conversation
service.setUsernameAndPassword("USERRNAME", "PASSWORD");
MessageRequest newMessage = new MessageRequest.Builder().inputText(input).context(context).build();
// Workspace ID of Conversation current workspace
String workspaceId = "WORKSPACE ID";
MessageResponse response = service.message(workspaceId, newMessage).execute();
return response;
}
}