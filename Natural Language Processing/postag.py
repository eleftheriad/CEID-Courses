import string
import nltk
import pymongo
from pymongo import MongoClient


connection = MongoClient()
db = connection.myarticles
mycol = db["articles_tb"]

count = mycol.count_documents({})
i=0
while i < count:

    x = mycol.find_one({"myid":1},{"_id":1, "text":1})
    y = x["text"]
    thisid = x["_id"]
    #pairnoume ta tokens
    text = nltk.word_tokenize(y)
    #pros8etoume ta postags
    final = nltk.pos_tag(text)


    query = {"_id": thisid}
    values = {"$set": {"myid": 2, "tokens":final}}
    mycol.update_one(query, values)
    i = i + 1