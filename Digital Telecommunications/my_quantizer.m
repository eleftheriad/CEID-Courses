function [xq, centers] = my_quantizer(x, N, min_value, max_value)
    %number of regions
    num_of_regions = 2^N;
    %distance between two centers
    range = (max_value - min_value) / (num_of_regions);
    %frequency for distortion overload
    frequency=0;
    %represtative values of x
    xq = zeros(length(x),1);
    for i=1:length(x)
        %if x is less than min value
        if x(i)<=min_value
            xq(i) = num_of_regions;
            frequency = frequency + 1;
        %if x is bigger than max value
        elseif x(i) >= max_value
            xq(i) = 1;
            frequency = frequency + 1;
        else
            for j=2:num_of_regions+1
                %find the region of x and give xq the appropriate number
                if x(i)>= (min_value + (j-2) * range) && x(i)<= (min_value + (j-1)* range)
                    xq(i) = num_of_regions + 1 - (j-1);
                end
            end
        end
    end
    
    
    %last element has lowest value
    centers(num_of_regions) = min_value + range/2;
    %assign values
    for i=2:num_of_regions-1
        centers(num_of_regions-i+1) = min_value + range/2 + (i-1)*range;
    end
    %first element has highest value
    centers(1) = max_value - range/2;
    %Px
    px=0;
    %distortion
    dist=0;
    
    for i=1:length(xq) 
        %calculate distortion & px
        dist = dist + ((x(i)-centers(xq(i))) ^2);
        px = px + x(i)^2;
    end
    disp('SQNR')
    disp(10*(log10(px/dist)));
    disp('Distortion:');
    disp(dist);
    disp('Px:');
    disp(px);
    disp('Frequencies');
    frequency = frequency/length(x);
    disp(frequency);

     
end

