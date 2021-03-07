function signal = myhuffmandeco(code, dict)
    [~, code_cols] = size(code);
    [dict_rows,~] = size(dict);
    temp=[];
    signal={};
    k=1;
    for i=1:code_cols
        temp = horzcat(temp, code(1,i));
        for j=1:dict_rows
            [~,temp_cols]=size(temp);
            [~,dict_cols]=size(dict{j,2});        
            if temp_cols == dict_cols
                if temp==dict{j,2}
                    signal{1,k} = dict{j,1};
                    k=k+1;
                    temp=[];
                end
            end
        end
    end

end