tic;
% Author : Π. ΕΛΕΥΘΕΡΙΑΔΗΣ, ΑΜ 1041741 , Date : 24/02/2021
n=500; A = randn(n); xsol = rand(n,1); b= A*xsol;
[X,FLAG,RELRES,ITER,RESVEC] = gmres(A,b,[],[],n);
toc;