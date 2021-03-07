
from pymongo import MongoClient
import math
from bson.objectid import ObjectId
import xml.etree.ElementTree as ET
import xml.dom.minidom


connection = MongoClient()
db = connection.myarticles
mycol = db["articles_tb"]

inv_index = ET.Element('inverted_index')

count = mycol.count_documents({})

j=0
total_word_freq = dict()
while j<count:
    x = mycol.find_one({"myid": 4}, {"_id": 1, "Word Frequency": 1})
    thisid = x["_id"]
    word_freq = x["Word Frequency"]
    #enwnw ola ta Word Frequency dicts, xwris na me endiaferoun ta values. Apla 8eloume ola ta monadika keys se ena dict
    total_word_freq = {**word_freq, **total_word_freq}

    myquery = {"_id": thisid}
    newvalues = {"$set": {"myid": 5}}
    mycol.update_one(myquery, newvalues)

    j=j+1


myid_var = 5
# GIA KA8E LEKSH STO ENIAIO DICT POU FTIAKSAME PANW
for word in total_word_freq:
    exists_in_doc_ids = []
    tfs = []
    i=0
    #GIA KA8E DOCUMENT
    while i<count:
        i = i + 1

        x = mycol.find_one({"myid": myid_var}, {"_id": 1, "Word Frequency": 1})
        if x is not None:
            the_id = str(x["_id"])
            word_freq = x["Word Frequency"]
            #N = len(word_freq)

            # An uparxei h leksh sto document, pairnw apla to tf tou sto document
            # kai to id tou gia na ma8w to idf apo to mege8os ths listas
            if word in word_freq:
                exists_in_doc_ids.append(the_id)
                tfs.append(word_freq[word])
                #print("Ta id:",exists_in_doc_ids)
                #print("Ta tf:",tfs)


            myquery = {"_id": ObjectId(the_id)}
            newvalues = {"$set": {"myid": 6}}
            mycol.update_one(myquery, newvalues)

    mycol.update_many({}, {"$set": {"myid": 5}})

    if len(exists_in_doc_ids)==0:
        continue
    #IDF
    idf = math.log(count / len(exists_in_doc_ids))
    #TF-IDF
    tfidfs = [i * idf for i in tfs]

    print("WORD:", word)

    #Ftiaxnw subelement lemma
    lemma = ET.SubElement(inv_index, 'lemma', name=word)
    #ftiaxnw ka8e document kai ta attributes pou exei to lemma
    for i in range(len(exists_in_doc_ids)):
        weight1 = ET.SubElement(lemma, 'document')
        weight1.set('id', exists_in_doc_ids[i])
        weight1.set('weight', str(tfidfs[i]))
        print("doc", exists_in_doc_ids[i], "weight", tfidfs[i])
        print("==================")

    #ka8arizoume tis listes gia thn epomenh leksh
    exists_in_doc_ids.clear()
    tfidfs.clear()

mydata = ET.tostring(inv_index)
myfile = open("inverted_index.xml", "w", encoding='utf-8')
#print sto xml
xml = xml.dom.minidom.parseString(mydata)
xml_pretty_str = xml.toprettyxml()
myfile.write(xml_pretty_str)