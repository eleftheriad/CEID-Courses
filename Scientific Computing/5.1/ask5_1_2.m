tic;
% Author : Π. ΕΛΕΥΘΕΡΙΑΔΗΣ, ΑΜ 1041741 , Date : 22/01/2021
n=500; A =spdiags([linspace(1,2,n/2)';linspace(1000,1001,n/2)'],[0],n,n); xsol = ones(n,1); b = A*xsol;
[X,FLAG,REALRES,ITER,RESVEC] = pcg(A,b,[],4*n);

toc;