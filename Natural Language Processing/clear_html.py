import nltk
import pymongo
import string
from bs4 import BeautifulSoup
from pymongo import MongoClient


connection = MongoClient()
db = connection.myarticles
mycol = db["articles_tb"]

#Ftiaxnw boh8itiko pedio mesa sth bash gia na ksexwrizw poia exoun peiraxtei kai poia oxi
query = {}
values = {"$set": {"myid": 0}}
mycol.update_many(query,values)


count = mycol.count_documents({})
i=0
while i < count:
    #me bash to myid pairnoume ka8e fora to epomeno document
    x = mycol.find_one({"myid":0},{"_id":1, "text":1})
    y = x["text"]
    thisid = x["_id"]


    #bgazoume ta brackets
    html = (" ".join(y))
    #ka8arizoume to html
    raw = BeautifulSoup(html, features="lxml").get_text()
    raw_no_punctuation = raw.translate(str.maketrans('', '', string.punctuation))
    #update th bash
    myquery = {"_id": thisid}
    newvalues = {"$set": {"text": raw_no_punctuation, "myid": 1}}
    mycol.update_one(myquery, newvalues)

    i = i + 1
