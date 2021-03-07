#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char *argv[])
{
	printf("$ ");

	while(1){
        	char p[20];
        	scanf("%s", p);
		if(strcmp(p,"exit") == 0){
		exit(0);
		}
		char *args[] = {p,NULL};

		pid_t pid;
		pid_t wpid;
		int status;


		pid = fork();

		if (pid < 0){
			perror("fork error");
		}
		else if (pid == 0){
		//child
			execvp(args[0],args);
		}
		else{
		//parent
			wpid = wait(&status);

	        printf("$ ");

		}

	}
		return 0;
}
