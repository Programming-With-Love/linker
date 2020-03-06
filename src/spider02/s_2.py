import csv
import requests
from bs4 import BeautifulSoup
from requests import HTTPError
import pymysql


# 网站分析 标题获取
def getTitle(url):
	"""
	:param url:
	:return:  获取标题
	"""
	try:
		html = requests.get(url)

	except HTTPError as e:
		return None
	try:
		bsObj = BeautifulSoup(html.text, features="html.parser")
		title = bsObj.title.text
	except AttributeError as e:
		return None
	return title


# 数据采集
def getItems(url):
	"""
	获取所需网站全部内容块： 名称、 简介、 网址
	分析页面特点  class="item"  为所需内容块
	:param url:
	:return:
	"""
	try:
		html = requests.get(url)
	except HTTPError as e:
		return None
	try:
		bsObj = BeautifulSoup(html.text, features="html.parser")
		itemList = bsObj.findAll("div", {"class": "label-info"})

		items = []
		for item in itemList:

			item_info = {
				"name": item.findAll("strong")[0].text,
				"info": item.findAll("p")[0].text,
				"url": item["data-original-title"],
			}

			items.append(item_info)
	except AttributeError as e:
		return None
	return items


# 数据清洗
def getUsefulInfo(items):
	urls = set()
	result = []
	for item in items:
		if item["url"] not in urls:
			# 去重
			item["name"] = item.get("name").strip().replace("\n", "")
			if item["info"] == '':
				item["info"] = item["name"]
			else:
				item["info"] = item.get("info").strip().replace("\n", "")
			urls.add(item["url"])
			print(item)
			result.append(item)

	return result


# 数据存储
def saveDataCsv(result, filename="spiderX"):
	csvFile = open(f"../../data/{filename}.csv", 'w', newline="", encoding='utf-8')
	try:
		writer = csv.writer(csvFile)
		writer.writerow(('序号', '名称', '简介', '网址'))
		id = 1
		for i in result:
			writer.writerow((str(id),i["name"], i["info"], i["url"]))
			id = id + 1
	finally:
		csvFile.close()


def saveDataMysql(result, fromUrl):
	conn = pymysql.connect(host=' ', user=' ', passwd=' ', db=' ', charset='utf8')
	cur = conn.cursor()
	insertSQL = "insert into searcher_websites (id, webUrl,webName,webDoc,webLogo,webType,cent, fromUrl) value(REPLACE(UUID(),'-',''),'{0}','{1}','{2}','','',0,'{3}')"
	try:
		# 写入数据
		for one in result:
			tempSql = insertSQL.format(one["url"], one["name"], one["info"], fromUrl)
			try:
				cur.execute(tempSql)
				print("写入成功：" + str(one))
			except Exception as e:
				print("写入失败：" + str(one))
		# 提交
		conn.commit()
	except Exception as e:
		# 错误回滚
		print(e)
		conn.rollback()
	cur.execute("SELECT count(*) FROM searcher_websites")
	print("总计网站： {} 个".format(cur.fetchone()[0]))
	cur.close()
	conn.close()


def run():
	url = 'http://miyue1980.com/'
	title = getTitle(url)
	print("开始采集网址： {}".format(title))
	items = getItems(url)
	result = getUsefulInfo(items)
	saveDataCsv(result, filename="spider02")
	saveDataMysql(result, url)
	print("共采集网址 {} 个".format(len(result)))


if __name__ == "__main__":
	run()
