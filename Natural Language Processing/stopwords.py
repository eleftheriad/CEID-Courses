import nltk
import pymongo
from pymongo import MongoClient
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from nltk.corpus import wordnet

def get_wordnet_pos(treebank_tag):

    if treebank_tag.startswith('J'):
        return wordnet.ADJ
    elif treebank_tag.startswith('V'):
        return wordnet.VERB
    elif treebank_tag.startswith('N'):
        return wordnet.NOUN
    elif treebank_tag.startswith('R'):
        return wordnet.ADV
    else:
        return wordnet.NOUN

connection = MongoClient()
db = connection.myarticles
mycol = db["articles_tb"]

count = mycol.count_documents({})
lemmatizer = WordNetLemmatizer()
stops = (stopwords.words('english'))
i=0
while i<count:
    x = mycol.find_one({"myid": 2}, {"_id": 1, "tokens": 1})
    thisid = x["_id"]
    tokens_list = x["tokens"]

    counter=0
    element=[]
    #krataw tis 8eseis pou briskontai ta stopwords
    for tokens in tokens_list:
        if tokens[0].lower() in stops:
            element.append(counter)
        counter = counter + 1

    #diagrafw ta stopwords
    for el in sorted(element, reverse=True):
        del tokens_list[el]
    #antika8istw ta tokens me to lemma tous
    for tokens in tokens_list:
        tokens[0]= lemmatizer.lemmatize(tokens[0], get_wordnet_pos(tokens[1])).lower()


    myquery = {"_id": thisid}
    newvalues = {"$set": {"tokens": tokens_list, "myid": 3}}
    mycol.update_one(myquery, newvalues)
    i=i+1