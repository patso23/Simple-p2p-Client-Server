import random
import sys
done = int(sys.argv[1])
filename = sys.argv[2]
print done
    
# Create a file object:
# in "write" mode
FILE = open(filename,"w")

for i in range(done):
	rand = random.randint(65,90)
	#print rand
	#print count
	FILE.write(chr(rand))
	
    
    
FILE.close()
