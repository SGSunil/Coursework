%Design: both vectors are of the same length.
%eucledian/L2 distance is calculated by formula = sqrt(sum(vector1(i) - vector2(i))^2)
%for every dimension i
function distance = calculateEucledianDist(vector1, vector2)
	[rows, cols] = size(vector1);
    sum = 0;
    for index = 1:cols,
        sum = sum + (vector1(1, index) - vector2(1, index))^2;
    end
    
    distance = sqrt(sum);
