function [y]= spmv_bcrs(y,val,col_idx,row_blk,trans,x)
    % Author : Π. ΕΛΕΥΘΕΡΙΑΔΗΣ, ΑΜ 1041741 , Date : 14/01/2021
    tic;

    [nb,~] = size(val);
    
    %arraysize = length(row_blk)-1;
    %A = zeros(nb*arraysize,nb*arraysize);
   
    %x = ones(length(y),1);
    v = zeros(length(y),1);%A*x or A'*x
    %y = zeros(1000,1);
    starting_point=0;
    k=0;
    
    for i=1:length(row_blk)-1
        %if row is empty, skip it
        if (i~=1 && row_blk(i) == row_blk(i-1)) || row_blk(i)==0
            continue;
        end
        %get value of row_blk.
        temp = row_blk(i);       
        %look at first next row_blk that's bigger than temp
        for j=i+1:length(row_blk)
            if row_blk(j)>temp
                %that's how many blocks are in that row
                blocks_in_row = row_blk(j) - temp;
                break;
            end
        end
        
        for j=1+starting_point : blocks_in_row + starting_point
            col = col_idx(j);
           %A*x
           if trans == 0
               %=========================================================
               %--Alternative way by recreating matrix A:
               
               %A(nb*(i-1)+1:nb*i, nb*(col-1)+1:nb*col) = val(1:1*nb, nb*(j-1)+1:j*nb);
               %temp2 = A(nb*(i-1)+1:nb*i, nb*(col-1)+1:nb*col) * x(nb*(col-1)+1:nb*col, 1);
               %v(nb*(i-1)+1:nb*i, 1) = v(nb*(i-1)+1:nb*i, 1) + temp2;
               %=========================================================
               %Faster way:
               
               %instead of recreating A, I use the indexes that I would use
               %for A, but instead give it directly to vector v
               
               %The idea is to do the calculations block by block.
               %Every block in val is multiplied with the correct nb values
               %of x and then are added in the correct position in v
               
               temp2 = val(1:1*nb, nb*(j-1)+1:j*nb) * x(nb*(col-1)+1:nb*col, 1);
               v(nb*(i-1)+1:nb*i, 1) = v(nb*(i-1)+1:nb*i, 1) + temp2;
               
           %A'*x
           elseif trans == 1
               %=========================================================
               %Alternative way by recreating matrix A 
               %A(nb*(i-1)+1:nb*i, nb*(col-1)+1:nb*col) = val(1:1*nb, nb*(j-1)+1:j*nb);              
               %A=A';
               %temp2 = A(nb*(col-1)+1:nb*col, nb*(i-1)+1:nb*i) * x(nb*(i-1)+1:nb*i, 1);
               %v(nb*(col-1)+1:nb*col, 1) = v(nb*(col-1)+1:nb*col, 1) + temp2;
               %=========================================================
               
               %Same as before, but now the indexes are switched because
               %the matrix is transposed
               temp2 = val(1:1*nb, nb*(j-1)+1:j*nb)' * x(nb*(i-1)+1:nb*i, 1);
               v(nb*(col-1)+1:nb*col, 1) = v(nb*(col-1)+1:nb*col, 1) + temp2;
           end
            k=k+1;
        end        
        starting_point = k;              
    end
    %final addition
    y = y + v;
    
    
    toc;
end