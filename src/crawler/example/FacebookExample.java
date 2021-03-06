package crawler.example;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;


/**
 * 資料探索練習 Facebook Graph Api Search 
 * 
 * 重點
 * 1. 利用Graph Api調整出需要的資料
 * 2. 取得一組Access Token (試著使用 long term token)
 * 3. 試著用『excel』或任何最簡易的方式，對資料進行探索
 * 
 * @author Abola Lee
 *
 */
public class FacebookExample {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑
		// [query sample]
		// search?fields=name,id,likes,talking_about_count&limit=1000&q=靠北&type=page
		String uri = 
				"https://graph.facebook.com/v2.11"
				+ "/search?q=pitti&type=page&limit=1000&fields=name,id,likes,talking_about_count,fan_count"
				+ "&access_token=EAACEdEose0cBALmfin9ke77WGXXoYJIjaLrVz8M9OQd3pd3qr4w9qSjMJsVZC7lOjxFGroQPjKyyjdRQERZBVLa7NmMLRU9DzxHIiNIvCz13EUYeYvDJfilvd38V4FpZBlZB7rhPFaS4eZBFkD9M5KZAJ3EOoQwLIzwa57umWCL0vb88VX8jXD4s7D205CZAMcZD";



		// Jsoup select 後回傳的是  Elements 物件
//		[data sample]
//		----
//		{
//			"data": [
//			{
//				"name": "靠北工程師",
//					"id": "1632027893700148",
//					"likes": 174587,
//					"talking_about_count": 188119
//			}
//		}
		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "id,名稱,按讚數,討論人數\n";
		
		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String name = data.select("name").text();
			String likes = data.select("likes").text();
			String talking_about_count = data.select("talking_about_count").text();
			
			output += id+"^\""+name+"\"^"+likes+"^"+talking_about_count+"\n";
		}
		
		System.out.println( output );

	} 
}
