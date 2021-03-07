function [mydict, integer_symbols]= myhuffmandict(symbols, prob)
    %get number of symbols
    [~, num_of_symbols] = size(symbols); 
    [~, num_of_probs] = size(prob); 
    if num_of_symbols ~= num_of_probs
        error("Number of symbols must equal to number of probabilities");
    end
    if sum(prob)>1.0001 || sum(prob)< 0.9999
        error("probabilities must sum to 1");
    end
    k=1;
    integer_symbols(1,num_of_symbols)=0;
    char_positions(1,num_of_symbols)=0;
    if iscell(symbols)     
        for i=1:num_of_symbols
            if ischar(symbols{1,i})
                integer_symbols(1,i) = double(symbols{1,i});
                char_positions(1,k) = i;
                k=k+1;
            else
                integer_symbols(1,i)= symbols{1,i};
            end
        end
    else
        for i=1:num_of_symbols
            integer_symbols(1,i)= symbols(1,i);
        end
    end    
    symbols = integer_symbols;
    %Initialize matrix A with an irrelevant number eg 5
    %The matrix will have the final huffman code for each symbol
    %Row 1 of A represents the first symbol
    %Second row represents second symbol etc...
    %Columns of A represent the time of each addition of the probabilities   

% H U F F M A N
    A(1:num_of_symbols,1:num_of_symbols) = 5;
    C{1,1}=0;  
    for i = 1:num_of_symbols-1
        
        if prob >= 1
            break;
        end
        %sort probabilities and symbols
        [prob, sortIdx] = sort(prob, 'descend');
        symbols = symbols(sortIdx);
        [~, last_symbol] = size(symbols);      
        %add the last two probabilities and assign to second last symbol
        disp(last_symbol-1);
        prob(last_symbol-1) = prob(last_symbol-1) + prob(last_symbol);       
        %keep track of symbols position
        symid1 = symbols(last_symbol-1);
        idx1 = find(integer_symbols == symid1);
        symid2 = symbols(last_symbol);
        idx2 = find(integer_symbols == symid2);        
        %0 goes to second last, 1 goes to last probability
        A(idx1,i) = 0;%0
        A(idx2,i) = 1;%1
        flag1=0;
        flag2=0;
       [rowsize, ~] = size(C);     
       %Symbols that were added together are concatanated in arrays in the
       %cell. Each row of C cell is an array of symbols that have
       %been added together in the past. So whatever happens to one of
       %them, must happen to the whole array
       for k=1:rowsize         
           if ~isempty(find(C{k,1} == idx1, 1))
               x = k;
               flag1=1;
           end
           if ~isempty(find(C{k,1} == idx2, 1))
               y = k;
               flag2=1;
           end
       end
        %If a symbol gets a 0 or 1, make sure that all others in
        %the same row as that symbol get the same value too        
        if flag1 
           temp = C{x,1};
           [~, column_size] = size(temp);
            for j = 1:column_size
                var=temp(1,j);                
                A(var, i) = A(idx1,i);                              
            end            
            C{x,1} = horzcat(temp, idx2);            
        end        
        if flag2
           temp = C{y,1};
           [~, column_size] = size(temp);
            for j = 1:column_size
                var=temp(1,j);
                A(var, i) = A(idx2,i);                
            end            
             C{y,1} = horzcat(temp, idx1);           
        end
        %Starting condition. First addition
        if i==1
            C{i,1} =[idx1 idx2];           
        end
        %if symbols are not already in the cell. Create new row in the
        %cell to place them
        if i>1 && ~flag1 && ~flag2
            C{rowsize+1,1} = [idx1 idx2];
        end
        %if both symbols are already in the cell, merge them  
        if flag1 && flag2
            temp = horzcat(C{x,1}, C{y,1});
            C{x,1} = unique(temp);
            C{y,1}=[];           
        end               
        %delete last symbol and its prob
        symbols(last_symbol) = [];
        prob(last_symbol) = [];
    end
    disp(A);
    disp(C);
    %Transform rows of A to 1d-arrays. Get rid of the 5s, and reverse the vectors
    %Create a cell array with symbols and vectors
    
    %initialize cell array
    mydict{num_of_symbols,2}=0;
    [rows, ~]= size(A);
    for i = 1:rows
        temp = A(i,:);
        temp(temp==5)=[];
        temp = fliplr(temp);        
        if ~isempty(find(i == char_positions(1,:), 1))
            mydict{i,1} = char(integer_symbols(1,i));
        else
            mydict{i,1} = integer_symbols(1,i);
        end
        mydict{i,2} = temp;
    end    
end