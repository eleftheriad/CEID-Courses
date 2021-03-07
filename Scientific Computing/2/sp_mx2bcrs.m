function [val,col_idx,row_blk]= sp_mx2bcrs(A,nb)
    % Author : Π. ΕΛΕΥΘΕΡΙΑΔΗΣ, ΑΜ 1041741 , Date : 12/01/2021
    
    [row_size, col_size]=size(A);
    k=1;%counts every block (row wise)
    n=1;%counts val and col_idx elements
    m=1;%counts row_blk elements
    prev_row=0;%condition used for row_blk
    row_count=0;%counts how many blocks are in the same row,(used for row_blk)
    blk_col=1;%counts block columns only
    b_row=1;%counts row columns only
    n_cols=1;%counts columns for val.
    row_blk=[];
    if mod(row_size,nb)~=0
        error('nb and size of A are not divisible');
    end
    for i=1:nb:row_size
        %condition for empty rows
        empty_row = true;
        for j=1:nb:col_size
            %take every nb size block
            temp = A(i:nb+i-1,j:nb+j-1);
            %if block contains non zero, we add it
            if ~isempty(find(temp)~=0)
                %row is not empty
                empty_row = false;
                %add the block to vector val
                val(1:nb, n_cols:n_cols+nb-1)=temp;
                %make val ready for next block
                n_cols=n_cols+nb;
                %get the block column
                col_idx(n)=blk_col;               
                n=n+1;
                %if we find another nonzero in the same row, increment the
                %counter
                if b_row == prev_row
                    row_count = row_count + 1;    
                    
                else %new value for row_blk, reset prev_row                  
                   row_count=row_count+1;
                   row_blk(m) = row_count;
                   prev_row = b_row;
                   m=m+1;
                end 
            end
            k=k+1;
            blk_col=blk_col+1;            
        end
        %condition for empty rows
        if empty_row
            %if first column(s) are empty
            if isempty(row_blk)
                row_blk(m)=0;
            else
            row_blk(m) = row_blk(m-1);           
            end
            m=m+1;
        end        
        blk_col=1;
        b_row= b_row+1;
    end
    %last value for row_blk
    row_blk(m)= row_count+1;
end

