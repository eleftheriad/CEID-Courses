tic;
% Author : Π. ΕΛΕΥΘΕΡΙΑΔΗΣ, ΑΜ 1041741 , Date : 22/01/2021
n=500; A =spdiags([1:n]',[0],n,n); xsol = ones(n,1); b = A*xsol;
[X,FLAG,REALRES,ITER,RESVEC] = pcg(A,b,[],4*n);
toc;