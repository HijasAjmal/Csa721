// C Program for Message Queue (Writer Process)
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <string.h>
 
// structure for message queue
struct mesg_buffer {
    long mesg_type;
    char mesg_text[100];
} message,message1;
 
int main()
{
    int msgid;
 
 	// msgget creates a message queue
    // and returns identifier
    msgid = msgget(1230, 0666 | IPC_CREAT);
    message.mesg_type = 1;
 
    printf("Write Data : ");
    gets(message.mesg_text);
 
    // msgsnd to send message
    msgsnd(msgid, &message, sizeof(message), 0);
 
    // display the message
    printf("Data send is : %s \n", message.mesg_text);
 	
 	msgid = msgget(1240, 0666 | IPC_CREAT);
 	msgrcv(msgid,&message1, sizeof(message1),1,0);
 	printf("%s\n",message.mesg_text);
 	printf("%s\n",message1.mesg_text);
 	if(strcmp(message1.mesg_text, message.mesg_text)==0)
 		printf("String is palindrome\n");
 	else
 		printf("String is Not palindrome\n");
    return 0;
}