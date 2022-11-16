//Tao Hu 23655842
//output image preview
//https://github.com/Talen-520/codingNote/blob/main/README.md
#include <stdio.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/wait.h>
#include <mqueue.h>
#include <time.h>
#include <string.h>
#include <limits.h>
#include <sys/utsname.h>
#include <errno.h>

#define MAX_LENGTH 1000

const int BUFFER_SIZE = 256;
const int MSG_CAPACITY = 100;

int main(int argc, const char *argv[])
{

	struct utsname buffer;
	char c;
	int NEWLINE_COUNT = 0;
	int CHAR_COUNT = 0;
	int word_count = 0;
	int MAX_LINE = 0;
	char chunk[MSG_CAPACITY];

	struct mq_attr attr;
	attr.mq_maxmsg = MSG_CAPACITY;
	attr.mq_msgsize = BUFFER_SIZE;

	write(STDOUT_FILENO, "Parent: Before mq_open...\n", 26);

	// Initialize the message queue.
	mqd_t mqd = mq_open(argv[3], O_CREAT | O_RDWR, 0644, NULL);
	if (mqd == (mqd_t)-1)
	{
		printf("Parent:  mq_open error");
		mq_unlink(argv[3]);
		return 2;
	}

	write(STDOUT_FILENO, "Parent: After mq_open...\n", 25);
	char CURRENT_WORKING_DIRECTORY[256];
	//open file 3 time used to calculate Line# word# Max_Line and characters#
	FILE *fp = fopen("Project1Input.txt", "r");
	if (fp == NULL)
	{
		printf("Unable to open file!");
		return 0;
	}
	char w;
	FILE *fp2 = fopen("Project1Input.txt", "r");
	FILE *fp3;
	char buff[MAX_LENGTH];
	fp3 = fopen("Project1Input.txt", "r");
	// List the message queue properties.
	struct mq_attr ret_attr;
	if (mq_getattr(mqd, &ret_attr) == -1)
	{
		printf("Parent: mq_getattr error");
		return 2;
	}

	printf("Parent: Maximum # of messages on queue: %ld\n", ret_attr.mq_maxmsg);
	printf("Parent: Maximum message size: %ld\n", ret_attr.mq_msgsize);
	printf("Parent: # of messages currently on queue: %ld\n\n", ret_attr.mq_curmsgs);

	// Parent is producer and child is consumer.
	pid_t pid = fork();
	int pid_t1 = getpid();
	int pid_t2 = getppid();
	if (pid < 0)
	{
		fprintf(stderr, "Parent: Fork Failed");
		return 2;
	}

	else if (pid == 0)
	{ // Child reads from message queue.

		char buf[BUFFER_SIZE];
		unsigned int prio;
		struct timespec timeout = {0, 0};

		ssize_t numRead = mq_timedreceive(mqd, buf, ret_attr.mq_msgsize, &prio, &timeout);

		if (numRead == -1)
		{
			printf("message size: %ld\n", ret_attr.mq_msgsize);
			perror("mq_timedreceive"); // this line used for debug
			printf("Child: mq_read error");
			return 5;
		}

		write(STDOUT_FILENO, "Child: Message 1 content is ", strlen(chunk));
		write(STDOUT_FILENO, buf, (size_t)numRead);
				// calculate LINES
		while (fgets(chunk, sizeof(chunk), fp) != NULL)
		{
			if (strlen(chunk) > MAX_LINE)
			{
				// get maxinum line
				MAX_LINE = strlen(chunk);
			}
			NEWLINE_COUNT++;
		}
		if (fp2)
		{
			// calculate Characters
			while (w = fgetc(fp2) != EOF)
			{

				CHAR_COUNT++;
			}
			// printf("%c",w); print one by one 
		}
		// calculate Words
		while (fscanf(fp3, "%999s", buff) == 1)
		{
			// printf("%s\n", buff); test for printing single word only
			strcpy(chunk, buff);

			word_count++;
		}
		// project 1 desired output
		printf("Project1Input:\t newline count is:%d\n", NEWLINE_COUNT);
		printf("Project1Input:\t character count is:%d\n", CHAR_COUNT);
		printf("Project1Input:\t word count is:%d\n", word_count);
		printf("Project1Input:\t maximum line length:%d\n", MAX_LINE);
		
		// Project 2 desired output
		printf("Process ID is:\t %d\n", pid_t1);//print child pid
		printf(" Parent process ID is:\t %d\n", pid_t2);//print parents ppid

		char cwd[PATH_MAX];
		if (getcwd(cwd, sizeof(cwd)) != NULL)
		{
			printf("Process current working directory is:\t %s\n", cwd);
		}
		else
		{
			perror("getcwd() error");//debugging 
			return 1;
		}

		errno = 0;
		if (uname(&buffer) < 0)
		{
			perror("uname");
			return 0;
		}

		printf("OS name = %s\n", buffer.sysname);
		printf("Hostname is:\t%s\n", buffer.nodename);
		printf("OS release is:\t %s\n", buffer.release);
		printf("OS version is:\t = %s\n", buffer.version);
		// printf("machine     = %s\n", buffer.machine);

#ifdef _GNU_SOURCE
		printf("domain name = %s\n", buffer.domainname);
#endif
		/*
		  char hostname[HOST_NAME_MAX + 1];
		  gethostname(hostname, HOST_NAME_MAX + 1);

		  printf("Hostname is:\t %s \n", hostname);*/
		// Close and release the queue.
		if (mq_close(mqd) == -1)
		{
			printf("Child: close error");
			mq_unlink(argv[3]);
			return 3;
		}

		write(STDOUT_FILENO, "Child: Terminating.\n", 15);

		return 0;
	} // End of Child branch

	else
	{ // Parent writes to the message queue.

		struct timespec timeout = {0, 0};
		// Write the 1st message.

		if (mq_timedsend(mqd, chunk, strlen(chunk), 1, &timeout) == -1)
		{
			printf("Parent: mq_send error");
			return 4;
		}
		printf("Parents message sent\n");

		// Release the message queue.
		if (mq_close(mqd) == -1)
		{
			printf("Parent: close error");
			mq_unlink(argv[3]);
			return 3;
		}

		// Parent checks for child termination.
		wait(NULL);
		write(STDOUT_FILENO, "Parent: Terminating.\n", 16);
		return 0;
	} // End of parent branch
	//close files
	fclose(fp);
	fclose(fp2);
	fclose(fp3);

} // End of main()
