## 二、实用的第三方库
 
### Pipenv 环境管理 

pipenv是python 官方推荐的包管理工具，集成了virtualenv、pyenv和pip
三者的功能于一身 

- 安装
 
```
$ pip install --user --upgrade pipenv 
```
- 创建环境 
```
$ pipenv --python 3.7
```

- 安装依赖 
```
$ cd myproject 
$ pipenv install requests
$ pipenv install bs4
```



[文档](https://pipenv.readthedocs.io/en/latest/)

### Requests 

Requests 是一个 Python 的第三库，可采用上面的Pipenv方式进行安装。


### BeautifulSoup

Beautiful Soup 是一个可以从HTML或XML文件中提取数据的Python库.
它能够通过你喜欢的转换器实现惯用的文档导航,查找,修改文档的方式.
Beautiful Soup会帮你节省数小时甚至数天的工作时间.

### Python 知识点

- 更方便的打印方式
```
 a = 123 
 b = '你好' 
 s = f'{b} hhh {a}' print(s) # 你好 hhh 123 
```

- 调整位置打印 
```
a = 1
b = 23
c = 30001
print(str(a).ljust(14), str(b).ljust(14), str(c).ljust(14))
print(str(b).ljust(14), str(a).ljust(14), str(c).ljust(14))
print(str(a).ljust(14), str(c).ljust(14), str(b).ljust(14))

#1              23             30001         
#23             1              30001         
#1              30001          23   
```

[文档](https://docs.python.org/zh-cn/3.7/index.html)