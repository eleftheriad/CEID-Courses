import nltk
import pymongo
from pymongo import MongoClient
connection = MongoClient()
db = connection.myarticles
mycol = db["articles_tb"]


count = mycol.count_documents({})


i=0
while i<count:
    x = mycol.find_one({"myid": 3}, {"_id": 1, "tokens": 1})
    thisid = x["_id"]
    tokens_list = x["tokens"]

    counter=0
    no_doublicates =[]
    values=[]
    for tokens in tokens_list:
        var = tokens[0]
        #ftiaxnw ena list me monadika tokens
        if var in no_doublicates or var.startswith("$"):
            continue
        else:
            no_doublicates.append(var)
            counter = 0
            #metrame th suxnothta twn tokens sto keimeno kai mpainoun se mia allh lista
            for tokens in tokens_list:
                if var == tokens[0]:
                    counter = counter + 1
            values.append(counter)
    #enwnw tis listes se dict
    wordFrequency = dict( zip(no_doublicates,values ))
    #print(wordFrequency)

    #update th bash
    myquery = {"_id": thisid}
    newvalues = {"$set": {"Word Frequency": wordFrequency, "myid": 4}}
    mycol.update_one(myquery, newvalues)

    i=i+1