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
# 获得详细页面 URL
def get_detail_url(url):
	detail_url = [
		"https://lerso.cn/dh/dy/",
		"https://lerso.cn/dh/gw/",
		"https://lerso.cn/dh/sh/",
		"https://lerso.cn/dh/sjs/",
		"https://lerso.cn/dh/zmt/",
		"https://lerso.cn/dh/blockchain/"
	]
	return detail_url


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
		print("请求Error", str(e))
		return None
	try:
		bsObj = BeautifulSoup(html.text, features="html.parser")

		itemList = bsObj.findAll("div", {"class": "xe-widget xe-conversations box2 label-info"})

		items = []
		for item in itemList:
			try:
				item_info = {
					"name": item.findAll("strong")[0].text,
					"info": item["data-original-title"],
					"url": item["onclick"].split('\'')[1],
				}
				print(item_info)
				items.append(item_info)
			except Exception as e:
				print("info error")
	except AttributeError as e:
		return None
	return items


# 数据存储
def saveDataCsv(result, filename="spiderX"):
	csvFile = open(f"../../data/{filename}.csv", 'w', newline="", encoding='utf-8')
	try:
		writer = csv.writer(csvFile)
		writer.writerow(('序号', '名称', '简介', '网址'))
		id = 1
		for i in result:
			writer.writerow((str(id), i["name"], i["info"], i["url"]))
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
				# 提交
				conn.commit()
			except Exception as e:
				print("写入失败：" + str(one))
				continue
	except Exception as e:
		# 错误回滚
		print(e)
		conn.rollback()
	cur.execute("SELECT count(*) FROM searcher_websites")
	print("总计网站： {} 个".format(cur.fetchone()[0]))
	cur.close()
	conn.close()



def run():
	url = 'https://lerso.cn'

	title = getTitle(url)
	print("开始采集网址： {}".format(title))
	urls = get_detail_url(url)
	print(urls)
	all_items = []
	for url in urls:
		print("开始采集: ", url)
		items = getItems(url)
		all_items = all_items + items

	print(f"------------------------采集完成共：{len(all_items)}条-------------------------")
	print(all_items)
	saveDataCsv(all_items, filename="spider03")
	saveDataMysql(all_items, url)
	print("本次共存储网址 {} 个".format(len(all_items)))


if __name__ == "__main__":
	run()
