function [xq, centers, D, sqnr] = Lloyd_Max(x, N, min_value, max_value)
    %number of regions
    num_of_regions = 2^N;
    %distance of region like my_quantizer
    range = (max_value - min_value) / (num_of_regions);
    %last element has lowest value
    centers(num_of_regions) = min_value + range/2;
    %assign values
    for i=2:num_of_regions-1
        centers(num_of_regions-i+1) = min_value + range/2 + (i-1)*range;
    end
    %first element has highest value
    centers(1) = max_value - range/2;
    %k counts iterations of while loop
    k=1;
    %initialize T
    T = zeros(1, num_of_regions+1);
    %initialize xq
    xq = zeros(1, length(x));
    while true 
        %first T element has the maxvalue
        T(1) = max_value; 
        %assign according to LloydMax algorithm
        for i=2:num_of_regions
            T(i) = (centers(i)+centers(i-1))/2;

        end
        %last T element has the minvalue
        T(num_of_regions+1) = min_value;

        for i=1:length(x)
            %assign values to xq according to which region x falls in
            if x(i)>= max_value
                xq(i) = 1;
            elseif x(i) <= min_value
                xq(i) = num_of_regions;
            else
                for j=1:num_of_regions
                    if x(i)<=T(j) && x(i)>T(j+1)
                        xq(i) = j;
                    end
                end
            end

        end
        %all xq values must be assigned
        if find(xq==0)
            error('0 found');
        end
        %initialize sum of x values
        sum = zeros(num_of_regions,1);
        %initialize number of x values
        counter = zeros(num_of_regions,1);
        for n=1:num_of_regions
            %add and count values that are in the same region
            for i=1:length(x)               
                if (x(i) <= T(n) && x(i) > T(n+1))
                    sum(n) = sum(n) + x(i);
                    counter(n) = counter(n) + 1;

                elseif ((x(i) > T(n)) && (n == 1))
                    sum(n) = sum(n) + T(n);
                    counter(n) = counter(n) + 1;

                elseif ((x(i) < T(n+1)) && (n == num_of_regions))
                    sum(n) = sum(n) + T(n+1);
                    counter(n) = counter(n) + 1;
                end
            end
            %divide sum by the number of elements to get weighted average
            if (counter(n) > 0)
                centers(n) = sum(n)/counter(n);
            end
        end
        %distortion
        dist=0;
        %Px
        px=0;
        %calculate distortion and Px
        for i=1:length(xq)         
            dist = dist + (x(i)-centers(xq(i))) ^2;
            px = px + x(i)^2;
        end 
        %get kth sqnr
        sqnr(k)= 10*log10(px/dist);
        %get kth average distortion
        D(k) = dist/length(xq);
        
        %break condition
        if k>1
            if(abs(D(k)-D(k-1)) < 10^(-6))
                break;
            end
        end
        k=k+1;
    end
    %frequency of every region
    frequency = zeros(num_of_regions,1);
    %count every value in the same region
    for i=1:num_of_regions
        for k=1:length(xq)
            if (i == xq(k))
                frequency(i) = frequency(i) + 1;
            end
        end
    end
    %calculate probability
    p = frequency./length(xq);
    disp(p);
end