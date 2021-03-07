#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>



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

        int pipe_flag = 0;    
        int number_of_pipes = 0;
        for (i=0;i<args_size;i++){
           // printf("%s\n",args[i] );

            if (*args[i] == '|'){
                pipe_flag = 1;
                number_of_pipes++;
               // printf("%d\n", pipe_flag);
            }
        }
        
        
        
            pid_t pid;
            pid_t wpid;
            int status;

        if (pipe_flag == 1){

            int i, j, k;
            char* commands_array[number_of_pipes + 1][args_size];

        

            i = 0;
            j = 0;
            k = 0;
            for (int i = 0; i < args_size; i++)
            {
                commands_array[j][k] = args[i];
                if(*args[i] == '|'){
                    commands_array[j][k] = NULL;
                    j++;
                    k=0;
                }else{
                    k++;
                }    
                
            }
            commands_array[j][k] = NULL;
           
            int p;
               
            pid = fork();


            if (pid < 0){
                perror("fork error");
            }
            else if (pid == 0){
                //child

                
                //printf("%d\n",number_of_pipes );
                for ( p = 0; p < number_of_pipes ; p++){
                    int fd[2];
                    pipe(fd);
                
                    if (!fork()){
                        dup2(fd[1],1);
                        close(fd[0]);
                        execvp(commands_array[p][0],commands_array[p]);
                        perror("exec");
                    }
                    
                        dup2(fd[0],0);
                        close(fd[1]);
                 
                }
                execvp(commands_array[p][0],commands_array[p]);
                perror("exec");
            }

                

                
            
            else{
                               
                wpid = wait(&status);
               
            }



        }else{
                

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