// C Program for Message Queue (Reader Process)
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <string.h>
 
// structure for message queue
struct mesg_buffer {
    long mesg_type;
    char mesg_text[100];
} message, message1;
 
int main()
{
    int msgid, length, i, j;
 
    // msgget creates a message queue
    // and returns identifier
    msgid = msgget(1230, 0666 | IPC_CREAT);
  	message1.mesg_type = 1;

    // msgrcv to receive message
    msgrcv(msgid, &message, sizeof(message), 1, 0);
 
    // display the message
    printf("Data Received is : %s \n", 
                    message.mesg_text);
 
 	msgid = msgget(1240, 0666 | IPC_CREAT);
 	length = strlen(message.mesg_text);
 	j = length;
 		j--;
 	for(i = 0; i<length;i++){
 			message1.mesg_text[i] = message.mesg_text[j];
 			j--;
 	}
 	msgsnd(msgid,&message1, sizeof(message1),0);
 	if(strcmp(message1.mesg_text, message.mesg_text)==0)
 		printf("String is palindrome\n");
 	else
 		printf("String is Not palindrome\n");
    // to destroy the message queue
    msgctl(msgid, IPC_RMID, NULL);
 
    return 0;
}