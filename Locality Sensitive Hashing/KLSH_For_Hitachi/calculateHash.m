function hash_position = calculateHash(BigHash)
	width = 1;
    length_1 = length(BigHash);
    b = 1:length_1;
    sum = BigHash(1,:) * b';
    rand_var = int64(width * rand(1,1));
	hash_position =  int64( sum ) / (width)  ;
