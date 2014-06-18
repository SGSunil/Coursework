%{
d= java.util.Hashtable

list = java.util.ArrayList;
list.add(10); %list.add(20);
d.put(1, list)
elements  = d.get(1).toArray()
elements(1,1)
if length(d.get(1)) == 0 
	disp('yes')
	list = java.util.ArrayList;
	list.add(10); list.add(20);
	d.put(1, list)
	d.get(1).add(30);
	size(d.get(1))
	d.put(2, list)
	size(d)
end
%}

v1 = [10, 8, 8, 10, 2, 8];
v2 = [7, 4, 4, 7, 1, 1];

distance = calculateEucledianDist(v1, v2)