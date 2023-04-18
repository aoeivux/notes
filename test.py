import pandas as pd  
import numpy as np  
from sklearn.cross_validation import train_test_split  
from sklearn.metrics import log_loss  
from sklearn.naive_bayes import BernoulliNB  
from sklearn.linear_model import LogisticRegression  
import time  
trainData=pd.read_csv('/data/python6/sf_crime_data/trainData.csv')  
# 添加犯罪的小时时间点作为特征  
features = ['Friday', 'Monday', 'Saturday', 'Sunday', 'Thursday', 'Tuesday',  
'Wednesday', 'BAYVIEW', 'CENTRAL', 'INGLESIDE', 'MISSION',  
'NORTHERN', 'PARK', 'RICHMOND', 'SOUTHERN', 'TARAVAL', 'TENDERLOIN']  
  
hourFea = [str(x) for x in range(0,24)]  
features = features + hourFea  
# 分割训练集(3/5)和测试集(2/5)  
training, validation = train_test_split(trainData, train_size=.60)  
# 朴素贝叶斯建模，计算log_loss  
model = BernoulliNB()  
nbStart = time.time()  
model.fit(training[features], training['crime'])  
nbCostTime = time.time() - nbStart  
predicted = np.array(model.predict_proba(validation[features]))  
print("朴素贝叶斯建模耗时 %f 秒" %(nbCostTime))  
print("朴素贝叶斯log损失为 %f" %(log_loss(validation['crime'], predicted)))  
#逻辑回归建模，计算log_loss  
model = LogisticRegression(C=.01)  
lrStart= time.time()  
model.fit(training[features], training['crime'])  
lrCostTime = time.time() - lrStart  
predicted = np.array(model.predict_proba(validation[features]))  
log_loss(validation['crime'], predicted)  
print( "逻辑回归建模耗时 %f 秒" %(lrCostTime))  
print( "逻辑回归log损失为 %f" %(log_loss(validation['crime'], predicted)))  