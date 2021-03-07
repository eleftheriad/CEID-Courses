import xml.etree.ElementTree as ET
import xml.dom.minidom
import time
import sys

x = list(map(str, input("Enter a multiple value: ").split()))
start = time.time()
base_tree = ET.parse('inverted_index.xml')
base_root = base_tree.getroot()

all_weights=[]
all_ids=[]

#ΓΙΑ ΚΑΘΕ ΛΕΞΗ ΣΤΟ INPUT
for y in x:
    #ΓΙΑ ΚΑΘΕ LEMMA STO XML
    for z in (base_root):
        att = (z.attrib)
        if att['name'] == y:
            #ΓΙΑ ΚΑΘΕ DOCUMENT
            for child in z:
                child_att = (child.attrib)
                #An doume to idio document duo+ fores, pros8etoume ta weights tou
                if child_att['id'] in all_ids:
                    index = all_ids.index(child_att['id'])
                    all_weights[index] = float(all_weights[index]) + float(child_att['weight'])
                #alliws bazoume to id tou document sth lista
                else:
                    all_weights.append(float(child_att['weight']))
                    all_ids.append(child_att['id'])
if len(all_ids)==0:
    sys.exit('No such word found')
#kanoume sort sta id me bash ta weights
Z = [x for y, x in sorted(zip(all_weights, all_ids),reverse=True)]
#kanoume sort kai sta weights
all_weights.sort(reverse=True)

dictionary = dict(zip(Z, all_weights))

end = time.time()

for key, value in dictionary.items():
    print(key, ' : ', value)


elapsed = end - start
print("Elapsed time is ",elapsed)

file0 = open("numbers.txt","a")#append mode
file0.write(str(elapsed))
file0.write(",")
file0.close()

file1 = open("results_of_queries.txt","a")#append mode
file1.write("\nNumber of Inputs: ")
file1.write(str(len(x)))
file1.write("\t\tElapsed Time: ")
file1.write(str(elapsed))
file1.write("\t\tWord(s): ")
for item in x:
    file1.write("%s, " % item)
file1.close()