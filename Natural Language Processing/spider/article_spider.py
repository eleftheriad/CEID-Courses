import scrapy
from ..items import QuotetutorialItem


class ArticleSpider(scrapy.Spider):

    name = 'articles'
    start_urls = [
        'https://mmajunkie.usatoday.com/',
        'https://chess24.com/en/read/news',
        'https://www.atptour.com/',
        'https://www.cbssports.com/nba/',
        'https://www.ittf.com'
    ]

    def parse(self, response):

        if response.url == "https://mmajunkie.usatoday.com/":
            for articles in response.css('h3 a ::attr("href")').extract():
                yield response.follow(articles, callback=self.parse_article1)

        elif response.url == "https://chess24.com/en/read/news":
            for articles in response.css('h3 a ::attr("href")').extract():
                yield response.follow(articles, callback=self.parse_article2)

        elif response.url == "https://www.atptour.com/":
            for articles in response.css('h3.listing-title a ::attr("href")').extract():
                complete_link = 'https://www.atptour.com' + articles
                yield response.follow(complete_link, callback=self.parse_article3)

        elif response.url == "https://www.cbssports.com/nba/":
            for articles in response.css('div.article-list-pack-image a ::attr("href")').extract():
                complete_link = 'https://www.cbssports.com' + articles
                yield response.follow(complete_link, callback=self.parse_article4)

        elif response.url == "https://www.ittf.com":
            for articles in response.css('div.col-xs-12.col-sm-6 a ::attr("href")').extract():
                yield response.follow(articles, callback=self.parse_article5)


    def parse_article1(self, response):
        items = QuotetutorialItem()

        title = response.css('.article__headline::text').extract_first()
        author = response.css('.fn::text').extract_first()
        text = response.css('.articleBody, .articleBody p::text').extract()
        website = "MMA"
        url = response.url

        items['title'] = title
        items['author'] = author
        items['text'] = text
        items['website'] = website
        items['url'] = url

        yield items

    def parse_article2(self, response):
        items = QuotetutorialItem()

        title = response.css('h1::text').extract_first()
        author = response.css('.ofMiniProfile::text').extract_first()
        text = response.css('p::text').extract()
        website = "Chess"
        url = response.url

        items['title'] = title
        items['author'] = author
        items['text'] = text
        items['website'] = website
        items['url'] = url
        yield items

    def parse_article3(self, response):
        items = QuotetutorialItem()

        title = response.css('.article-title::text').extract_first()
        author = response.css('.name::text').extract_first()
        text = response.css('#articleSection p , p a::text').extract()
        website = "Tennis"
        url = response.url

        items['title'] = title
        items['author'] = author
        items['text'] = text
        items['website'] = website
        items['url'] = url

        yield items

    def parse_article4(self, response):
        items = QuotetutorialItem()

        title = response.css('#article0 .Article-headline::text').extract_first()
        author = response.css('#article0 .ArticleAuthor-name--link::text').extract_first()
        text = response.css('#article0 #Article-body , #article0 p , #article0 a::text').extract()
        website = "NBA"
        url = response.url

        items['title'] = title
        items['author'] = author
        items['text'] = text
        items['website'] = website
        items['url'] = url

        yield items

    def parse_article5(self, response):
        items = QuotetutorialItem()

        title = response.css('.page-header-container h1::text').extract_first()
        author = response.css('em::text').extract_first()
        text = response.css('.col-md-10 p , h6 , .post-introduction p::text').extract()
        website = "Table Tennis"
        url = response.url

        items['title'] = title
        items['author'] = author
        items['text'] = text
        items['website'] = website
        items['url'] = url

        yield items
