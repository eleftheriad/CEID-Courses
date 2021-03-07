function [counter] = find_count(G, k, start, finish)
% Author : Π. ΕΛΕΥΘΕΡΙΑΔΗΣ, ΑΜ 1041741 , Date : 16/01/2021
    tic;
    counter=0;
   for i=1:k
       counter = counter + G(start, finish,i);
   end
    
    toc;

end