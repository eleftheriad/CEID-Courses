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
        int args_size = i;
        //printf("%d\n",args_size );

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
        

        int temp = 0;
        int pipe_flag = 0;    
        
        for (i=0;i<args_size;i++){
           // printf("%s\n",args[i] );

            if (*args[i] == '|'){
                temp = i;
                pipe_flag = 1;
               // printf("%d\n", pipe_flag);
            }
        }

       

        if (pipe_flag == 1){

            pid_t pid;
            pid_t wpid;
            int status;

            int sizeofA = temp + 1;
            int sizeofB = args_size - temp;
            char *A[sizeofA];
            char *B[sizeofB];

            

            for (int i = 0; i < sizeofA; i++){
               A[i] = args[i];

            }
            A[sizeofA-1] = NULL;
            
            int counter = 0;
            for (int i = 0; i < sizeofB ; i++){
               
                counter++;
                B[i] = args[temp + 1 + i];
                 
            }
            B[sizeofB-1] = NULL;

            /*for (i=0;i<1;i++){
                printf("%s\n",A[i] );
                printf("%s\n",B[i] );
            }*/

               
            pid = fork();


            if (pid < 0){
                perror("fork error");
            }
            else if (pid == 0){
                //child

                int fd[2];
                pipe(fd);
                
                if (!fork())
                {
                    dup2(fd[1],1);
                    execvp(A[0],A);
                    perror("exec");
                }

                dup2(fd[0], 0);
                close(fd[1]);
                execvp(B[0],B);
                perror("exec");

                

                
            }
            else{
                               
                wpid = wait(&status);
                
            }


        }else{
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
                
                }
        }        
        
        printf("$ ");
    

         }

    return 0; 
}