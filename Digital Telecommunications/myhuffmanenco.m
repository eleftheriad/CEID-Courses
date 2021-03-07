function code = myhuffmanenco(sig, dict)
    [~,sig_cols] = size(sig);
    [dict_rows,~] = size(dict);
    
    code = [];
    if iscell(sig)
        for i = 1:sig_cols
            for j = 1:dict_rows          
                if sig{1,i}==dict{j,1}
                   code = horzcat(code, dict{j,2});
                end
            end
        end
    else
        for i = 1:sig_cols
            for j = 1:dict_rows          
                if sig(1,i)==dict{j,1}
                   code = horzcat(code, dict{j,2});
                end
            end
        end
    end
end