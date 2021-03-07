#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>

int main(int args, int *argv[])
{
        printf("$ ");

        while(1){
        //get the line
		char *line= NULL;
        ssize_t bufsize = 0;
        getline(&line, &bufsize, stdin);
		line[strcspn(line, "\r\n")] = 0;
		//tokens
		char *token = strtok(line, " ");
		int i = 0;
		char *args[50];
		while(token != NULL) {
			args[i++] = token;
			token = strtok(NULL, " ");
		}
		args[i]=NULL;

		if(strcmp(args[0],"exit") == 0){
            exit(0);		
       	}
       	//cd
        if(strcmp(args[0],"cd") == 0){
        	if(*args[1]== '/'){
     			chdir(args[1]);
			}
			else{
				char buf[1024]; 
      		    getcwd(buf,sizeof(buf));
				strcat(buf,"/");
     			strcat(buf,args[1]);
        		chdir(buf);
			}        
        }
       
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
				else
                //parent
                   
                    wpid = wait(&status);
                        

                printf("$ ");


        }

        return 0;

}

