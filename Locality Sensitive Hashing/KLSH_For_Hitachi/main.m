%Code adapted from Brian Kulis & Kristen Grauman for KLSH

t1 = cputime;
% Parameters for GIST Descriptor calculations
clear param
param.imageSize = [256 256]; % it works also with non-square images
param.orientationsPerScale = [8 8 8 8 8];
param.numberBlocks = 4;
param.fc_prefilt = 4;

%Read all the data images from the data folder.
files = dir('data/*.jpg');
total_files = length(files)
dimension_of_gist_vector = 640;
X = zeros(total_files,  dimension_of_gist_vector);
cell_files = {};

for i = 1:total_files
    f = files(i);
    [img, map] = imread(strcat('data/', f.name));
    [gist, param] = LMgist(img, '', param);
    X(i,:) = gist;
    %Store file names are they are read in cells
    cell_files{i} = f.name;
end

%Read the Query image (all the images that is placed in the folder)
files = dir('*.jpg');
total_files = length(files);
Ktest_in = zeros(total_files,  dimension_of_gist_vector);
cell_files_input = {};

for i = 1:total_files
    f = files(i);
    [img, map] = imread(f.name);
    %f.name
    [gist, param] = LMgist(img, '', param);
    Ktest_in(i,:) = gist;
    cell_files_input{i} = f.name;
end

%Put the query image in the data images to check the results.
cell_files_final = [cell_files_input, cell_files];
t2 = cputime;
sprintf('time spent in reading data images and query images: %f', (t2 - t1))

%Data processing - forming KLSH Matrix
t1 = cputime;
X = [Ktest_in; X];
[n,d] = size(X)
%form RBF (Radial basis function) over the data:
nms = sum(X'.^2);
K = exp(-nms'*ones(1,n) -ones(n,1)*nms + 2*X*X');
%size(K)

Ktrain = K;
%size(Ktrain)
Ktest = Ktrain(1,:);

L = 100;
L_Hash_Tables = java.util.Hashtable;
L_Hash_Tables_Compact = java.util.Hashtable;
L_Hash_Weights = java.util.Hashtable;

disp('Doing Big hash table creation...');
for i = 1:L, 
    %form KLSH hash table with b = 300 bits, and t = 30 samples
    [H W] = createHashTable(Ktrain, 300, 30);
    L_Hash_Tables.put(i, H);
    L_Hash_Weights.put(i, W);
end

disp('completed Big hash table creation...');
%size(W)
%Compute query image hash
%H_query = (Ktest*W) > 0;
t2 = cputime;
sprintf('Time spent in Data processing including hash table creation: %f', (t2 - t1))
    
%Design: maintain a ArrayList for every hash position because
%there may be more than one similar items hashed to a single hash position.
%Hash function is doing sum of Big Hash received from KLSH createHashTable
%and calculating small hash that is used for actual hashing.
%Perform L iteration to avoid unlucky escape from hashing.
disp('Creating hash table');
t1 = cputime;
hash_table = java.util.Hashtable;
CH = [];
[r, c] = size(H);
for j = 1:L,
    H = L_Hash_Tables.get(j);
    W = L_Hash_Weights.get(j);
    %Compacts the b bits hashes into 8 bytes group
    CH = compactbit(H);
    L_Hash_Tables_Compact.put(j, CH);
    
    for i=1:r,
        %(sum(CH(i,:)))
        hash_position = calculateHash(H(i,:));
        %If this is first time hash position is touched, 
        %We must check if data is already present or not, then
        %we must add some data 
        if length(hash_table.get(hash_position)) == 0
            list = java.util.ArrayList;
            list.add(i);
            hash_table.put(hash_position, list);
        else
            hash_table.get(hash_position).add(i);
        end
    end
end
t2 = cputime;
sprintf('Time spent in Hash table creation: %f', (t2 - t1))
sprintf('hash table size: %d', length(hash_table))


disp('Displaying Query hashes')
%(sum(CH_query(1,:)))
%query_hash_pos = calculateHash(CH_query(1,:));

%Query hash location in hash table
%Design: locate nearby 5 or 10 hash bins to get all the items (assumption
%is nearby bins may contain similar items to the query items.)
CH_query = [];
t1 = cputime;
index_array = [];
for i = 1:L,
    W = L_Hash_Weights.get(i); 
    H_query = (Ktest*W) > 0;
    CH_query = compactbit(H_query);
    
   query_hash_pos = calculateHash(H_query(1,:));
   h = query_hash_pos;
   
   if length(hash_table.get(h)) > 0
       array_size = size(hash_table.get(h));
       cells = hash_table.get(h).toArray().cell;
       index_array = [index_array, cells{1:array_size}] ;   
   end
end

%index_array
disp('Displaying size of local items to the query items');
%index_array
size(index_array)

disp('Displaying AGAIN size of local items to the query items');
index_array = unique(index_array);
size(index_array)

hash_array = [];

for i = 1:length(index_array),
   hash_array = [ hash_array; CH(index_array(i),:) ];
end

disp('Calculate the hamming distance of query hash and local hashes');
Dist = hammingDist(CH_query, hash_array);
[v, ind] = sort(Dist(1,:),'ascend');

cell_files_final_hash = {};
length_index_array = length(index_array);
if length_index_array > 5,
    length_index_array = 5;
end
for i = 1:length_index_array,
    cell_files_final_hash{i} = cell_files_final{index_array(ind(i))};
end

cell_files_final_hash
t2 = cputime;
sprintf('Time spent in locating the query item: %f', (t2 - t1))

t1 = cputime;
[r, c] = size(X);
eucledianDistances = [];
for index = 1:r,
    eucledianDistances = [eucledianDistances, calculateEucledianDist(Ktest_in(1,:), X(index, :))];
end

[data, indexes] = sort(eucledianDistances);
cell_files_final_linear = {};
for i = 1:length_index_array,
    cell_files_final_linear{i} = cell_files_final{indexes(i)};
end

cell_files_final_linear
t2 = cputime;
sprintf('Time spent in locating the query item in Linear fashion: %f', (t2 -t1))

accuracy_percentage = ( int64(length(intersect(unique(cell_files_final_hash), unique(cell_files_final_linear))))*100 )/length(unique(cell_files_final_linear));
sprintf('Accuracy is: %f', accuracy_percentage)

%write the accuracy data in a file
file_name = 'Accuracy.csv';
mode = 'a+';
fid = fopen(file_name, mode);
fprintf(fid, '%d\n', accuracy_percentage);
fclose(fid);
