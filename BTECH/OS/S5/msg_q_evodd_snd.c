// C Program for Message Queue (Writer Process)
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <string.h>
 
// structure for message queue
struct mesg_buffer {
    long mesg_type;
    int input_number;
} message,message1;
 
int main()
{
    int j, msgid;
 
 	// msgget creates a message queue
    // and returns identifier
    msgid = msgget(1230, 0666 | IPC_CREAT);
    message.mesg_type = 1;
    message1.input_number = 0;
    printf("Enter the Number : ");
    scanf("%d", &message.input_number);
 
    // msgsnd to send message
    msgsnd(msgid, &message, sizeof(message), 0);
 
    // display the message
    printf("Data send is : %d \n", message.input_number);
 	
 	msgid = msgget(1240, 0666 | IPC_CREAT);
 	msgrcv(msgid,&message1, sizeof(message1),1,0);
    if(message1.input_number == 1)
        printf("Number is even\n");
    else
        printf("Number is odd\n");
 	
    return 0;
}