import nltk 
import re
import numpy as np
import pandas as pd 
from sklearn.naive_bayes import MultinomialNB
from pandas import DataFrame
from nltk.corpus import stopwords 
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from nltk.stem.wordnet import WordNetLemmatizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score


df = pd.read_csv("all_data.csv", )

sentences = df.iloc[:, 1].values
together=[]
separator = ' '
stop_words = set(stopwords.words('english'))
lmtzr = WordNetLemmatizer()

for i in range(0,len(sentences)):
    t = sentences[i]
    t = re.sub(r'\s+[a-zA-Z]\s+', ' ', t)
    t = re.sub(r'http\S+', '', t)
    t = re.sub(r'@\S+', '', t)
    t = re.sub(r'#\S+', '', t)
    t = re.sub(r'\s+', ' ', t, flags=re.I)
    token_list = nltk.word_tokenize(t)
    token_list = [word.lower() for word in token_list if word.isalpha()]
    token_list = [word for word in token_list if word not in stopwords.words('english')]
    token_list = [lmtzr.lemmatize(token) for token in token_list]  
    #tokens become text again  
    together.append(separator.join(token_list))

vectorizer = TfidfVectorizer()

X = vectorizer.fit_transform(together)
y = df.iloc[:, 2].values

X_train, X_test, y_train, y_test = train_test_split(X.toarray(), y, test_size=0.20)

model = MultinomialNB().fit(X_train, y_train)

y_pred = model.predict(X_test)

print(confusion_matrix(y_test, y_pred))
print(classification_report(y_test, y_pred)) 
print(accuracy_score(y_test, y_pred))