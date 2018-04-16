// C Program for Message Queue (Reader Process)
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <string.h>
 
// structure for message queue
struct mesg_buffer {
    long mesg_type;
    int input_number;
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
    printf("Data Received is : %d \n", 
                    message.input_number);
 
 	msgid = msgget(1240, 0666 | IPC_CREAT);
    j = message.input_number;
 	if(j%2 == 0)
        message1.input_number = 1;
 	msgsnd(msgid,&message1, sizeof(message1),0);
    // to destroy the message queue
    msgctl(msgid, IPC_RMID, NULL);
 
    return 0;
}