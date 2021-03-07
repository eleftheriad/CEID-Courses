function [C, P] = band_stats(mxid, p)
% Author : Π. ΕΛΕΥΘΕΡΙΑΔΗΣ, ΑΜ 1041741 , Date : 12/02/2021

    %check if mxid is an integer or character
    if (isscalar(mxid)&& ~isfloat(mxid)) || ischar(mxid)
        %download matrix using ssget from SuiteSparse-5.8.1
        Problem = ssget(mxid);
        A = Problem.A;
    %if already a matrix
    elseif ismatrix(mxid)
        A = mxid;
    else
        error('You can only enter an integer, string or Matrix');
    end  
    
    %k == 1    
    %get the diagonal of A
    C = diag(diag(A, 0));
    %calculate stats
    rnnz = nnz(C)/nnz(A);
    rerr = norm(A - C, 'fro')/norm(A, 'fro');
    %place on array
    P(1,1) = rnnz;
    P(1,2) = rerr;
    
    %k > 1
    for i=1:p
        %k = 2*i + 1;  
        %each time add the next upper diagonal and lower diagonal to
        %existing matrix
        C = C + diag(diag(A, i),i) + diag(diag(A, -i),-i);   
        %calculate stats
        rnnz = nnz(C)/nnz(A);
        rerr = norm(A - C, 'fro')/norm(A, 'fro');
        %place on array
        P(i+1,1) = rnnz;
        P(i+1,2) = rerr;

    end