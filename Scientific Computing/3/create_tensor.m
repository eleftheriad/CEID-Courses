function [G] = create_tensor(A, k)
% Author : Π. ΕΛΕΥΘΕΡΙΑΔΗΣ, ΑΜ 1041741 , Date : 16/01/2021
    tic;
    [rows,cols] = size(A);
    %initialize so that we can pass values to G
    G = tenzeros(rows,cols,k);
    temp = A;
    %G(:,:,1)=A, G(:,:,2)=A^2, G(:,:,k)=A^k
    for i=1:k       
        G(:,:,i) = temp;        
        temp = temp * A;
    end
    toc;
end

